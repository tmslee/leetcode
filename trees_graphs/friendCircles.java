class Solution {
  //count 0 indegrees after going through top half of matrix 
  public void bfs(int curr, int[] visited, int[][] isConnected){
      Queue<Integer> q = new LinkedList();
      q.add(curr);
      while(!q.isEmpty()) {
          int currNode = q.poll();
          visited[currNode] = 1;
          for(int i=0 ; i<isConnected.length ; i++){
              if(i!= currNode && isConnected[currNode][i] == 1 && visited[i] == 0) q.add(i);
          }
      }
  }
  
  public int findCircleNum(int[][] isConnected) {
      int[] visited = new int[isConnected.length];
      
      int ans = 0;
      for(int i=0 ; i<isConnected.length ; i++){
          if(visited[i] == 0) {
              ans++;
              bfs(i, visited, isConnected);
          }
      }
      return ans;
  }
}