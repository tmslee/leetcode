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

// 84 largest rectangle in histogram
int largestRectangleArea(const std::vector<int>& heights) {
    const int n = std::ssize(heights);
    std::vector<int> stack;
    stack.reserve(n+1);
    int ans = 0;
    for(int i=0; i<=n; ++i) {
        // height 0 at i==n to flush remaining bars from stack
        const int currH = i<n ? heights[i] : 0;
        while(!stack.empty() && heights[stack.back()] > currH) {
            const int h = heights[stack.back()]; 
            stack.pop_back();
            const int w = stack.empty() ? i : i-stack.back()-1;
            ans = std::max(ans, h*w);
        } 
        stack.push_back(i);
    }
    return ans;
}