// 98. validate bst
bool helper(const TreeNode* node, std::optional<int> lo, std::optional<int> hi) {
    if(!node) return true;
    if(lo && node->val <= *lo) return false;
    if(hi && node->val >= *hi) return false;
    return helper(node->left, lo, node->val) && helper(node->right, node->val, hi);
}

bool isValidBST(const TreeNode* root) {
    return helper(root, std::nullopt, std::nullopt);
}

// 100 same tree
bool isSameTree(TreeNode* p, TreeNode* q) {
    if(!p && !q) return true;
    if(!p || !q) return false;
    if(p->val == q->val) {
        return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
    }
    return false;
}

// 102 binary tree level order traversal
std::vector<std::vector<int>> levelOrder(TreeNode* root) {
    std::vector<std::vector<int>> ans;
    if(!root) return ans;

    std::queue<TreeNode*> q;
    q.push(root);
    while(!q.empty()) {
        const int sz = static_cast<int>(q.size());
        std::vector<int> curr_level;
        for(int i=0; i<sz; ++i) {
            const auto* node = q.front();
            q.pop();
            curr_level.push_back(node->val);
            if(node->left) q.push(node->left);
            if(node->right) q.push(node->right);
        }
        ans.push_back(std::move(curr_level));
    }
    return ans;
}

// 104 max depth of binary tree
int maxDepth(TreeNode* root) {
    if(!root) return 0;
    return std::max(maxDepth(root->left), maxDepth(root->right)) + 1;
}