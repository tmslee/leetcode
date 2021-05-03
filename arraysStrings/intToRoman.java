package arrays;

public class intToRoman {
  public static String getRomanStr(int quotient, int div){
    char one = 'I';
    char five = 'V';
    char ten = 'X';
        
    switch(div) {
        case 1000:
            one = 'M';
            break;
        case 100:
            one = 'C';
            five = 'D';
            ten = 'M';
            break;
        case 10:
            one = 'X';
            five = 'L';
            ten = 'C';
            break;
        case 1:
            one = 'I';
            five = 'V';
            ten = 'X';
            break;
    }
    
    String ans = "";
    
    if(quotient >= 5){
        if(quotient == 9){
            return ans + one + ten;
        } else {
            int rem = quotient-5;
            ans += five;
            for (int i=0 ; i<rem ; i++) ans += one;
            return ans;
        }
    } else {
        if(quotient == 4){
            return ans + one + five;
        } else {
            for (int i=0 ; i<quotient ; i++) ans += one;
            return ans;
        }         
    }

  }

  public static String solution(int num) {
      String ans = "";
      while(num > 0) {
          if(num >= 1000){
              int quotient = num/1000;
              ans = ans + getRomanStr(quotient, 1000);
              num %= 1000;
          } else if (num >= 100) {
              int quotient = num/100;
              ans = ans + getRomanStr(quotient, 100);
              num %= 100;
          } else if (num >= 10) {
              int quotient = num/10;
              ans = ans + getRomanStr(quotient, 10);
              num %= 10;
          } else {
              int quotient = num;
              ans = ans + getRomanStr(quotient, 1);
              num = 0;
          }
      }
      return ans;
  }

  public static void main(String[] args){
    int input = 3;
    System.out.println(solution(input));
  }
}
