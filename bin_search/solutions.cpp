// 4. median of two sorted arrays
double findMedianSortedArrays(const vector<int>& nums1,const vector<int>& nums2) {
    if(nums1.size() > nums2.size()) {
        return findMedianSortedArrays(nums2, nums1);
    }
    constexpr auto kMin = std::numeric_limits<int>::min();
    constexpr auto kMax = std::numeric_limits<int>::max();

    const int sz1 = static_cast<int>(nums1.size());
    const int sz2 = static_cast<int>(nums2.size());
    const int half = (sz1+sz2)/2;
    const bool is_even = (sz1%2) == (sz2%2);

    int lo=-1;
    int hi=sz1-1;
    while (lo <= hi) {
        const int cut1 = lo + (hi - lo)/2;
        
        const int left1 = (cut1 >= 0) ? nums1[cut1] : kMin;
        const int right1 = (cut1+1 < sz1) ? nums1[cut1+1] : kMax;

        const int cut2 = half - (cut1+1) - 1;
        const int left2 = (cut2 >= 0) ? nums2[cut2] : kMin;
        const int right2 = (cut2+1 < sz2) ? nums2[cut2+1] : kMax;

        if(left1 <= right2 && left2 <= right1) {
            if(is_even) {
                return (std::max(left1, left2) + std::min(right1, right2))/2.0;
            } else {
                return std::min(right1, right2);
            }
        } 
        if(left1 > right2) {
            hi = cut1-1;
        } else {
            lo = cut1+1;
        }
    }
    
    throw std::invalid_argument{"no valid median partition found"};
}

// 33 search in rotated sorted array
int search(const std::vector<int>& nums, const int target) {
    const int n = std::ssize(nums);
    int l = 0;
    int r = n-1;
    while(l <= r) {
        const int m = l + (r-l)/2;
        if(nums[m] == target) return m;
        if(nums[l] <= nums[m]) {
            if(target >= nums[l] && target < nums[m]) {
                r = m-1;
            } else {
                l = m+1;
            }
        } else {
            if(target > nums[m] && target <= nums[r]) {
                l = m+1;
            } else {
                r = m-1;
            }
        }
    }
    return -1;
}

// 34 find first and last position of element in sorted array
std::vector<int> searchRange(const std::vector<int>& nums, const int target) {
    const int n = static_cast<int>(nums.size());
    auto helper = [&](const int t, const bool is_left) -> int {
        int l = 0;
        int r = n-1;
        int ans = -1;
        while(l<=r) {
            int m = l + (r-l)/2;
            if(nums[m] == t) {
                ans = m;
            }
            if(is_left) {
                if(nums[m] >= t) {
                    r = m-1;
                } else {
                    l = m+1;
                }
            } else {
                if(nums[m] <= t) {
                    l = m+1;
                } else {
                    r = m-1;
                }
            }
        }
        return ans;
    };
    return {helper(target, true), helper(target, false)};
}
// using lower_bound/ uppser_bound
vector<int> searchRange(vector<int>& nums, int target) {
    auto lo = std::lower_bound(nums.begin(), nums.end(), target);
    auto hi = std::upper_bound(nums.begin(), nums.end(), target);
    
    if(lo == nums.end() || *lo != target) return {-1, -1};
    return {
        static_cast<int>(lo - nums.begin()),
        static_cast<int>(hi - nums.begin()) - 1
    };
}

// 74 search a 2d matrix
bool searchMatrix(const std::vector<std::vector<int>>& matrix, const int target) {
    if(matrix.empty()) return false;
    const int rows = static_cast<int>(matrix.size());
    const int cols = static_cast<int>(matrix[0].size());

    int lo = 0;
    int hi = rows*cols - 1;
    while (lo <= hi) {
        const int mid = lo + (hi-lo)/2;
        const int r = mid/cols;
        const int c = mid%cols;
        if(matrix[r][c] == target) return true;
        if(matrix[r][c] < target) {
            lo = mid+1;
        } else {
            hi = mid-1;
        }
    }
    return false;
}

// 153 find minimum in rotated sorted array
int findMin(const std::vector<int>& nums) {
    const int n =static_cast<int>(nums.size());
    int l = 0;
    int r = n-1;
    int ans = std::numeric_limits<int>::max();
    while(l <= r) {
        int m = l + (r-l)/2;
        if(nums[l] <= nums[m]) { //left side sorted
            ans = std::min(ans, nums[l]);
            l = m+1;
        } else { //right side sorted
            ans = std::min(ans, nums[m]);
            r = m-1;
        }
    }
    return ans;
}