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

// 15 3sum
std::vector<std::vector<int>> threeSum(std::vector<int>& nums) {
    std::sort(nums.begin(), nums.end());
    const int n = std::ssize(nums);
    std::vector<std::vector<int>> ans;

    for(int i=0; i<n; ++i) {
        if(nums[i] > 0) break;
        if(i>0 && nums[i] == nums[i-1]) continue;

        int l = i+1;
        int r = n-1;
        while(l < r) {
            const int tot = nums[l] + nums[r] + nums[i];
            if(tot == 0) {
                ans.push_back({nums[i], nums[l], nums[r]});
            }
            if(tot <= 0) {
                ++l;
                while(l<r && nums[l] == nums[l-1]) ++l;
            } else {
                --r;
                while(l<r && nums[r] == nums[r+1]) --r;
            }
        }
    }
    return ans;
}

// 42 trapping rainwater
int trap(const std::vector<int>& height) {
    int l=0;
    int r=static_cast<int>(height.size())-1;
    int lmax = 0;
    int rmax = 0;

    int ans = 0;
    /*
        if h[l] < h[r]
        we know that the bottleneck wtill be lmax
        only case where this can go wrong is is h[r] is SHORTER than lmax -> impossible
        iteration ensures we always move the shorter pointer, meaning at any given point in time
        all h[r] that is shorter than lmax wouldve been processed already
        meaning that current h[r] >= lmax.
        
        AT ALL TIMES:
        if h[l] < h[r] : h[r] >= lmax
        if h[r] >= h[l] : h[l] >= rmax
    */
    while(l<r) {
        if(height[l] < height[r]) {
            lmax = std::max(lmax, height[l]);
            ans += lmax-height[l];
            ++l;
        } else {
            rmax = std::max(rmax, height[r]);
            ans += rmax-height[r];
            --r;
        }
    }
    return ans;
}