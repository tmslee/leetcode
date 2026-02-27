// 5. longest palindromic substring
std::string longestPalindrome(const std::string& s) {
    if(s.empty()) return {};

    using P = std::pair<int,int>;
    const int len = std::ssize(s);
    
    auto expand = [&](int l, int r) -> P {
        while(l>=0 && r<len && s[l]==s[r]) {
            --l;
            ++r;
        }
        return {l+1, r-1};
    };

    int best_start = 0;
    int best_len = 0;
    
    for(int i=0; i<len; ++i) {
        for(auto [l,r] : {expand(i,i), expand(i,i+1)}) {
            const int length = r-l+1;
            if(length > best_len) {
                best_start = l;
                best_len = length;
            }
        }
    }
    return s.substr(best_start, best_len);
}

// 70 climbing stairs
int climbStairs(const int n) {
    if (n <= 2) return n;
    int prev2 = 1;
    int prev1 = 2;
    for (int i = 3; i <= n; ++i) {
        const int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}