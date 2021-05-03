package arrays;
import java.util.HashMap;
import java.util.Map;

public class longestWithoutRepeat {
  public static int solution(String s) {        
    // save encountered char, encountered index +1
    // when we encounter character we've seen before update left idx as index stored in max
    // at every iteration update maxlen using right-left+1
    
    int n = s.length();
    int ans = 0;        
    Map <Character, Integer> map = new HashMap();
    
    for (int j=0, i=0 ; j<n ; j++) {
        if (map.containsKey(s.charAt(j))) {
           i = Math.max(map.get(s.charAt(j)), i); 
        }
        ans = Math.max(ans, j-i+1);
        map.put(s.charAt(j), j+1);
    }
    return ans;
  }

  public static void main(String[] args) {
    String input = "abcabcbb";
    int ans = solution(input);
    System.out.println(ans);
  }
}
