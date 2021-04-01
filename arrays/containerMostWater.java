package arrays;

public class containerMostWater {
  public static int solution(int[] height) {
    int left = 0;
    int right = height.length-1;
    int maxArea = 0;
    while(right > left) {
        int leftH = height[left];
        int rightH = height[right];
        int currArea = Math.min(rightH, leftH) * (right-left);
        maxArea = Math.max(maxArea, currArea);
        if (rightH < leftH) right--;
        else left++;
    }
    return maxArea;
  }

  public static void main(String[] args) {
    int[] input = new int[] {1,8,6,2,5,4,8,3,7};
    int ans = solution(input);
    System.out.println(ans);
  }
}
