class Solution {
  Map <Integer, Double> distMap = new HashMap <Integer, Double>();
  
  public double calcDist (int[] coord) {
      return Math.sqrt(Math.pow(coord[0], 2) + Math.pow(coord[1], 2));
  }
  
  public void swap (int[] indexes, int i, int j) {
      int temp = indexes[i];
      indexes[i] = indexes[j];
      indexes[j] = temp;
  }
  
  public int partition (Map<Integer, Double> distMap, int[] indexes, int start, int end){
      double target = distMap.get(indexes[end]);
      int swapIdx = start;
      for (int i=start ; i<=end; i ++) {
          if (distMap.get(indexes[i]) < target) {
              swap(indexes, i, swapIdx); 
              swapIdx++;
          }
      }
      swap(indexes, swapIdx, end);
      return swapIdx;
  }
  
  public int kClosestHelper(Map<Integer, Double> distMap, int[] indexes, int start, int end, int k) {
      if(start == end) return indexes[start];
      
      int partitionIdx = partition(distMap, indexes, start, end);
      
      if (partitionIdx == k) return indexes[partitionIdx];
      else if (partitionIdx < k) {
          return kClosestHelper(distMap, indexes, partitionIdx + 1, end, k);
      } else {
          return kClosestHelper(distMap, indexes, start, partitionIdx - 1, k);
      }
  }
  
  public int[][] kClosest(int[][] points, int k) {
      int[][] ans = new int[k][2];
      
      int[] indexes = new int[points.length];
      
      for(int i=0 ; i<points.length ; i++){
          indexes[i] = i;
          distMap.put(i, calcDist(points[i]));
      }
      
      int idx = kClosestHelper(distMap, indexes, 0, points.length-1, k-1);

      for(int i=0 ; i<k ; i++) {
          ans[i] = points[indexes[i]];
      }
      
      return ans;
  }
}