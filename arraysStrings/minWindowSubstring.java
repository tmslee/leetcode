class Solution {
    
  Map<Character, Integer> charCount = new HashMap<>();
  
  public String minWindow(String s, String t) {
      if(t.length() > s.length()) return "";
      if(t.equals(s)) return s;
      
      for (int i=0 ; i<t.length() ; i++) {
          char currChar = t.charAt(i);
          int count = charCount.getOrDefault(currChar, 0);
          charCount.put(currChar, count+1);
      }
      
      int countMatches = charCount.keySet().size();
      
      int minl = 0;
      int minr = 0;
      int minLen = Integer.MAX_VALUE;
      
      int l=0;
      int r=0;
      
      while(r<s.length()) {
          char rChar = s.charAt(r);
          
          //decrement if rChar is part of needed chars
          //update countMatches
          if (charCount.containsKey(rChar)){ 
              int count = charCount.get(rChar);
              count--;
              if(count == 0) countMatches--;
              charCount.put(rChar, count);
          }
          
          //if countMatches is 0 -> we have a window that satisfies
          if(countMatches == 0) {
              //now we want to increment left side until our window is invalid
              while (countMatches == 0) {
                  char lChar = s.charAt(l);
                  if (charCount.containsKey(lChar)){ 
                      int count = charCount.get(lChar);
                      count++;
                      if(count == 1) countMatches++;
                      charCount.put(lChar, count);
                  }
                  l++;
              }
              
              //at this point we want to update minl and minr
              int len = r-(l-1) + 1; //we want to sub 1 from l bc l-1 is the valid window start
              if(len < minLen) {
                  minl = l-1;
                  minr = r;
                  minLen = len;
              }
          }
          r++;
      }
      
      if(minLen != Integer.MAX_VALUE) return s.substring(minl, minr+1);
      else return "";    
  }
}