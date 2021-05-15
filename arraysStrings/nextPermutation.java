class Solution {
  public int findFirstDecreasing (int[] nums) {
      for(int i=nums.length-2 ; i>=0 ; i--){
          if(nums[i] < nums[i+1]) return i;
      }
      return -1;
  }
  
  //can do binary search here
  public int findClosestLarger (int start, int end, int target, int[] nums) {
      int ans = start;
      for(int i=start ; i<=end ; i++){
          if(nums[i] > target) ans = i;
          else break;
      }
      return ans;
  }
  
  public void swap (int i, int j, int[] arr){
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      return;
  }
  
  //you can also sort here. but reversing is faster
  public void reverse(int start, int end, int[] nums){
      int l=start;
      int r=end;
      for(int i=0 ; i<(end-start+1)/2 ; i++){
          swap(l, r, nums);
          l++;
          r--;
      }
  }
  
  public void nextPermutation(int[] nums) {
      if(nums.length < 2) return;
      //if sorted descending all the way, sort the array
      // 1. find index of number starting from right where it decreses
      // 2. find number betwen i+1 and end the closest number that is larger
      // 3. swap these
      // 4. sort i+1 to end
      int firstDecreasingIdx = findFirstDecreasing(nums);
      if(firstDecreasingIdx == -1) Arrays.sort(nums);
      else {
          int decreasingNum = nums[firstDecreasingIdx];
          int closestLargerIdx = findClosestLarger(firstDecreasingIdx+1, nums.length-1, decreasingNum, nums);
          swap(firstDecreasingIdx, closestLargerIdx, nums);
          reverse(firstDecreasingIdx+1, nums.length-1, nums);
      }
  }
}