//17 letter combinations of a phone number
std::vector<std::string> letterCombinations(const std::string& digits) {
    if(digits.empty()) return {};

    static constexpr std::array<std::string_view, 8> digmap = {
        "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    
    std::vector<string> ans;
    std::string curr;
    const auto n = std::ssize(digits);

    auto helper = [&](this auto&self, int idx) -> void {
        if(idx == n) {
            ans.push_back(curr);
            return;
        }
        for(const char c : digmap[digits[idx] - '2']) {
            curr.push_back(c);
            self(idx+1);
            curr.pop_back();
        }
    };
    helper(0);
    return ans;
}

// 22 generate parentheses
std::vector<string> generateParenthesis(const int n) {
    std::vector<string> ans;
    std::string curr;
    auto gen = [&](this auto& self, const int opens, const int closes)-> void {
        if(static_cast<int>(curr.size()) == n*2) {
            ans.push_back(curr);
            return;
        } 
        if(opens < n) {
            curr.push_back('(');
            self(opens+1, closes);
            curr.pop_back();
        }
        if(closes < opens) {
            curr.push_back(')');
            self(opens, closes+1);
            curr.pop_back();
        }
    };
    gen(0, 0);
    return ans;
}