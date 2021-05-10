class Solution {
  /*
      solution 1: 2 pointers
          1. sort smallest to large
          2. iterate. at current index i, use 2 pointers l and r that ranges from i+1 to end of array
          3. if complement too small shift l else shift r
          4. account for dups
          
      solution 2: no sort
          1. iterate through
          2. @ iteration (i), iterate from j nums[i+1] and calculate complement
          3. if we have seen the complement during our current iteration (i), we found triplet
  */
  
  public List<List<Integer>> threeSum1(int[] nums) {
      List<List<Integer>> ans = new ArrayList<>();
      if(nums.length == 0) return ans;
      Arrays.sort(nums);
      int outerPrev = nums[0];
      for(int i=0 ; i<nums.length ; i++) {
          if(i== 0 || nums[i] != outerPrev){
              int l = i+1;
              int r = nums.length - 1;
              while(l<r) {
                  int sum = nums[l] + nums[r] + nums[i];
                  if (sum < 0) {
                      l++;
                  } else if (sum > 0){
                      r--;
                  } else {
                      ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                      l++; r--;
                      while(l < r && nums[l] == nums[l-1]) l++; 
                  }
              }
          }
          outerPrev = nums[i];
      }
      return ans;
  }
  
  public List<List<Integer>> threeSum2(int[] nums) {
      Set<List<Integer>> res = new HashSet<>();
      Set<Integer> dups = new HashSet<>();
      Map<Integer, Integer> seen = new HashMap<>();
      
      for (int i=0 ; i<nums.length ; i++) {
          if (!dups.contains(nums[i])) {
              dups.add(nums[i]);
              
              for (int j=i+1 ; j<nums.length ; j++) {
                  int complement = -nums[i] - nums[j];
                  if (seen.containsKey(complement) && seen.get(complement) == i) {
                      List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                      Collections.sort(triplet);
                      res.add(triplet);
                  }
                  seen.put(nums[j], i);
              }
          }
      }
      return new ArrayList(res);
  }
}