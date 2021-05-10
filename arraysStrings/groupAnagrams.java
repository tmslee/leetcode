class Solution {
  Map<String, List<String>> codeMap = new HashMap<>();
  
  public String encode (String str){
      StringBuilder res = new StringBuilder();
      
      int[] charCounts = new int[26];
      for(int i=0 ; i<str.length() ; i ++){
          int charVal = str.charAt(i) - 'a';
          charCounts[charVal]++;
      }
      for(int i=0 ; i<26 ; i++){
          if(charCounts[i] != 0){
              char currChar = (char) ('a' + i);
              res.append(currChar);
              res.append(charCounts[i]);
          }
      }
      return res.toString();
  }
  
  public List<List<String>> groupAnagrams(String[] strs) {
      List<List<String>> ans = new ArrayList<>();
      for (String str : strs) {
          String code = encode(str);
          List<String> stringList = codeMap.getOrDefault(code, new ArrayList<String>());
          stringList.add(str);
          codeMap.put(code, stringList);
      }
      for (String code : codeMap.keySet()){
          ans.add(codeMap.get(code));
      }
      return ans;
  }
}