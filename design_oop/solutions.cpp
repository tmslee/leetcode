// 146 LRU cache
class LRUCache {
private:
    using Entry = pair<int, int>;
    std::unordered_map<int, std::list<Entry>::iterator> itmap_;
    std::list<Entry> entries_;
    const int cap_;
    
    void update_recency(int key) {
        entries_.splice(entries_.end(), entries_, itmap_[key]);    
    }

public:
    explicit LRUCache(int capacity) : cap_(capacity) {}
    
    int get(int key) {
        const auto it = itmap_.find(key);
        if(it == itmap_.end()) return -1;
        update_recency(key);
        return it->second->second;
    }
    
    void put(int key, int value) {
        if(auto it = itmap_.find(key); it != itmap_.end()) {
            it->second->second = value;
            update_recency(key);
        } else {
            entries_.push_back({key, value});
            itmap_[key] = std::prev(entries_.end());

            if(std::ssize(entries_) > cap_) {
                const int evict_key = entries_.front().first;
                entries_.pop_front();
                itmap_.erase(evict_key);
            }
        }
    }
};

// 355 design twitter
// Twitter Design with Two-Layer Celebrity Caching
//
// Layer 1 (base cache): non-celeb tweets, pre-merged via k-way merge
//   - Invalidated when: follow/unfollow non-celeb, non-celeb tweets
//   - Built lazily on next getNewsFeed call
//
// Layer 2 (feed cache): base cache merged with followed celebs' tweets
//   - Invalidated when: layer 1 invalidated, or a followed celeb tweets
//   - Built lazily on next getNewsFeed call
//
// Write path (postTweet):
//   - Non-celeb: invalidate both layers for all followers
//   - Celeb: invalidate only layer 2 (feed) for all followers
//
// Read path (getNewsFeed):
//   - If feed cache valid → return immediately (O(1))
//   - If base cache invalid → rebuild base, then merge with celebs
//   - If only feed invalid → just re-merge with celebs
//
// Complexity tradeoffs:
//   postTweet:  O(followers) for invalidation (just bool flips)
//   getNewsFeed: O(1) cache hit, O(followees * kFeedSize) cache miss
//   follow/unfollow: O(1) amortized, O(followers) on celeb demotion

#include <deque>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <vector>

class Twitter {
    static constexpr int kFeedSize = 10;
    static constexpr int kCelebPromote = 1000;
    static constexpr int kCelebDemote = 900;

    int time_ = 0;
    using Tweet = std::pair<int, int>;  // {timestamp, tweetId}

    // ── Core data ──
    std::unordered_map<int, std::deque<Tweet>> tweets_;
    std::unordered_map<int, std::unordered_set<int>> following_;
    std::unordered_map<int, std::unordered_set<int>> followers_;
    std::unordered_set<int> celebs_;

    // ── Layer 1: base cache (non-celeb tweets only) ──
    std::unordered_map<int, std::deque<Tweet>> base_cache_;
    std::unordered_map<int, std::unordered_set<int>> base_sources_;
    std::unordered_map<int, bool> base_valid_;

    // ── Layer 2: final feed (base + celeb tweets merged) ──
    std::unordered_map<int, std::vector<int>> feed_cache_;
    std::unordered_map<int, bool> feed_valid_;

    // ──────────────────────────────────────────────
    // K-way merge utility
    // ──────────────────────────────────────────────

    // Merges multiple sorted deques (newest at back) into a single
    // newest-first result of at most kFeedSize items.
    struct MergeSource {
        const std::deque<Tweet>* data;
        int idx;
        int source_id;
    };

    std::vector<Tweet> kWayMerge(std::vector<MergeSource>& sources) {
        using Entry = std::pair<Tweet, int>;  // {tweet, source_index}
        std::priority_queue<Entry> pq;

        for (int i = 0; i < static_cast<int>(sources.size()); ++i) {
            if (sources[i].idx >= 0) {
                pq.push({(*sources[i].data)[sources[i].idx], i});
            }
        }

        std::vector<Tweet> result;
        result.reserve(kFeedSize);

        while (result.size() < kFeedSize && !pq.empty()) {
            const auto [tweet, src_idx] = pq.top();
            pq.pop();
            result.push_back(tweet);

            if (--sources[src_idx].idx >= 0) {
                pq.push({(*sources[src_idx].data)[sources[src_idx].idx], src_idx});
            }
        }

        return result;  // newest-first order
    }

    // ──────────────────────────────────────────────
    // Layer 1: build base cache
    // ──────────────────────────────────────────────

    void buildBaseCache(int user_id) {
        std::vector<MergeSource> sources;

        // Own tweets
        if (!tweets_[user_id].empty()) {
            sources.push_back({
                &tweets_[user_id],
                static_cast<int>(tweets_[user_id].size()) - 1,
                user_id
            });
        }

        // Non-celeb followees
        for (const int id : following_[user_id]) {
            if (!celebs_.contains(id) && !tweets_[id].empty()) {
                sources.push_back({
                    &tweets_[id],
                    static_cast<int>(tweets_[id].size()) - 1,
                    id
                });
            }
        }

        auto merged = kWayMerge(sources);

        // Store as deque (oldest-first for consistency with tweet storage)
        base_cache_[user_id].clear();
        base_sources_[user_id].clear();

        for (auto it = merged.rbegin(); it != merged.rend(); ++it) {
            base_cache_[user_id].push_back(*it);
        }

        // Track which sources contributed
        for (const auto& src : sources) {
            if (src.idx < static_cast<int>(src.data->size()) - 1) {
                // This source had at least one tweet consumed
                base_sources_[user_id].insert(src.source_id);
            }
        }
        // Simpler: just record all sources that had tweets
        base_sources_[user_id].clear();
        for (const auto& src : sources) {
            base_sources_[user_id].insert(src.source_id);
        }

        base_valid_[user_id] = true;
    }

