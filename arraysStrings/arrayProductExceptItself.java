class Solution {
  public int[] productExceptSelf(int[] nums) {
      int [] ans = new int[nums.length];
      ans[0] = 1;
      
      int acc = 1;
      for (int i=1 ; i<nums.length ; i++) {
          acc *= nums[i-1];
          ans[i] = acc;
      }
      acc = 1;
      for (int i=nums.length-2 ; i>=0 ; i--){
          acc *= nums[i+1];
          ans[i] *= acc;
      }
      return ans;
  }
}