class Solution {
  int ans = 0;
  Set<Character> currWindow = new HashSet();
  Map<Character, Integer> lastSeen = new HashMap();
  
  public int lengthOfLongestSubstringTwoDistinct(String s) {
      int l = 0;
      int r = 0;
      
      while(r<s.length()) {
          char c = s.charAt(r);
          lastSeen.put(c, r);
    
          if((currWindow.size() == 2 && currWindow.contains(c)) || currWindow.size() < 2) {
              //expand window
              currWindow.add(c);
              ans = Math.max(ans, r-l+1);
              
          } else {
              //we must update l to minimum last seen index between the 2 unique chars
              char removeChar = '#';
              int minLastSeen = Integer.MAX_VALUE;
              for(Character windowChar : currWindow) {
                  if(lastSeen.get(windowChar) < minLastSeen) {
                      minLastSeen = lastSeen.get(windowChar);
                      removeChar = windowChar;
                  }
              }
              l = minLastSeen+1;
              currWindow.remove(removeChar);
              currWindow.add(c);
          }
          r++;
      }
      return ans;
  }
}