package linked_lists;

public class reverseLinkedList {
  public static ListNode solutionIter (ListNode head) {
    ListNode prev = null;
        
    while(head != null){
        ListNode temp = head.next;
        head.next = prev;
        prev = head; 
        head = temp;
    }
    return prev;
  }

  public static ListNode solutionRecur (ListNode head) {
    if (head == null) return head;
    if (head.next == null) return head;
    ListNode subReverse = solutionRecur(head.next);
    ListNode ansPtr = subReverse;
    
    ListNode tempNext = head.next;
    head.next = null;
    
    tempNext.next = head;
    return ansPtr;
  }
}
