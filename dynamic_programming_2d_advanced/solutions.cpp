// 10 regular expression matching
bool isMatch(const std::string& s, const std::string& p) {
    const int slen = std::ssize(s);
    const int plen = std::ssize(p);

    std::vector<std::vector<bool>> dp(slen+1, std::vector<bool>(plen+1, false));

    dp[0][0] = true;
    for(int i=1; i<plen+1; ++i) {
        if(p[i-1] == '*') {
            dp[0][i] = dp[0][i-2];
        }
    }

    for(int i=1; i<=slen; ++i) {
        for(int j=1; j<=plen; ++j) {
            const char schar = s[i-1];
            const char pchar = p[j-1];
            if(pchar == '.' || pchar == schar) {
                dp[i][j] = dp[i-1][j-1];
            } else if(pchar == '*') {
                dp[i][j] = dp[i][j-2];
                const char prev_pchar = p[j-2];
                if(prev_pchar == '.' || prev_pchar == schar) {
                    dp[i][j] = dp[i][j] || dp[i-1][j];
                }
            }
        }
    }

    return dp[slen][plen];
}