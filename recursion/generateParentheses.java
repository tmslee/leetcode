public class generateParentheses {
  public List<String> solution(int n) {
    StringBuilder curr = new StringBuilder();
    List<String> ans = new ArrayList <String>();
    backtrack(0, 0, n, ans, curr);
    return ans;
}

public void backtrack(int l, int r, int max, List<String> ans, StringBuilder curr) {
    if(curr.length() == max*2) {
        ans.add(curr.toString());
        return;
    }
    
    if(l < max) {
        curr.append("(");
        backtrack(l+1, r, max, ans, curr);
        curr.deleteCharAt(curr.length()-1);
    } 
    if(r < l) {
        curr.append(")");
        backtrack(l, r+1, max, ans, curr);
        curr.deleteCharAt(curr.length()-1);
    }
}
}
