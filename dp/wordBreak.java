class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
      Set<String> words = new HashSet<String>(wordDict);
      boolean[] mem = new boolean[s.length() + 1];
      
      //mem to store if s.substring(0, index) can be separated
      mem[0] = true; 
      
      
      for(int end=1 ; end<=s.length() ; end++) {
          for(int partition=0 ; partition<end ; partition++){
              // if s.substring(0, partition) can be separated and s.substring(partition, end) is a word in dict
              // we know taht s.substring(0, end) can be separated.
              
              if(mem[partition] && words.contains(s.substring(partition, end))) {
                  mem[end] = true;
                  break;
              }
          }
      }
      return mem[s.length()];
  }
}