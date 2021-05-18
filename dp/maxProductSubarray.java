class Solution {
  int ans;
  int[] minMem;
  int[] maxMem;
  
  public int maxProduct(int[] nums) {
      ans = nums[0];
      minMem = new int[nums.length];
      maxMem = new int[nums.length];
      
      minMem[0] = nums[0];
      maxMem[0] = nums[0];
      
      for(int i=1 ; i<nums.length ; i++) {
          int minCont = nums[i]*minMem[i-1];
          int maxCont = nums[i]*maxMem[i-1];
          
          int minMemCont = Math.min(Math.min(minCont, maxCont), nums[i]);
          int maxMemCont = Math.max(Math.max(minCont, maxCont), nums[i]);
          
          minMem[i] = minMemCont;
          maxMem[i] = maxMemCont;
          
          ans = Math.max(ans, maxMemCont);
      }
      return ans;
  }
}