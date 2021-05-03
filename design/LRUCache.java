class DLLNode {
  DLLNode prev = null;
  DLLNode next = null;
  int key;
  int value;
  
  public DLLNode(){}
}

class LRUCache {
  //use doubly linked list
  private Map <Integer, DLLNode> DLLMap;
  private int cap; 
  private DLLNode head;
  private DLLNode tail;
  
  private void addNode (DLLNode node) {
      DLLNode temp = this.head.next;
      this.head.next = node;
      node.next = temp;
      node.prev = this.head;
      temp.prev = node;
      
      this.DLLMap.put(node.key, node);
  }
  
  private void removeNode (DLLNode node) {
      DLLNode prev = node.prev;
      DLLNode next = node.next;
      prev.next = next;
      next.prev = prev;
      
      this.DLLMap.remove(node.key);
  }
  
  private void moveToFirst (DLLNode node) {
      removeNode(node);
      addNode(node);
  }
  
  private void removeLast() {
      removeNode(this.tail.prev);
  }
  
  public LRUCache(int capacity) {
      this.DLLMap = new HashMap<Integer, DLLNode>();
      this.cap = capacity;
      this.head = new DLLNode();
      this.tail = new DLLNode();
      this.head.next = this.tail;
      this.tail.prev = this.head;
  }
  
  public int get(int key) {
      DLLNode node = this.DLLMap.getOrDefault(key, null);
      if (node == null) return -1;
      moveToFirst(node);
      return node.value;
  }
  
  public void put(int key, int value) {
      if (this.DLLMap.containsKey(key)) {
          DLLNode node = this.DLLMap.get(key);
          node.value = value;
          moveToFirst(node);
      } else {
          DLLNode node = new DLLNode();
          node.key = key;
          node.value = value;
          addNode(node);
          if (this.DLLMap.size() > this.cap) removeLast();   
      }
  }
}