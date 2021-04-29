class TrieNode {
  Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;
  public TrieNode(){}
}

class Solution {
  
  public void insertToTrie(TrieNode root, String word) {
      TrieNode currentNode = root;
      for (int i=0 ; i<word.length() ; i++) {
          Character currChar = word.charAt(i);
          if (currentNode.children.containsKey(currChar)) {
              currentNode = currentNode.children.get(currChar);
          } else {
              TrieNode newNode = new TrieNode();
              currentNode.children.put(currChar, newNode);
              currentNode = newNode;
          }
      }
      currentNode.word = word;
  }
  
  public boolean isValid(int r, int c, char[][] board) {
      if (r<0 || r>=board.length || c<0 || c>=board[0].length) return false;
      return true;
  }
  
  public void backtrack(int r, int c, char[][] board, TrieNode parent, List<String> ans) {    
      Character currChar = board[r][c];
      TrieNode currNode = parent.children.get(currChar);
      
      if (currNode.word != null) {
          ans.add(currNode.word);
          currNode.word = null;
      }
      
      board[r][c] = '#';
      int [] rOffset = {-1, 1, 0, 0};
      int [] cOffset = {0, 0, -1, 1};
      
      for (int i=0 ; i<4 ; i++){
          int newR = r + rOffset[i];
          int newC = c + cOffset[i];
          if (isValid(newR, newC, board)) {
              Character newChar = board[newR][newC];
              if(currNode.children.containsKey(newChar)) {
                  backtrack(newR, newC, board, currNode, ans);
              }
          }
      }
      board[r][c] = currChar;
      if(currNode.children.isEmpty()) parent.children.remove(currChar);
      
  }
  
  public List<String> findWords(char[][] board, String[] words) {
      List<String> ans = new ArrayList<String>();
      
      if(board.length == 0 || board[0].length == 0) return ans; 
      
      int rows = board.length;
      int cols = board[0].length;
      
      TrieNode root = new TrieNode();
      for (String word : words) {
          insertToTrie(root, word);
      }
      
      for (int r=0 ; r<rows ; r++){
          for (int c=0 ; c<cols ; c++) {
              Character currChar = board[r][c];
              if (root.children.containsKey(currChar)) {
                  backtrack(r, c, board, root, ans);
              }
          } 
      }
      
      return ans;
  }
}