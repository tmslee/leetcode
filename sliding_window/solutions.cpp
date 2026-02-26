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