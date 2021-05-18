class Solution {
  public int[] plusOne(int[] digits) {
      int carry = 0;
      int currDig = digits.length-1;
      
      do {
          int val = digits[currDig] +1;
          digits[currDig] = val%10;
          if(val >= 10) carry = 1;
          else carry = 0;
          currDig--;
      } while(carry != 0 && currDig >= 0);
          
      if(carry == 1) {
          int[] ans = new int[digits.length+1];
          ans[0] = 1;
          for(int i=0; i<digits.length ; i++) {
              ans[i+1] = digits[i];
          }
          return ans;
      }
      else return digits;
  }
}