package leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.

	For example,
	Given 1->2->3->4, you should return the list as 2->1->4->3.
	
	Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * @author wish
 *
 */
public class SwapNodesInPairs {
  public ListNode swapPairs(ListNode head) {
	    if(head == null || head.next == null) return head;
	    ListNode fakeHead = new ListNode(-1);
	    ListNode temp = fakeHead;
	    while(head != null && head.next != null){
	        ListNode nextnext = head.next.next;
	        temp.next = head.next;
	        temp.next.next = head;
	        temp = head;
	        head = nextnext;
	    }
	    temp.next = head;
	    return fakeHead.next;
	  }
    
    public static void main(String[] args) {
    	SwapNodesInPairs s = new SwapNodesInPairs();
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	head.next.next.next.next = new ListNode(5);
    	ListNode result = s.swapPairs(head);
    	while(result != null){
    		System.out.println(result.val);
    		result = result.next;
    	}
	}

}
