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

// 150 evaluate reverse polish notation
int evalRPN(const std::vector<std::string>& tokens) {
    std::stack<int> st;
    for(const auto& token : tokens) {
        if(token.size() == 1 && !std::isdigit(token[0])) {
            int sec = st.top();
            st.pop();
            int fir = st.top();
            st.pop();
            switch(token[0]) {
                case '+': st.push(fir + sec); break;
                case '-': st.push(fir - sec); break;
                case '*': st.push(fir * sec); break;
                case '/': st.push(fir / sec); break;
            }
        } else {
            st.push(std::stoi(token));
        }
    }
    return st.top();
}

// 224 basic calculator
int calculate(const std::string& s) {
    std::stack<int> signs;
    signs.push(1);
    int result = 0;
    int sign = 1;
    const int n = static_cast<int>(s.size());

    for (int i = 0; i < n; ++i) {
        const char c = s[i];
        if (std::isdigit(c)) {
            long long num = 0;
            while (i < n && std::isdigit(s[i])) {
                num = num * 10 + (s[i] - '0');
                ++i;
            }
            result += static_cast<int>(sign*num);
            --i;  // outer loop will ++i
        } else if (c == '+') {
            sign = signs.top();
        } else if (c == '-') {
            sign = -signs.top();
        } else if (c == '(') {
            signs.push(sign);
        } else if (c == ')') {
            signs.pop();
        }
    }
    return result;
}