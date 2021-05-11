class Solution {
  //split by dot
  //remove trailing zeros in array
  //remove leading zeros in elements
  
  public List<String> process (String str) {
      List<String> list = new ArrayList<String>(Arrays.asList(str.split("\\.")));
      while(!list.isEmpty() && Integer.parseInt(list.get(list.size()-1)) == 0){
          list.remove(list.size()-1);
      }
      return list;
  }
  
  public int compareVersion(String version1, String version2) {
      List<String> list1 = process(version1);
      List<String> list2 = process(version2);
      
      int idx = 0;
      
      while(idx < list1.size() && idx < list2.size()){
          int currVal1 = Integer.parseInt(list1.get(idx));
          int currVal2 = Integer.parseInt(list2.get(idx));
          
          if(currVal1 > currVal2) return 1;
          else if(currVal1 < currVal2) return -1;
          
          idx++;
      }
      
      if(list1.size() > list2.size()) return 1;
      else if (list1.size() < list2.size()) return -1;
      else return 0;
  }
}