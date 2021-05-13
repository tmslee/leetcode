/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

//KEY THINGS: 
//  start with origin
//  GO BACK
//  CALCULATE NEW COORD BASED ON DIRECTION
 

class Solution {
  Robot robot;
  Set<Pair<Integer, Integer>> visited = new HashSet();
  int[][] directions = {{-1, 0},{0, 1},{1, 0},{0, -1}};
  
  public void goBack() {
      robot.turnRight();
      robot.turnRight();
      robot.move();
      robot.turnRight();
      robot.turnRight();
  }
  
  public void backtrack(int r, int c, int d) {
      visited.add(new Pair(r,c));
      robot.clean();
      for(int i=0 ; i<4 ; i++){
          int newDir = (d+i)%4;
          int newR = r + directions[newDir][0];
          int newC = c + directions[newDir][1];
          if (!visited.contains(new Pair(newR, newC)) && robot.move()){
              backtrack(newR, newC, newDir);
              goBack();
          }
          robot.turnRight();
      }
  }
  
  public void cleanRoom(Robot robot) {
      this.robot = robot;
      backtrack(0, 0, 0);
  }
}