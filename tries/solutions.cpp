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