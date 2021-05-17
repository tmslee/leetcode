class Solution {
    
  // 1. make map of words
  // 2. traverse and try to make valid order
  //      use indegree
  
  Map<Character, Set<Character>> charMap = new HashMap();
  int[] indegree = new int[26];
  
  public boolean getEdgeFromWords (String s1, String s2) {        
      int len = Math.min(s1.length(), s2.length());
      boolean diffFound = false;
      for(int i=0 ; i<len ; i++) {
          char c1 = s1.charAt(i);
          char c2 = s2.charAt(i);
          if(c1 != c2) {
              diffFound = true;
              Set<Character> charChildren = charMap.getOrDefault(c1, new HashSet());
              if(!charChildren.contains(c2)){
                  indegree[c2 - 'a']++;
                  charChildren.add(c2);
                  charMap.put(c1, charChildren);

              }
              break;
          }
      }
      if(!diffFound && s1.length() > s2.length()) return false;
      return true;
  }
  
  public boolean populateMap (String[] words) {
      Arrays.fill(indegree, -1); //first fill with -1
      
      for(String word : words){
          for(int i=0 ; i<word.length() ; i++) {
              indegree[word.charAt(i) - 'a'] = 0;
              if(!charMap.keySet().contains(word.charAt(i))){
                  charMap.put(word.charAt(i), new HashSet());
              }
          }
      }
      
      for(int i=0 ; i<words.length-1; i++) {
          if (!getEdgeFromWords(words[i], words[i+1])) return false;
      }
      return true;
  }

  public List<Character> get0Indegree (int[] indegree) {
      List<Character> ans = new ArrayList();
      for(int i=0 ; i<26 ; i++) {
          if(indegree[i] == 0) ans.add((char) ('a' + i));
      }
      return ans;
  }
  
  public String alienOrder(String[] words) {
      String ans = "";
      if (!populateMap(words)) return ans;
       
  
      List<Character> noIndegree = get0Indegree(indegree);
      if(noIndegree.size() == 0) return ans;
      
      Queue<Character> q = new LinkedList();
      q.addAll(noIndegree);
      int validEdges=0;
      while(!q.isEmpty()) {
          Character currChar = q.poll();
          ans += currChar;
          Set<Character> children = charMap.get(currChar);
      
          if(children != null) {
              for(Character c : children) {
                  indegree[c - 'a']--;
                  if(indegree[c - 'a'] == 0) q.add(c); 
              }
          }
      }

      if(ans.length() == charMap.keySet().size()) return ans;
      return "";
  }
}