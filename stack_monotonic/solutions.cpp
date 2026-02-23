// 20. valid parantheses
bool isValid(const std::string& s) {
    std::vector<char> stack;
    stack.reserve(s.size());

    static const std::unordered_map<char, char> match {
        {')', '('},
        {']', '['},
        {'}', '{'}
    };

    for(const char c : s) {
        if(c=='(' || c=='{' || c=='[') {
            stack.emplace_back(c);
        } else {
            const auto it = match.find(c);
            if(it == match.end()) continue; //unexepcted char
            if(stack.empty() || stack.back() != it->second) return false;
            stack.pop_back();
        }
    }

    return stack.empty();
}