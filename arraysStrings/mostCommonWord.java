class Solution {
  public String mostCommonWord(String paragraph, String[] banned) {
      Map<String, Integer> countMap = new HashMap<>(); 
      Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
      
      paragraph = paragraph.toLowerCase();
      String[] words = paragraph.split("[\\W]");
      String ans = "";
      int maxCount = 0;
      for (String word : words) {
          if(word.length() > 0 && !bannedSet.contains(word)) {
              int count = countMap.getOrDefault(word, 0);
              count++;
              if(count > maxCount) {
                  maxCount = count;
                  ans = word;
              }
              countMap.put(word, count);
          }
           
      }
      return ans;
  }
}