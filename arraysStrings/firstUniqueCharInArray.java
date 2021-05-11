class Solution {
  public int firstUniqChar(String s) {
      int[] charIdxArr = new int[26];
      Arrays.fill(charIdxArr, Integer.MIN_VALUE);
      
      for (int i=0 ; i<s.length() ; i++) {
          char currC = s.charAt(i);
          int charIdx = currC - 'a';
          if(charIdxArr[charIdx] == Integer.MIN_VALUE) charIdxArr[charIdx] = i;
          else charIdxArr[charIdx] = -i;
      }
      
      int ans = Integer.MAX_VALUE;
      for(int i=0; i<26 ; i++) {
          if(charIdxArr[i] >= 0) {
              ans = Math.min(ans, charIdxArr[i]);
          }
      }
      if (ans == Integer.MAX_VALUE) return -1;
      else return ans;
  }
}