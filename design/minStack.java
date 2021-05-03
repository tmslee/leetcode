// make stack of 2 values as array: (value and min value)

class MinStack {
  LinkedList <int[]> minStack;
  
  /** initialize your data structure here. */
  public MinStack() {
      this.minStack = new LinkedList<int[]>();
  }
  
  public void push(int val) {
      int min = getMin();
      this.minStack.push(new int[]{val, Math.min(min, val)});
  }
  
  public void pop() {
      if (this.minStack.size() > 0){
          this.minStack.pop();    
      }
  }
  
  public int top() {
      if (this.minStack.size() > 0){
          return this.minStack.peek()[0];   
      } else {
          return -1;
      }
  }
  
  public int getMin() {
      if (this.minStack.size() > 0){
          return this.minStack.peek()[1];            
      } else {
         return Integer.MAX_VALUE; 
      }
  }
}