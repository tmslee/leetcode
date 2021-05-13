class Solution {
  int index = 0;
  public String decodeString(String s) {
      StringBuilder ans = new StringBuilder();
      while (index < s.length() && s.charAt(index) != ']') {
          if(!Character.isDigit(s.charAt(index))) {
              ans.append(s.charAt(index));
              index++;
          } else {
              int repeatCount = 0;
              while(index < s.length() && Character.isDigit(s.charAt(index))){
                  repeatCount = repeatCount*10 + s.charAt(index) - '0';
                  index++;
              }
              index++; //opening bracket skipped 
              String decodedStr = decodeString(s);
              index++; //closing bracket skipped
              while(repeatCount>0) {
                  ans.append(decodedStr);
                  repeatCount--;
              }
          }
      }
      return ans.toString();
  }
}