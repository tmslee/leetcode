// word ladder
int ladderLength(const std::string& beginWord, const std::string& endWord, const std::vector<string>& wordList) {
    std::unordered_map<std::string, vector<std::string_view>> graph;

    for(const auto& word : wordList) {
        std::string pattern(word);
        for(int i=0; i< std::ssize(word); ++i) {
            const char temp = pattern[i];
            pattern[i] = '#';
            graph[pattern].push_back(word);
            pattern[i] = temp;
        }
    }

    int depth = 1;
    std::unordered_set<std::string> seen{beginWord};
    std::queue<std::string> q;
    q.push(beginWord);

    while(!q.empty()) {
        const auto level_size = std::ssize(q);
        for(int i=0; i<level_size; ++i) {
            auto curr = std::move(q.front()); q.pop();
            for(int j=0; j<std::ssize(curr); j++) {
                const char temp = curr[j];
                curr[j] = '#';
                if(const auto it = graph.find(curr); it != graph.end()) {
                    for(const auto nei : it->second) {
                        if(nei == endWord) return depth+1;
                        if(!seen.contains(std::string(nei))) {
                            seen.emplace(nei);
                            q.emplace(nei);
                        }
                    }
                }
                curr[j] = temp;
            }
        }
        ++depth;
    }
    return 0;
}