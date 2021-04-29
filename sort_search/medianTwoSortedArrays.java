class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
      
      int len1 = nums1.length;
      int len2 = nums2.length;

      int lo = 0;
      int hi = len1;
      
      while (lo <= hi) {
          int part1 = lo + (hi-lo)/2;
          int part2 = (len1 + len2 + 1)/2 - part1;
          
          int left1 = part1 == 0 ? Integer.MIN_VALUE : nums1[part1-1]; 
          int right1 = part1 == len1 ? Integer.MAX_VALUE : nums1[part1];
              
          int left2 = part2 == 0 ? Integer.MIN_VALUE : nums2[part2-1];
          int right2 = part2 == len2 ? Integer.MAX_VALUE : nums2[part2];
          
          if (left1 <= right2 && right1 >= left2) {
              if((len1 + len2) % 2 == 0) {
                  return (Math.max(left1, left2) + Math.min(right1, right2))/2.0;
              } else {
                  return (double)Math.max(left1, left2);
              }
          } else if (left1 > right2) {
              hi = part1 - 1;
          } else {
              lo = part1 + 1;
          }
      }
      return -1.0;
  }
}