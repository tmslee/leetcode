// 127 word ladder
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

// 126 word ladder II 
std::vector<std::vector<std::string>> findLadders(
    const std::string& beginWord, 
    const std::string& endWord, 
    const std::vector<std::string>& wordList) 
{
    std::unordered_map<std::string, std::vector<std::string>> graph;
    auto insertToMap = [&](const std::string& word) -> void{
        std::string key(word);
        for(int i=0; i<static_cast<int>(word.size()); ++i) {
            const char temp = key[i];
            key[i] = '#';
            graph[key].push_back(word);
            key[i] = temp;
        }
    };

    bool end_exists = false;
    insertToMap(beginWord);
    for(const auto& word : wordList) {
        if(word == endWord) end_exists = true;
        insertToMap(word);
    }
    if(!end_exists) return {};

    std::queue<string> q;
    std::unordered_map<std::string, std::vector<std::string>> parents;
    std::unordered_map<std::string, int> dist;

    dist[beginWord] = 0;
    q.push(beginWord);
    bool end_found = false;

    while(!q.empty() && !end_found) {
        const int level_size = static_cast<int>(q.size());
        for(int i=0; i<level_size; ++i){
            const auto curr = q.front(); 
            q.pop();

            if(curr == endWord) end_found = true;

            const int curr_dist = dist[curr];
            std::string key(curr);

            for(int j=0; j<static_cast<int>(curr.size()); ++j) {
                const char temp = key[j];
                key[j] = '#';
                if(const auto it = graph.find(key); it != graph.end()) {
                    for(const auto& nei : it->second) {
                        if(nei == curr) continue;
                        //update distance
                        auto [distIt, inserted] = dist.try_emplace(nei, curr_dist+1);
                        if(inserted) {
                            q.push(nei);
                        }
                        if(dist[nei] == curr_dist+1) {
                            parents[nei].push_back(curr);
                        }
                    }
                }
                key[j] = temp;
            }
        }
        
    }

    if(!end_found) return {};

    std::vector<std::vector<std::string>> ans;
    std::vector<std::string> curr({endWord});

    auto backtrack = [&](this auto& self)-> void {
        if(curr.back() == beginWord) {
            auto path = curr;
            std::reverse(path.begin(), path.end());
            ans.push_back(std::move(path));
            return;
        }
        for(const auto& parent : parents[curr.back()]) {
            curr.push_back(parent);
            self();
            curr.pop_back();
        }
    };

    backtrack();
    return ans;
}