class AutocompleteSystem {
  //1. find words -> trie
  //2. find top 3 -> sort
  
  //optimize: maybe during insertion update priority queue
  //  -> does not work bc we need to update the entire tree everytime we do a search.
  
  Map<String, Integer> countMap;
  TrieNode root; 
  String currTerm;
  
  class TrieNode {
      char c;
      Set<String> words;
      Map<Character, TrieNode> children;
      
      public TrieNode(char c) {
          this.c = c;
          words = new HashSet<String>();
          children = new HashMap<Character, TrieNode>();
      }
  }
  
  public void insertToTrie(TrieNode parent, String word, int charIdx) {
      parent.words.add(word);
      
      if(charIdx >= word.length()) return;
      char currC = word.charAt(charIdx);
      
      if(parent.children.containsKey(currC)) {
          insertToTrie(parent.children.get(currC), word, charIdx+1);
      } else {
          TrieNode newNode = new TrieNode(currC);
          parent.children.put(currC, newNode);
          insertToTrie(newNode, word, charIdx+1);
      }
  }
  
  public Set<String> getWordsFromSearch (TrieNode parent, String searchTerm, int charIdx) {
      if(charIdx == searchTerm.length()) return parent.words;
      
      char currC = searchTerm.charAt(charIdx);
      if (parent.children.containsKey(currC)) {
          return getWordsFromSearch(parent.children.get(currC), searchTerm, charIdx+1);
      } 
      return new HashSet<String>();
  }
          
  public List<String> getTop3List (String searchTerm) {      
      Set<String> wordSet = getWordsFromSearch(root, searchTerm, 0);
      List<String> ans = new ArrayList<String>(wordSet);
      Collections.sort(ans, new Comparator<String>(){
          @Override
          public int compare (String str1, String str2) {
              int count1 = countMap.get(str1);
              int count2 = countMap.get(str2);
              if (count1 != count2) return count2-count1;
              else return str1.compareTo(str2);
          }
      });
      if(ans.size() <= 3) return ans;
      else return ans.subList(0,3);
  }
  
  public AutocompleteSystem(String[] sentences, int[] times) {
      countMap = new HashMap<String, Integer>();
      root = new TrieNode('#');
      currTerm = "";
      
      for(int i=0 ; i<sentences.length ; i++) {
          countMap.put(sentences[i], times[i]);
          insertToTrie(root, sentences[i], 0);
      }
  }
  
  public List<String> input(char c) {
      List<String> ans = new ArrayList<>();
      
      if (c == '#') {
          if (!countMap.containsKey(currTerm)) {
              insertToTrie(root, currTerm, 0);
          } 
          int count = countMap.getOrDefault(currTerm, 0);
          countMap.put(currTerm, count+1);
          currTerm = ""; 
          return ans;
      }
      currTerm += c;
      return getTop3List(currTerm);
  }
}

/**
* Your AutocompleteSystem object will be instantiated and called as such:
* AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
* List<String> param_1 = obj.input(c);
*/