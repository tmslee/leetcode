// 3. longest substring without repeating character
int lengthOfLongestSubstring(const std::string& s) {
    std::vector<int> last_idx(128, -1);
    const auto n = std::ssize(s);
    int ans = 0;
    int l = 0;

    for(int r=0; r<n; ++r) {
        const auto idx = static_cast<int>(s[r]);
        if(last_idx[idx] >= l) {
            l = last_idx[idx]+1;
        }
        last_idx[idx] = r;
        ans = std::max(ans, r-l+1);
    }
    return ans;
}

// 76 minimum window substring
int getIdx(const char c) const {
    return std::islower(c) ? c-'a' : c-'A' + 26;
}

std::string minWindow(const std::string& s, const std::string& t) {
    const int len = std::ssize(s); 
    std::vector<int> counts(52, 0);
    int diff = 0;
    
    for(const char c : t) {
        if(++counts[getIdx(c)] == 1) ++diff;
    }

    int start = 0;
    int shortest_len = std::numeric_limits<int>::max();
    int l=0;

    for(int r=0; r<len; ++r) {
        const int ridx = getIdx(s[r]);
        if(--counts[ridx] == 0) --diff;

        while(diff==0) {
            if(r-l+1 < shortest_len) {
                shortest_len = r-l+1;
                start = l;
            }
            const int lidx = getIdx(s[l]);
            if(++counts[lidx] == 1) ++diff;
            ++l;
        }
    }
    return (shortest_len == std::numeric_limits<int>::max()) ? "" : s.substr(start, shortest_len);
}

//159 longest substring with at most two distinct characters
int lengthOfLongestSubstringTwoDistinct(const std::string& s) {
    const int n = static_cast<int>(s.size());
    int l=0;
    int ans = 0;

    int last_seen1 = -1;
    int last_seen2 = -1;
    
    for(int r=0; r<n; ++r) {
        const char rc = s[r];
        if(last_seen1 == -1 || s[last_seen1] == rc) {
            last_seen1 = r;
        } else if(last_seen2 == -1 || s[last_seen2] == rc) {
            last_seen2 = r;
        } else {
            const int min_last_seen = std::min(last_seen1, last_seen2);
            l = min_last_seen + 1;
            if(min_last_seen == last_seen1) {
                last_seen1 = r;
            } else {
                last_seen2 = r;
            }
        }
        ans = std::max(ans, r-l+1);
    }
    return ans;
}