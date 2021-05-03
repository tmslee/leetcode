class Solution {
  //try expanding from valid centers, check length, update start and end index
  //retrn substring once done
  
  public int expandFrom(String s, int c1, int c2){
      int left = c1;
      int right = c2;
      
      while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
          left--;
          right++;
      }
      return right - left - 1;
  }
  
  public String longestPalindrome(String s) {
      if(s == null || s.length() < 1) return "";
      int start = 0;
      int end = 0;
      for(int i=0 ; i<s.length() ; i++) {
          int len1 = expandFrom(s, i, i);
          int len2 = expandFrom(s, i, i+1);
          int len = Math.max(len1, len2);
          
          if(len > end - start) {
              start = i - (len-1)/2;
              end = i + len/2;
          }
      }
      
      return s.substring(start, end+1);
  }  
}