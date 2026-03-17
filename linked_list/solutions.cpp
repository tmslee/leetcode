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

// 21 merge two sorted lists
ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    ListNode dummy(0);
    ListNode* curr = &dummy;
    while(list1 && list2) {
        if(list1->val < list2->val) {
            curr->next = list1;
            list1 = list1->next;
        } else {
            curr->next = list2;
            list2 = list2->next;
        }
        curr = curr->next;
    }
    curr->next = list1 ? list1 : list2;
    return dummy.next;
}

// 23 merge k sorted lists
ListNode* mergeKLists(const std::vector<ListNode*>& lists) {
    ListNode dummy(0);
    ListNode* curr = &dummy;

    auto cmp = [](ListNode* l1, ListNode* l2) {
        return l1->val > l2->val;
    };
    std::priority_queue<ListNode*, vector<ListNode*>, decltype(cmp)> pq(cmp);
    for(auto* list : lists) {
        if(list) pq.push(list);
    }

    while(!pq.empty()) {
        auto* node = pq.top();
        pq.pop();
        curr->next = node;
        if(node->next) pq.push(node->next);
        curr = curr->next;
    }
    return dummy.next;
}

//25 reverse nodes in k-group
ListNode* reverseKGroup(ListNode* head, int k) {
    ListNode* ans = nullptr;
    ListNode* curr = head;
    ListNode* start = head;
    ListNode* prev_end = nullptr;
    int cnt = 1;
    
    auto reverse = [](this auto&& self, ListNode* head) -> ListNode*{
        if(!head || !head->next) return head;
        ListNode* nxt = head->next;
        ListNode* newHead = self(nxt);
        nxt->next = head;
        return newHead;
    };

    while(curr) {
        ListNode* nxt = curr->next;
        if(cnt == k) {
            curr -> next = nullptr;                                 
            cnt = 0;
            ListNode* r_head = reverse(start);
            if(prev_end) {
                prev_end -> next = r_head; //prev end's next is the reversed head
            } else  {
                ans = r_head;
            }
            prev_end = start; //start is the new prev_end
            prev_end -> next = nxt; //new prev_end's next is nxt;
            start = nxt; //new start is nxt
        }
        curr = nxt;
        ++cnt;
    }
    return ans ? ans : head;
}