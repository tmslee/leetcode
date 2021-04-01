package arrays;
import java.util.Map;
import java.util.HashMap;

public class romanToInt {
  public static int solution(String s) {
    Map <Character, Integer> intMap = new HashMap();
    intMap.put('I', 1);
    intMap.put('V', 5);
    intMap.put('X', 10);
    intMap.put('L', 50);
    intMap.put('C', 100);
    intMap.put('D', 500);
    intMap.put('M', 1000);    
    
    int ans = 0;
    char lastSeen = ' ';
    boolean sub = false;
    
    for (int i=s.length()-1 ; i>=0 ; i--) {
        char currChar = s.charAt(i);
        int currInt = intMap.get(currChar);
        
        if (currChar == 'I' && (lastSeen == 'V' || lastSeen == 'X')) sub = true;
        else if (currChar == 'X' && (lastSeen == 'L' || lastSeen == 'C')) sub = true;
        else if (currChar == 'C' && (lastSeen == 'D' || lastSeen == 'M')) sub = true;
        else sub = false;
        
        if(!sub) ans += currInt;
        else ans -= currInt;
        
        lastSeen = currChar;
    }
    return ans;
  }
  public static void main (String[] args) {
    String input = "III";
    System.out.println(solution(input));
  }
}
