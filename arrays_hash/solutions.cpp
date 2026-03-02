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

// 49 group anagrams
std::vector<std::vector<std::string>> groupAnagrams(const std::vector<std::string>& strs) {
    std::unordered_map<std::string, vector<string>> groups;

    auto make_key = [](const std::string& s) {
        std::array<int, 26> counts{};
        for(const char c : s) ++counts[c-'a'];
        return std::string(counts.begin(), counts.end());
    };

    for(const auto& str : strs) {
        std::string key = make_key(str);
        groups[key].push_back(str);
    }
    
    std::vector<std::vector<std::string>> ans;
    for(const auto& [key, group] : groups) {
        ans.push_back(std::move(group));
    }
    return ans;
}