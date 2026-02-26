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

