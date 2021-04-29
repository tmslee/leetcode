class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int len1 = nums1.length;
      int len2 = nums2.length;
      
      if(len1 > len2) return findMedianSortedArrays(nums2, nums1);
      
      int lo = 0;
      int hi = len1;
      while (lo <= hi) {
          int partition1 = lo + (hi - lo)/2;
          int partition2 = (len1 + len2 + 1)/2 - partition1;
          
          int left1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
          int right1 = partition1 == len1 ? Integer.MAX_VALUE : nums1[partition1];
          int left2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
          int right2 = partition2 == len2 ? Integer.MAX_VALUE : nums2[partition2];
          
          if (left1 <= right2 && left2 <= right1) {
              if ((len1 + len2) % 2 == 0) {
                  return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
              } else {
                  return (double)Math.max(left1, left2);
              }
          } else if (left1 > right2) {
              hi = partition1 - 1;
          } else {
              lo = partition1 + 1;
          }
      }
      return -1.0;
  }
}