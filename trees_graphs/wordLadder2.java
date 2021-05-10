class Solution {
  Map <String, List<String>> auxWordMap = new HashMap<>();
  Set<String> visited = new HashSet<>();
  
  public void populateWordMap (String word) {
      for(int i=0 ; i<word.length() ; i++) {
          String currAuxWord = word.substring(0, i) + "*";
          if(i < word.length() - 1) currAuxWord += word.substring(i+1, word.length());
          List<String> wordList = auxWordMap.getOrDefault(currAuxWord, new ArrayList<>());
          wordList.add(word);
          auxWordMap.put(currAuxWord, wordList);
      }
  }
  
  public List<String> getAdjWords (String word) {
      List<String> res = new ArrayList<>();
      for(int i=0 ; i<word.length() ; i++) {
          String currAuxWord = word.substring(0, i) + "*";
          if(i < word.length() -1) currAuxWord += word.substring(i+1, word.length());
          List<String> wordList = auxWordMap.get(currAuxWord);
          if(wordList != null) res.addAll(wordList);
      }
      return res;
  }
  
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      for (String word : wordList) {
          populateWordMap(word);
      }
      List<List<String>> ans = new ArrayList<>();
      LinkedList<List<String>> pathsQ = new LinkedList<>();
      
      List<String> startPath = new ArrayList<>();
      startPath.add(beginWord);
      pathsQ.addLast(startPath);
      
      int currDepth = 1;
      int minDepth = Integer.MAX_VALUE;
      
      Set<String> visitedThisLevel = new HashSet<>();
      Set<String> wordSet = new HashSet(wordList);
      
      visitedThisLevel.add(beginWord);
      
      //do bfs and only exit @ end of a new depth
      //the queue is a queue of paths, and we get adjacent word from wordMap
      //we know what the current word is by grabbing the last element from the list
      
      while(!pathsQ.isEmpty()) {
          List<String> currPath = pathsQ.removeFirst();
          
          if(currPath.size() > currDepth) { 
              //when entering new level you update the wordset and reset visited
              //these words will never be visited again after this level and should be removed from wordSet
              
              wordSet.removeAll(visitedThisLevel);
              visitedThisLevel.clear();
              
              if(currPath.size() > minDepth) break;
              if(currPath.size() > currDepth) currDepth = currPath.size();
          }

          String currWord = currPath.get(currPath.size()-1);
          List<String> currAdjWords = getAdjWords(currWord);
          
          for (String adjWord : currAdjWords) {
              if (wordSet.contains(adjWord)) { //check if wordSet contains the adjacent word
                  List<String> pathCopy = new ArrayList<>(currPath);
                  pathCopy.add(adjWord);
                  
                  if (adjWord.equals(endWord)) {
                      minDepth = currDepth;
                      ans.add(pathCopy);
                  } else {
                      visitedThisLevel.add(adjWord);
                      pathsQ.addLast(pathCopy);
                  }
              }
          }
      }
      return ans;
  }
}