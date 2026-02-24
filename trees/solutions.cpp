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