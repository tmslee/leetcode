class MedianFinder {

  /** initialize your data structure here. */
  // have 2 Priority queues that keeps first half of data stream and second half of data stream
  // when we add a number we do it so that this split is maintianed
  PriorityQueue<Integer>leftHeap;
  PriorityQueue<Integer>rightHeap;
  boolean even = true;
  
  public MedianFinder() {
      leftHeap = new PriorityQueue<>();
      rightHeap = new PriorityQueue<>(1, new Comparator<Integer>(){
          public int compare(Integer e1, Integer e2){
              return e2.compareTo(e1);
          }
      });
  }
  
  public void addNum(int num) {
      if(even){
          rightHeap.add(num);
          leftHeap.add(rightHeap.poll());
      } else {
          leftHeap.add(num);
          rightHeap.add(leftHeap.poll());
      }
      
      even = !even;
  }
  
  public double findMedian() {
      if(even) return (leftHeap.peek() + rightHeap.peek())/2.0;
      else return (double) leftHeap.peek(); 
  }
}