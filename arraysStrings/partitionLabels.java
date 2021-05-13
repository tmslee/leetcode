class Solution {
  public List<Integer> partitionLabels(String s) {
      List<Integer> ans = new ArrayList();
      
      int[] lastSeen = new int[26];
      for (int i=0 ; i<s.length() ; i++) {
          lastSeen[s.charAt(i) - 'a'] = i;
      }
      
      int minParitionEnd = 0;
      int partitionStart = 0;
      for(int i=0 ; i<s.length() ; i++) {
          minParitionEnd = Math.max(minParitionEnd, lastSeen[s.charAt(i) - 'a']);
          if(i == minParitionEnd) { //
              ans.add(i - partitionStart + 1);
              partitionStart = i+1;
          }
      }
      return ans;
  }
}