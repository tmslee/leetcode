class Solution {
  public ListNode merge2Lists(ListNode head1, ListNode head2) {
      if(head1 == null) return head2;
      if(head2 == null) return head1;
      
      ListNode ansPtr = new ListNode(0);
      ListNode curr = ansPtr;
      while(head1!=null || head2!=null){
          if(head1==null) {
              curr.next = head2;
              return ansPtr.next;
          } 
          if(head2==null) {
              curr.next = head1;
              return ansPtr.next;
          }
          if(head1.val <= head2.val) {
              curr.next = head1;
              head1=head1.next;
              curr=curr.next;
          } else {
              curr.next = head2;
              head2=head2.next;
              curr=curr.next;
          }
      }
      return ansPtr.next;
  }
  
  public ListNode mergeKLists(ListNode[] lists) {
      if(lists.length == 0) return null;
      
      int interval = 1;
      while (interval < lists.length) {
          for (int i=0 ; i<lists.length ; i+=interval*2) {
              lists[i] = merge2Lists(lists[i], i+interval < lists.length ? lists[i+interval] : null);
          }
          interval*=2;
      }
      return lists[0];
  }
}