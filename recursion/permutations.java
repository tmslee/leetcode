class Solution {
  List<List<Integer>> ans = new ArrayList();
  int[] nums;
  
  public void swap(int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
  
  public void backtrack(int startIdx) {
      if(startIdx == nums.length) {
          List<Integer> curr = new ArrayList();
          for(int num : nums) curr.add(num);
          ans.add(curr);
          return;
      }
      
      for(int i=startIdx ; i<nums.length; i++){
          swap(startIdx, i);
          backtrack(startIdx+1);
          swap(startIdx, i);
      }
  }
  
  public List<List<Integer>> permute(int[] nums) {
      this.nums = nums;
      backtrack(0);
      return ans;
  }
}