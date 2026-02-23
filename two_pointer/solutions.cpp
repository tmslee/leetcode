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