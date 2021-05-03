package arrays;
import java.util.HashMap;
import java.util.Map;

public class twoSum {
  public static int[] solution(int[] nums, int target) {
      int [] ans = new int[]{0,0};
      Map <Integer, Integer> complementMap = new HashMap();
      for (int i=0 ; i<nums.length ; i++) {
          int currNum = nums[i];
          int complement = target - currNum;
          if (complementMap.containsKey(complement)) {
              return new int[]{i, complementMap.get(complement)};
          } else {
              complementMap.put(currNum, i);
          }
      }
      return ans;
  }
  

  public static void main(String[] args) {
    int [] input = new int[]{2,7,11,15};
    int [] ans = solution(input, 9);
    System.out.println(ans[0] + ", " + ans[1]);
  }
}

