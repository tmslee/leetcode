class Solution {
  public int trap(int[] height) {
      int[] maxLeft = new int[height.length];
      int[] maxRight = new int[height.length];
      
      for(int i=1 ; i<height.length ; i++){
          maxLeft[i] = Math.max(height[i-1], maxLeft[i-1]);    
      }
      for(int i=height.length-2 ; i>=0 ; i--){
          maxRight[i] = Math.max(height[i+1], maxRight[i+1]);    
      }
      
      int ans = 0;
      for(int i=0 ; i<height.length ; i++) {
          int currHeight = Math.min(maxLeft[i], maxRight[i]);
          if(currHeight > height[i]) ans += currHeight - height[i];
      }
      return ans;
  }
}