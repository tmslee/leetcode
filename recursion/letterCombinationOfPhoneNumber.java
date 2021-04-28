public class letterCombinationOfPhoneNumber {
  Map<Character, String[]> digMap = new HashMap<Character, String[]>();
    
  public void populateDigMap () {
      digMap.put('2', new String[]{"a", "b", "c"});
      digMap.put('3', new String[]{"d", "e", "f"});
      digMap.put('4', new String[]{"g", "h", "i"});
      digMap.put('5', new String[]{"j", "k", "l"});
      digMap.put('6', new String[]{"m", "n", "o"});
      digMap.put('7', new String[]{"p", "q", "r", "s"});
      digMap.put('8', new String[]{"t", "u", "v"});
      digMap.put('9', new String[]{"w", "x", "y", "z"});
      return;
  }
  
  public List<String> solution(String digits) {
      populateDigMap();
      return letterCombHelper(digits);
  }
  
  public List<String> letterCombHelper(String digits){
      List<String> ans = new ArrayList<String>();
      
      if (digits.length() == 0) return ans;
      if (digits.length() == 1) {
          char currDig = digits.charAt(0);
          String[] currChars = digMap.get(currDig);
          for(String c : currChars) {
            ans.add(c); 
          }
          return ans;
      }
      
      char currDig = digits.charAt(0);
      String[] currChars = digMap.get(currDig);
      List<String> prev = letterCombHelper(digits.substring(1, digits.length()));
      for(String s : prev) {
          for(String c : currChars) {
              ans.add(c + s);
          }
      }
      return ans;
  }
}
