// 208 implement trie
class Trie {
public:
    struct TrieNode {
        std::unordered_map<char, std::unique_ptr<TrieNode>> children;
        bool is_word = false;
    };
    
    std::unique_ptr<TrieNode> root = std::make_unique<TrieNode>();

    void insert(const std::string& word) {
        TrieNode* curr = root.get();
        const int sz = std::ssize(word);
        for(const char c : word){
            auto& child = curr->children[c]; 
            if(!child) {
                child = std::make_unique<TrieNode>();
            }
            curr = child.get();
        }
        curr->is_word = true;
    }

    const TrieNode* find(const std::string& prefix) const {
        TrieNode* curr = root.get();
        for(const char c : prefix) {
            const auto it = curr->children.find(c);
            if(it == curr->children.end()) return nullptr;
            curr = it->second.get();
        }
        return curr;
    }
    
    bool search(const std::string& word) const {
        const auto* node = find(word);
        return node && node->is_word;
    }
    
    bool startsWith(const std::string& prefix) const {
        return find(prefix) != nullptr;
    }
};

// 211 design add and search words
class WordDictionary {

struct TrieNode {
    std::unordered_map<char, std::unique_ptr<TrieNode>> children;
    bool is_word = false;
};

std::unique_ptr<TrieNode> root = std::make_unique<TrieNode>();

bool searchHelper(TrieNode* curr, const int idx, std::string_view word) const{
    if(!curr) return false;
    if(idx == word.length()) return curr->is_word;

    const char c = word[idx];
    if(c != '.') {
        const auto it = curr->children.find(c);
        if(it == curr->children.end()) return false;
        return searchHelper(it->second.get(), idx+1, word);
    }
    
    for(const auto& [child, childNode] : curr->children) {
        if(searchHelper(childNode.get(), idx+1, word)) {
            return true;
        }
    }
    return false;
}

public:
    WordDictionary() = default;
    
    void addWord(const std::string& word) {
        TrieNode* curr = root.get();
        for(const char c : word) {
            auto& child = curr->children[c];
            if(!child) child = std::make_unique<TrieNode>();
            curr = child.get();
        }
        curr->is_word = true;
    }
    
    [[nodiscard]] bool search(const std::string& word) const {
        return searchHelper(root.get(), 0, word);
    }
};

// 212 word search II
class Solution {

struct TrieNode {
    std::unordered_map<char, std::unique_ptr<TrieNode>> children;
    std::string_view word;
};

std::unique_ptr<TrieNode> root = std::make_unique<TrieNode>();

void addWord(std::string_view word) {
    TrieNode* curr = root.get();
    for(const char c : word) {
        auto& child = curr->children[c];
        if(!child) child = std::make_unique<TrieNode>();
        curr = child.get();
    }
    curr->word = word;
}

public:
    std::vector<std::string> findWords(std::vector<std::vector<char>>& board, const std::vector<std::string>& words) {
        // put in a trie, search using it, update trie if we find the word.
        if(board.empty()) return {};

        for(const auto& word : words) {
            addWord(word);
        }

        std::vector<std::string> ans;
        
        const int rows = static_cast<int>(board.size());
        const int cols = static_cast<int>(board[0].size());
        static constexpr std::array<std::pair<int,int>, 4> offsets = {{
            {1,0}, {-1,0}, {0,1}, {0,-1}
        }};

        auto search = [&](this auto& self, TrieNode* curr, const int row, const int col) -> void {
            const char temp = board[row][col];
            
            auto it = curr->children.find(temp);
            if(it == curr->children.end()) return;

            TrieNode* next = it->second.get();
            if(!next->word.empty()) {
                ans.emplace_back(next->word);
                next->word = {};
            }

            board[row][col] = '#';
            for(const auto [roffset, coffset] : offsets){
                const int nr = row+roffset;
                const int nc = col+coffset;
                if(nr>=0 && nc>=0 && nr<rows && nc<cols && board[nr][nc] != '#') {
                    self(next, nr, nc);
                }
            }
            board[row][col] = temp;

            if(next->children.empty()) {
                curr->children.erase(temp);
            }
        };

        for(int r=0; r<rows; ++r) {
            for(int c=0; c<cols; ++c) {
                if(root->children.empty()) break;
                search(root.get(), r, c);
            }
        }
        return ans;
    }
};

