package leetcode;
public class RemoveNthNodeFromEndOfList {
	/* one pass */
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null) return null;
    	ListNode l1 = head;
    	ListNode l2 = head;
    	while(n > 0){
    	    l1 = l1.next;
    	    n--;
    	}
    	if(l1 == null) return head.next;
    	ListNode pre = null;
    	while(l1 != null){
    	    pre = l2;
    	    l2 = l2.next;
    	    l1 = l1.next;
    	}
    	pre.next = l2.next;
    	return head;
    }
    
    /*two pass*/
    public ListNode removeNthFromEnd1(ListNode head, int n) {
    	if(head == null) return null;
    	ListNode temp = head;
    	int count = 0;
    	while(temp != null){
    		count++;
    		temp = temp.next;
    	}
    	
    	int k = count-n;
    	temp = head;
    	ListNode pre = null;
    	while(k > 0){
    		pre = temp;
    		temp = temp.next;
    		k--;
    	}
    	
    	if(pre == null) return temp.next;
    	pre.next = temp.next;
    	return head;
        
    }
    
    public static void main(String[] args) {
    	RemoveNthNodeFromEndOfList r = new RemoveNthNodeFromEndOfList();
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	head.next.next.next.next = new ListNode(5);
    	ListNode result = r.removeNthFromEnd(head, 3);
    	while(result != null){
    		System.out.println(result.val);
    		result = result.next;
    	}
	}
    

}
