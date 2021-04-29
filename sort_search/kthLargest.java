class Solution {
  public int partition (int start, int end, int[] nums) {
      int swapIdx = start;
      int target = nums[end];
      for (int i=start ; i<=end ; i++) {
          if (nums[i] < target){
              swap(swapIdx, i, nums);
              swapIdx++;
          }
      }
      swap(swapIdx, end, nums);
      return swapIdx;
  }
  
  public void swap (int i, int j, int[] nums) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
  
  public int kSmallest (int start, int end, int[] nums, int k) {
      if(start == end) return nums[start];
      
      int idx = partition(start, end, nums);
      if(idx == k) return nums[idx];
      else if(idx < k) return kSmallest(idx+1, end, nums, k);
      else return kSmallest(start, idx-1, nums, k);
  }
  
  public int findKthLargest(int[] nums, int k) {
      return kSmallest(0, nums.length-1, nums, nums.length - k);
  }
}