// 642 design search autocomplete system
class AutocompleteSystem {
    struct TrieNode {
        std::unordered_map<char, std::unique_ptr<TrieNode>> children;
        std::vector<int> entries;
    };
    
    std::unique_ptr<TrieNode> root_ = std::make_unique<TrieNode>();
    TrieNode* curr_node_ = nullptr;
    TrieNode* prev_node_ = nullptr;

    std::unordered_map<int, std::string> imap_;
    std::unordered_map<std::string, int> cmap_;

    int next_id_ = 0;
    std::string curr_entry_;

    std::vector<std::string> cache_;

    void insertToTrie(int entry_id, const std::string& entry) {
        TrieNode* curr = root_.get();
        for(const char c : entry) {
            auto& child = curr->children[c];
            if(!child) child = std::make_unique<TrieNode>();
            curr = child.get();
            curr->entries.push_back(entry_id);
        }
    }

    void addOrIncrement(const std::string& entry, int count=1) {
        if(auto it = cmap_.find(entry); it != cmap_.end()) {
            it->second += count;
            return;
        }
        const int id = next_id_++;
        imap_[id] = entry;
        cmap_[entry] = count;
        insertToTrie(id, entry);
    }

    void finalizeCurrentEntry() {
        if(curr_entry_.empty()) return;
        addOrIncrement(curr_entry_);
        curr_entry_.clear();
    }

    void resetInputTraversalState() {
        curr_node_ = root_.get();
        prev_node_ = nullptr;
        cache_.clear();
    }

    const std::vector<std::string>& getTopEntries() {
        if(!curr_node_) return {};

        if(prev_node_ && prev_node_->entries.size() == curr_node_->entries.size()) {
            return cache_;
        }

        auto cmp = [this](const int a, const int b) {
            const auto& sa = imap_.at(a);
            const auto& sb = imap_.at(b);
            const int ca = cmap_.at(sa);
            const int cb = cmap_.at(sb);
            if(ca != cb) return ca > cb;
            return sa < sb;
        };

        std::priority_queue<int, std::vector<int>, decltype(cmp)> pq(cmp);
        for(const int idx : curr_node_->entries) {
            pq.push(idx);
            if(static_cast<int>(pq.size()) > 3) pq.pop();
        }

        const int sz = static_cast<int>(pq.size());
        cache_.resize(sz);
        for(int i=sz-1; i>=0; --i) {
            cache_[i] = imap_.at(pq.top());
            pq.pop();
        }
        return cache_;
    }

public:
    AutocompleteSystem(const std::vector<std::string>& sentences, const std::vector<int>& times) {
        const int n = static_cast<int>(sentences.size());
        for(int i=0; i<n; ++i) {
            addOrIncrement(sentences[i], times[i]);
        }
        resetInputTraversalState();
    }
    
    std::vector<std::string> input(char c) {
        if(c == '#'){
            finalizeCurrentEntry();
            resetInputTraversalState();
            return {};
        } 
        
        curr_entry_ += c;
        if(!curr_node_) return {};

        auto it = curr_node_->children.find(c);
        if(it == curr_node_->children.end()) {
            curr_node_ = nullptr;
            return {};
        }
        prev_node_ = curr_node_;
        curr_node_ = it->second.get();
        return getTopEntries();
    }
};

// 1032 stream of characters 
class StreamChecker {
public:
    struct TrieNode {
        std::array<unique_ptr<TrieNode>, 26> children;
        bool is_end = false;
    };

    std::unique_ptr<TrieNode> root = std::make_unique<TrieNode>();
    std::vector<char> curr_str;

    void insertWord(const std::string_view sv) {
        TrieNode* curr = root.get();
        const int len = static_cast<int>(sv.size());
        for(int i=len-1; i>=0; --i) {
            char c = sv[i];
            auto& child = curr->children[c-'a'];
            if(!child) child = std::make_unique<TrieNode>();
            curr = child.get();
        }
        curr->is_end = true;
    }

    StreamChecker(const std::vector<std::string>& words) {
        for(const auto& word : words) {
            insertWord(word);
        }
    }


    bool query(char letter) {
        curr_str.push_back(letter);
        TrieNode* curr = root.get();
        const int len = static_cast<int>(curr_str.size());
        for(int i=len-1; i>=0; --i) {
            char c = curr_str[i];
            auto& child = curr->children[c-'a'];
            if(!child) return false;
            curr = child.get();
            if(curr->is_end) return true;
        }
        return false;
    }
};