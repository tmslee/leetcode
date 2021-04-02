package linked_lists;

public class reverseNodesInKGroup {
  public ListNode[] reverse (ListNode start, ListNode end) {        
    end.next = null;
    if(start.next == null) return new ListNode[] {start, start};
    ListNode [] subReverse = reverse(start.next, end);
    subReverse[1].next = start;
    start.next = null;
    subReverse[1] = start;
    return subReverse;
  }

  public ListNode solution (ListNode head, int k) {
      if(k == 1) return head;
      
      ListNode ansPtr = new ListNode();
      ansPtr.next = head;
      
      ListNode prev = ansPtr;
      ListNode start = head;
      ListNode end = head;
      while(end != null){
          int steps = 0;
          
          while(steps < k-1 && end.next != null) {
              steps ++;
              end = end.next;
          }
          
          if (steps == k-1) {
              ListNode tempStart = end.next;
              ListNode tempEnd = end.next;                
              ListNode[] reversed = reverse(start, end);
              
              prev.next = reversed[0];
              prev = reversed[1];
              reversed[1].next = tempStart;
              start = tempStart;
              end = tempEnd;
          } else{
              break;
          }
      }
      
      return ansPtr.next;
  }
}
