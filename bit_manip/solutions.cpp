// 136. single number
int singleNumber(const std::vector<int>& nums) {
    int ans = 0;
    for(const int n : nums) {
        ans ^= n;
    }
    return ans;
}

// 190 reverse bits
uint32_t reverseBits(uint32_t n) {
    uint32_t ans = 0;
    for(int i=0; i<32; ++i) {
        ans <<= 1;
        ans |= (n&1);
        n >>= 1;
    }
    return ans;
}

// 191 number of 1 bits
int hammingWeight(uint32_t n) {
    int ans = 0;
    while (n) {
        n &= n-1;
        ++ans;
    }
    return ans;
}
// std lib
int hammingWeight(uint32_t n) {
    return std::popcount(n);
}