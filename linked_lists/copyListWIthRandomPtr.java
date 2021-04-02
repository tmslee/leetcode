package linked_lists;

public class copyListWIthRandomPtr {
  public static Node solution (Node head) {
    Node ansPtr = new Node(0);
    Node currNode = ansPtr;
    while (head != null) {
        Node newNode = new Node(head.val);
        newNode.random = head.random;
        
        Node tempNext = head.next;
        head.next = newNode;
        head = tempNext;

        currNode.next = newNode;
        currNode = currNode.next;
    }
    
    currNode = ansPtr.next;
    while(currNode != null){
        System.out.println("curr val is: " + currNode.val);
        if(currNode.random != null) {
            // System.out.println(currNode.random.val);
            // System.out.println(currNode.random.next.val);
            currNode.random = currNode.random.next;
            System.out.println("random val is: " + currNode.random.val);
        } else {
            System.out.println("random val is: null");
        }
        currNode = currNode.next;
    }
    return ansPtr.next;
  }
}
