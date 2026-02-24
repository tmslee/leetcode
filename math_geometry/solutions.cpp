// 43. multiply string
std::string multiply(const std::string& num1, const std::string& num2) {        
    const int s1 = std::ssize(num1);
    const int s2 = std::ssize(num2);
    std::vector<int> ans(s1+s2, 0);

    for(int i=s1-1; i>=0; --i) {
        for(int j=s2-1; j>=0; --j) {
            const int prod = (num1[i]-'0')*(num2[j]-'0');
            const int p1 = i+j;
            const int p2 = i+j+1;

            const int sum = prod + ans[p2];
            ans[p2] = sum%10;
            ans[p1] += sum/10;
        }
    }

    std::string result;
    for(const int digit : ans) {
        if(result.empty() && digit == 0) continue;
        result += static_cast<char>(digit + '0');
    }
    return result.empty() ? "0" : result;
}