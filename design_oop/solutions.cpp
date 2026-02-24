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