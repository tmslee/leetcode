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