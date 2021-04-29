class Solution {
  public int[][] merge(int[][] intervals) {
      Arrays.sort(intervals, new Comparator<int[]> (){
          public int compare (int[] interval1, int[] interval2) {
              if(interval1[0] == interval2[0]) return Integer.compare(interval1[1], interval2[1]);
              else return Integer.compare(interval1[0], interval2[0]);
          }
      });
      List<int[]> ans = new ArrayList<int[]> ();
      for (int[] interval : intervals) {
          if (ans.isEmpty()) ans.add(interval);
          else {
              int[] last = ans.get(ans.size() - 1);
              if (interval[0] > last[1]) ans.add(interval);
              else {
                  last[1] = Math.max(last[1], interval[1]);
                  ans.set(ans.size() - 1, last);
              }
          }
      }
      return ans.toArray(new int[ans.size()][]);
  }
}