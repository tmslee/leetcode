// 1. two Sum https://leetcode.com/problems/two-sum/description/
vector<int> twoSum(const vector<int>& nums, int target) {
    unordered_map<int,int> idx_map;
    const auto n = static_cast<int>(nums.size());
    for(int i=0; i<nums.size(); ++i) {
        if(auto it = idx_map.find(target-nums[i]) ; it != idx_map.end()) {
            return {it->second, i};
        }
        idx_map[nums[i]] = i;
    }
    throw std::invalid_argument{"no two-sum solution exists"};
}

// 11. container with most water
int maxArea(const vector<int>& height) {
    int l=0;
    int r=std::ssize(height)-1;
    int ans = 0;
    while (l < r) {
        ans = std::max(ans, (r-l)*std::min(height[l], height[r]));
        if(height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
}