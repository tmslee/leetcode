class Solution {
  class TrieNode {
      char c;
      Map<Character, TrieNode> children;
      Set<String> words;
      
      public TrieNode(char c) {
          this.c = c;
          children = new HashMap();
          words = new HashSet();
      }
  }
  
  TrieNode root = new TrieNode('#');
  
  public Set<String> getWordsStartingWith (TrieNode parent, String prefix, int charIdx) {
      if(charIdx >= prefix.length()) return parent.words;
      
      char c = prefix.charAt(charIdx);
      TrieNode currNode = parent.children.getOrDefault(c, null);
      if(currNode == null) return new HashSet();
      return getWordsStartingWith(currNode, prefix, charIdx+1);
  }
  
  public void insertToTrie (TrieNode parent, String word, int charIdx) {
      if(charIdx >= word.length()) return;
      
      char c = word.charAt(charIdx);
      TrieNode currNode = parent.children.getOrDefault(c, new TrieNode(c));
      currNode.words.add(word);
      parent.children.put(c, currNode);
      insertToTrie(currNode, word, charIdx+1);
  }
  
  public void backtrack (List<String> curr, List<List<String>> ans) {
      if(curr.size() == curr.get(0).length()){
          ans.add(curr);
          return;
      }
      String prefix = "";
      
      int letterIdx = curr.size();
      
      for(int i=0 ; i<curr.size() ; i++) {
          prefix += curr.get(i).charAt(letterIdx);
      }
      
      Set<String> candidates = getWordsStartingWith(root, prefix, 0);
      
      for(String candidate : candidates){
          List<String> currCopy = new ArrayList(curr);
          currCopy.add(candidate);
          backtrack(currCopy, ans);
      }
  }
  
  public List<List<String>> wordSquares(String[] words) {
      List<List<String>> ans = new ArrayList();
      
      for(String word : words) insertToTrie(root, word, 0);
      
      for(String word : words) {
          List<String> curr = new ArrayList<>();
          curr.add(word);
          backtrack(curr, ans);
      }
  
      return ans;
  }
}