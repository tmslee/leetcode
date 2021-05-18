class Solution {
  public boolean isBipartite(int[][] graph) {
      int n = graph.length;
      int[] color = new int[n];
      Arrays.fill(color, -1);

      for (int start = 0; start < n; ++start) {
          if (color[start] == -1) {
              Queue<Integer> q = new LinkedList();
              q.add(start);
              color[start] = 0;

              while (!q.isEmpty()) {
                  Integer node = q.poll();
                  for (int nei: graph[node]) {
                      if (color[nei] == -1) {
                          q.add(nei);
                          color[nei] = color[node] ^ 1;
                      } else if (color[nei] == color[node]) {
                          return false;
                      }
                  }
              }
          }
      }

      return true;
  }
}