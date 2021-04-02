package trees_graphs;

public class numIsland {
  public int solution(char[][] grid) {
    if(grid.length == 0 || grid[0].length == 0) return 0;
    int ans = 0;
    for(int r=0 ; r<grid.length ; r++){
        for(int c=0; c<grid[0].length ; c++){
            if(grid[r][c] == '1') {
                expandIsland(grid, r, c);
                ans++;
            }
        }
    }
    return ans;
  }

  public void expandIsland(char[][] grid, int r, int c) {
      if(grid[r][c] == '1') {
          grid[r][c] = '0';
          int [] rOffset = new int[]{0, 1, 0, -1};
          int [] cOffset = new int[]{-1, 0, 1, 0};
          for(int i=0 ; i<4 ; i++){
              int newR = r+rOffset[i];
              int newC = c+cOffset[i];
              if(isValid(grid, newR, newC)) expandIsland(grid, newR, newC);
          }
      }
  }

  public boolean isValid(char[][] grid, int r, int c){
      if(r<0 || r >= grid.length || c<0 || c>= grid[0].length) return false;
      return true;
  }
}
