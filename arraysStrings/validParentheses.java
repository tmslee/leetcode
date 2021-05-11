class Solution {
  public boolean isValid(String s) {
      Map<Character, Character> matchMap = new HashMap<>();
      matchMap.put('(', ')');
      matchMap.put('[', ']');
      matchMap.put('{', '}');
      
      LinkedList <Character> lBracketStack = new LinkedList<>();
      
      for(int i=0 ; i<s.length() ; i++){
          char currC = s.charAt(i);
          if (matchMap.containsKey(currC)) lBracketStack.addFirst(currC);
          else {
              if(!lBracketStack.isEmpty() && matchMap.get(lBracketStack.getFirst()) == currC) {
                  lBracketStack.removeFirst();
              } else {
                  return false;
              }
          }
      }
      if(lBracketStack.isEmpty()) return true;
      else return false;
  }
}