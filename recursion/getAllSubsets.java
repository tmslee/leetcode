class Solution {
    
  public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> ans = new ArrayList();
      ans.add(new ArrayList());
      
      for(int num : nums) {
          List<List<Integer>> newSubsets = new ArrayList();
          for(List<Integer> curr : ans) {
              List<Integer> newSubset = new ArrayList<Integer>(curr);
              newSubset.add(num);
              newSubsets.add(new ArrayList<Integer>(curr));
          }
          for(List<Integer> newSubset : newSubsets) ans.add(newSubset);
      }
      return ans;
  }
}