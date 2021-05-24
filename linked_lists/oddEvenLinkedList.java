/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
  public ListNode oddEvenList(ListNode head) {
      if(head == null) return null;
      ListNode odd = head;
      ListNode even = head.next;
      ListNode evenHead = even;
      if(even == null) return head;
      ListNode current = even.next;
      boolean isEven = false;
      while(current != null) {
          if(isEven) {
              even.next = current;
              even = even.next;
          } else {
              odd.next = current;
              odd = odd.next;
          }
          current = current.next;
          isEven = !isEven;
      }
      even.next = null;
      odd.next = evenHead;
      return head;
  }
}