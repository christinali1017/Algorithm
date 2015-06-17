package leetcode;
public class ReverseLinkedList_2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	if(head == null) return null;
    	if(m < 0) m = 0;
    	if(m > n){
    		int tempValue = m;
    		m = n;
    		n = tempValue;
    	}
    	
    	ListNode temp = head;
    	ListNode previousM = null;
    	
    	/* find node at m */
    	for(int i = 1; i < m && temp != null; i++){
    		previousM = temp;
    		temp = temp.next;
    	}
    	ListNode saveTemp = temp;
    	
    	/* reverse node between m and n */
    	ListNode pre = null;
    	ListNode next = null;
    	for(int i = m; i <= n && temp != null; i++){
    		next = temp.next;
    		temp.next = pre;
    		pre = temp;
    		temp = next;
    	}
    	
    	/* concatenate */
    	if(previousM != null) previousM.next = pre;
    	if(saveTemp != null) saveTemp.next = temp;
    	
    	if(m == 1) return pre; 
    	return head;
    }
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(head == null || head.next == null || (m == n)) return head;
        ListNode saveHead = head;
        ListNode preReverse = null;
        while(m-1 > 0){
            preReverse = head;
            head = head.next;
            m--;
            n--;
        }
        ListNode reverseHead = null;
        ListNode reverseTail = null;
        ListNode next = null;
        while(n-1 >= 0){
            n--;
            if(reverseHead == null){
                reverseHead = head;
                reverseTail = head;
                head = head.next;
                continue;
            }
            next = head.next;
            head.next = reverseHead;
            reverseHead = head;
            head = next;
        }
        if(preReverse != null) preReverse.next = reverseHead;
        if(reverseTail != null) reverseTail.next = head;
        if(preReverse == null) return reverseHead;
        return saveHead;
    }
    
    
    public static void main(String[] args) {
    	ReverseLinkedList_2 r = new ReverseLinkedList_2();
    	ListNode ln = new ListNode(2);
		ln.next = new ListNode(1);	
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(5);
    	ListNode result = r.reverseBetween(ln, 2, 5);
    	
    	while(result != null){
    		System.out.println(result.val);
    		result = result.next;
    	}
	}

}
