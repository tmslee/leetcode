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

// 41 first missing positive
int firstMissingPositive(vector<int>& nums) {
    const int n = std::ssize(nums);
    bool one_present = false;
    for(int i=0; i<n; ++i) {
        if(nums[i] == 1) one_present = true;
        if(nums[i] <= 0) nums[i] = 1;
    }
    if(!one_present) return 1;
    
    for(int i=0; i<n; ++i) {
        const int idx = std::abs(nums[i])-1;
        if(idx < n) {
            nums[idx] = -std::abs(nums[idx]);
        }
    }
    
    for(int i=0; i<n; ++i){
        if(nums[i] > 0) return i+1;
    }
    return n+1;
}