// 136. single number
int singleNumber(const std::vector<int>& nums) {
    int ans = 0;
    for(const int n : nums) {
        ans ^= n;
    }
    return ans;
}