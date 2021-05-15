class Solution {
  public boolean isAnagram(String s, String t) {
      if(s.length() != t.length()) return false;
      int [] charCount = new int[26];
      for(int i=0 ; i<s.length() ; i++) {
          char sChar = s.charAt(i);
          char tChar = t.charAt(i);
          charCount[sChar - 'a'] ++;
          charCount[tChar - 'a'] --;
      }
      for(int i=0 ; i<26 ; i++){
          if(charCount[i] != 0) return false;
      }
      return true;
  }
}