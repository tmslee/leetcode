/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    
  public Node[] flattenHelper(Node head) {
      if(head == null) return new Node[]{null, null};
      
      Node headPtr = head;
      Node tail = head;
      while(head != null) {
          if(head.child != null){
              Node tempNext = head.next;
              Node[] flattenedChild = flattenHelper(head.child);
              head.next = flattenedChild[0];
              
              flattenedChild[0].prev = head;
              flattenedChild[1].next = tempNext;
  
              if(tempNext != null) tempNext.prev = flattenedChild[1];
              head.child = null;
              head = tempNext;
              
          } else head = head.next;
          
          if(head != null) tail = head;
      }

      while(tail.next != null) tail = tail.next;
      return new Node[]{headPtr, tail};
  }
  
  public Node flatten(Node head) {
      if(head == null) return null;
      Node[] ansHeadTail = flattenHelper(head);
      return ansHeadTail[0];
  }
}