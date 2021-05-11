class Solution {
  int billion = 1000000000;
  int million = 1000000;
  int thousand = 1000;
  
  Map<Integer, String> suffixMap = new HashMap<>();
  Map<Integer, String> onesMap = new HashMap<>();
  Map<Integer, String> tensMap = new HashMap<>();
  Map<Integer, String> teensMap = new HashMap<>();
  
  public void populateMaps () {
      suffixMap.put(0, "");
      suffixMap.put(1, "Thousand");
      suffixMap.put(2, "Million");
      suffixMap.put(3, "Billion");
      
      onesMap.put(1, "One");
      onesMap.put(2, "Two");
      onesMap.put(3, "Three");
      onesMap.put(4, "Four");
      onesMap.put(5, "Five");
      onesMap.put(6, "Six");
      onesMap.put(7, "Seven");
      onesMap.put(8, "Eight");
      onesMap.put(9, "Nine");
      
      tensMap.put(2, "Twenty");
      tensMap.put(3, "Thirty");
      tensMap.put(4, "Forty");
      tensMap.put(5, "Fifty");
      tensMap.put(6, "Sixty");
      tensMap.put(7, "Seventy");
      tensMap.put(8, "Eighty");
      tensMap.put(9, "Ninety");
      
      teensMap.put(10, "Ten");
      teensMap.put(11, "Eleven");
      teensMap.put(12, "Twelve");
      teensMap.put(13, "Thirteen");
      teensMap.put(14, "Fourteen");
      teensMap.put(15, "Fifteen");
      teensMap.put(16, "Sixteen");
      teensMap.put(17, "Seventeen");
      teensMap.put(18, "Eighteen");
      teensMap.put(19, "Nineteen");
  }
  
  public String convertTriplet (int num) {
      StringBuilder ans = new StringBuilder();
      int hundreds = num/100;
      if(hundreds > 0) ans.append(" " + onesMap.get(hundreds) + " Hundred");
      num%=100;
      int tens = num/10;
      
      if(tens > 0 && tens < 2) {
          ans.append(" " + teensMap.get(num));
      } else {
          if(tens > 0) ans.append(" " + tensMap.get(tens));
          int ones = num%=10;
          if(ones > 0) ans.append(" " + onesMap.get(ones));
      }
      
      ans.deleteCharAt(0);
      return ans.toString();
  }
  
  public String numberToWords(int num) {
      if(num == 0) return "Zero";
      
      populateMaps();
      
      StringBuilder ans = new StringBuilder();
      int divCount = 0;
      while(num > 0){
          int val = num%1000;
          
          if(val > 0) {
              String triplet = convertTriplet(val);
              String suffix = suffixMap.get(divCount);
              String appendStr = suffix.length() == 0 ? triplet + " " : triplet + " " + suffix + " ";
              ans.insert(0, appendStr);
          }
          
          num /= 1000;
          divCount++;
      }
      
      ans.deleteCharAt(ans.length()-1);
      return ans.toString();
  }
}