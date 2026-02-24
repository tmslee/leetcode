// 45 jump game II
int jump(const std::vector<int>& nums) {
    int jumps = 0;
    int currEnd = 0;
    int maxReach = 0;
    for(int i=0; i<std::ssize(nums); ++i) {
        maxReach = std::max(maxReach, i+nums[i]);
        if(i == currEnd && i!=std::ssize(nums)-1) {
            ++jumps;
            currEnd = maxReach;
        }
    }
    return jumps;
}