    // ──────────────────────────────────────────────
    // Layer 2: merge base cache with celeb tweets
    // ──────────────────────────────────────────────

    void buildFeedCache(int user_id) {
        if (!base_valid_[user_id]) {
            buildBaseCache(user_id);
        }

        const auto& base = base_cache_[user_id];

        // Collect celeb sources
        std::vector<MergeSource> sources;

        // Base cache as one source
        if (!base.empty()) {
            sources.push_back({
                &base,
                static_cast<int>(base.size()) - 1,
                -1  // sentinel: base cache
            });
        }

        // Celeb followees
        for (const int id : following_[user_id]) {
            if (celebs_.contains(id) && !tweets_[id].empty()) {
                sources.push_back({
                    &tweets_[id],
                    static_cast<int>(tweets_[id].size()) - 1,
                    id
                });
            }
        }

        // If no celebs, just use base directly
        if (sources.size() <= 1) {
            feed_cache_[user_id].clear();
            feed_cache_[user_id].reserve(base.size());
            for (auto it = base.rbegin(); it != base.rend(); ++it) {
                feed_cache_[user_id].push_back(it->second);
            }
        } else {
            auto merged = kWayMerge(sources);
            feed_cache_[user_id].clear();
            feed_cache_[user_id].reserve(merged.size());
            for (const auto& tweet : merged) {
                feed_cache_[user_id].push_back(tweet.second);
            }
        }

        feed_valid_[user_id] = true;
    }

    // ──────────────────────────────────────────────
    // Invalidation helpers
    // ──────────────────────────────────────────────

    void invalidateBase(int user_id) {
        base_valid_[user_id] = false;
        feed_valid_[user_id] = false;  // feed depends on base
    }

    void invalidateFeed(int user_id) {
        feed_valid_[user_id] = false;
    }

    void ensureUser(int user_id) {
        if (!tweets_.contains(user_id)) {
            tweets_[user_id];
            following_[user_id];
            followers_[user_id];
            base_valid_[user_id] = false;
            feed_valid_[user_id] = false;
        }
    }

public:
    Twitter() = default;

    void postTweet(int user_id, int tweet_id) {
        ensureUser(user_id);

        tweets_[user_id].push_back({time_++, tweet_id});
        if (tweets_[user_id].size() > kFeedSize) {
            tweets_[user_id].pop_front();
        }

        // Invalidate own caches
        invalidateBase(user_id);

        if (celebs_.contains(user_id)) {
            // Celeb: only feed layer needs rebuild for followers
            for (const int f : followers_[user_id]) {
                invalidateFeed(f);
            }
        } else {
            // Non-celeb: base + feed layers need rebuild for followers
            for (const int f : followers_[user_id]) {
                invalidateBase(f);
            }
        }
    }

    std::vector<int> getNewsFeed(int user_id) {
        ensureUser(user_id);

        if (!feed_valid_[user_id]) {
            buildFeedCache(user_id);
        }

        return feed_cache_[user_id];
    }

    void follow(int follower_id, int followee_id) {
        if (follower_id == followee_id) return;
        ensureUser(follower_id);
        ensureUser(followee_id);

        if (following_[follower_id].contains(followee_id)) return;

        following_[follower_id].insert(followee_id);
        followers_[followee_id].insert(follower_id);

        // Check celeb promotion
        if (followers_[followee_id].size() >= kCelebPromote) {
            celebs_.insert(followee_id);
        }

        // New followee's tweets may affect our feed
        if (celebs_.contains(followee_id)) {
            invalidateFeed(follower_id);       // celeb: only feed layer
        } else {
            invalidateBase(follower_id);       // non-celeb: both layers
        }
    }

    void unfollow(int follower_id, int followee_id) {
        if (follower_id == followee_id) return;
        if (!following_[follower_id].contains(followee_id)) return;

        following_[follower_id].erase(followee_id);
        followers_[followee_id].erase(follower_id);

        // Check celeb demotion (with hysteresis)
        const bool was_celeb = celebs_.contains(followee_id);
        if (was_celeb && followers_[followee_id].size() < kCelebDemote) {
            celebs_.erase(followee_id);

            // Demoted to non-celeb: their tweets were never in followers'
            // base caches, so all followers need base rebuild
            for (const int f : followers_[followee_id]) {
                invalidateBase(f);
            }
        }

        // Invalidate unfollower's cache if the unfollowed user contributed
        if (was_celeb) {
            invalidateFeed(follower_id);
        } else if (base_sources_[follower_id].contains(followee_id)) {
            invalidateBase(follower_id);
        } else {
            // Unfollowed user had no tweets in our cache — no rebuild needed
        }
    }
};