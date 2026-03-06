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

// 53 maximum subarray
int maxSubArray(const std::vector<int>& nums) {
    int ans = std::numeric_limits<int>::min();
    int currSum = 0;
    for(const int n : nums) {
        currSum = std::max(currSum + n, n);
        ans = std::max(ans, currSum);
    }
    return ans;
}

// 121 best time to buy and sell stock
int maxProfit(const std::vector<int>& prices) {
    int minBuy = std::numeric_limits<int>::max();
    int ans = 0;
    for(const int price : prices) {
        minBuy = std::min(minBuy, price);
        ans = std::max(ans, price-minBuy);
    }
    return ans;
}

// 55 jump game
bool canJump(const std::vector<int>& nums) {
    const int n = static_cast<int>(nums.size());
    int curr_max = nums[0];
    for(int i=1; i<n; ++i){
        if(i>curr_max) return false;
        curr_max = std::max(curr_max, i+nums[i]);
    }
    return true;
}

// 134 gas station
int canCompleteCircuit(const std::vector<int>& gas, const std::vector<int>& cost) {
    const int n = static_cast<int>(gas.size());
    int total_gain = 0;
    int curr_gain = 0;
    int ans = 0;
    for(int i=0; i<n; ++i) {
        total_gain += gas[i] - cost[i];
        curr_gain += gas[i] - cost[i];
        if(curr_gain < 0) {
            ans = i+1;
            curr_gain = 0;
        }
    }
    return total_gain >= 0 ? ans : -1;
}