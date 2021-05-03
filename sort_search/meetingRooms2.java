class Solution {
  // sort by start time
  // make priority queue and stores all end times
  // iterate through intervals:
  //  @ each iteration, if current's end time is greater than earliest end time, remove that from the             priority queue. then add the new end time to queue. 
  // @ end return priority queue's size.
  
  public int minMeetingRooms(int[][] intervals) {
      if (intervals.length == 0) return 0;
      
      Arrays.sort(intervals, new Comparator<int[]> () {
          public int compare (int[] intervalA, int[] intervalB) {
              return intervalA[0] - intervalB[0];
          }
      });
      
      PriorityQueue <Integer> endTimes = new PriorityQueue <Integer> (
          intervals.length, 
          new Comparator<Integer> () {
              public int compare (Integer a, Integer b) {
                  return a - b;
              }
          }
      );
      
      endTimes.add(intervals[0][1]);
      for(int i=1 ; i<intervals.length ; i++){
          if(intervals[i][0] >= endTimes.peek()){
              endTimes.poll();
          }
          endTimes.add(intervals[i][1]);
      }
      
      return endTimes.size();
  }
}