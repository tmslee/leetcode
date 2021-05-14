class Solution {
  public List<String> findStroboHelper(int n) {
      List<String> ans = new ArrayList();
      if(n == 1) {
          ans.add("0");
          ans.add("1");
          ans.add("8");
          return ans;
      }
      if(n == 2){
          ans.add("00");
          ans.add("11");
          ans.add("69");
          ans.add("88");
          ans.add("96");
          return ans;
      }
      List<String> prev = findStroboHelper(n-2);
      for(String str : prev) {
          ans.add("0" + str + "0");
          ans.add("1" + str + "1");
          ans.add("6" + str + "9");
          ans.add("9" + str + "6");
          ans.add("8" + str + "8");
      }
      return ans;
  }
  
  public List<String> findStrobogrammatic(int n) {
      List<String> strobos = findStroboHelper(n);
      if(n == 1) return strobos;
      
      List<String> ans = new ArrayList();
      for(String str : strobos) if(str.charAt(0) != '0') ans.add(str);
      return ans;
  }
}