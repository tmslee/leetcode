class Solution {
    
  public String Stringify(int lower, int upper) {
      if(lower == upper) return Integer.toString(lower);
      else return Integer.toString(lower) + "->" + Integer.toString(upper);
  }
  
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
      List<String> ans = new ArrayList();
      
      int prev = lower - 1;
      for (int i = 0; i <= nums.length; i++) {
          int curr = (i < nums.length) ? nums[i] : upper + 1;
          if (prev + 1 <= curr - 1) {
              ans.add(Stringify(prev + 1, curr - 1));
          }
          prev = curr;
      }
      
      return ans;
  }
}