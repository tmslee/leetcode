// 2. add two numbers
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    ListNode dummy(0);
    ListNode* curr = &dummy;
    int carry = 0;
    while(l1 || l2 || carry) {
        int sum = carry;
        if(l1) {sum+=l1->val; l1=l1->next;}
        if(l2) {sum+=l2->val; l2=l2->next;}
        carry = sum/10;
        curr->next = new ListNode(sum%10);
        curr = curr->next;
    }
    return dummy.next;
}

// remove nth node from end of list
ListNode* removeNthFromEnd(ListNode* head, const int n) {
    ListNode dummy(0, head);
    ListNode* fast = &dummy;
    ListNode* slow = &dummy;

    for(int i=0; i<n; ++i) {
        fast = fast->next;
    }
    
    while(fast->next) {
        fast = fast->next;
        slow = slow->next;
    }

    ListNode* to_delete = slow->next;
    slow->next = to_delete->next;
    delete to_delete;

    return dummy.next;
}