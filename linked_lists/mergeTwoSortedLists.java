package linked_lists;

public class mergeTwoSortedLists {
  public static ListNode solution (ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    else if (l2 == null) return l1;
    
    ListNode ans = new ListNode();
    ListNode ansPtr = ans;
    
    while (l1 != null && l2 != null) {
        if(l1.val <= l2.val) {
            ans.next = l1;
            l1 = l1.next;
        } else {
            ans.next = l2;
            l2 = l2.next;
        }
        ans = ans.next;
    }
    if(l1 != null) ans.next = l1;
    else if(l2 != null) ans.next = l2;
    return ansPtr.next;
  }
}
