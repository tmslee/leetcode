package linked_lists;

public class addTwoNumbers {
  public static ListNode solution (ListNode l1, ListNode l2) {
    ListNode ans = new ListNode();
    ListNode ansPtr = ans; 
    int carry = 0;
    while (l1 != null || l2 != null) {
        int res = 0;
        if (l1 == null || l2 == null) {
            if (l1 == null){
                res = l2.val + carry;
                l2 = l2.next;
            } else {
                res = l1.val + carry;
                l1 = l1.next;
            }
        } else {
            res = l1.val + l2.val + carry;
            l1 = l1.next;
            l2 = l2.next;
        }
        carry = res/10;
        res %= 10;
        ListNode newNode = new ListNode(res);
        ans.next = newNode;
        ans = ans.next;
    }
    if(carry == 1) {
        ListNode newNode = new ListNode(carry);
        ans.next = newNode;
        ans = ans.next;
    }
    return ansPtr.next;
  }
}
