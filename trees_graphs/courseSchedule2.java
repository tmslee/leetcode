class Solution {
  // in degree: traverse through map and remove nodes with 0 in degrees.
  
  public int[] findOrder(int numCourses, int[][] prerequisites) {
      Map<Integer, Set<Integer>> courseMap = new HashMap();;
      int[] indegree = new int[numCourses];
      int[] ans = new int[numCourses];
      
      for (int[] prereq : prerequisites) {
          Set<Integer> children = courseMap.getOrDefault(prereq[1], new HashSet());
          children.add(prereq[0]);
          courseMap.put(prereq[1], children);
          
          indegree[prereq[0]] ++;
      }
      
      Queue<Integer> q = new LinkedList();
      for (int i=0 ; i<numCourses ; i++) {
          if(indegree[i] == 0) q.add(i);
      }
      
      int i=0;
      
      while(!q.isEmpty()) {
          int currCourse = q.remove();
          ans[i] = currCourse;
          i++;
          if(courseMap.containsKey(currCourse)) {
              for(Integer child : courseMap.get(currCourse)) {
                  indegree[child]--;
                  if(indegree[child] == 0) q.add(child);
              }
          }
      }
      
      if(i == numCourses) return ans;
      return new int[0];
  }
}