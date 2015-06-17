package leetcode;
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if(n < 0 || head == null) return head;
        ListNode temp = head;
        int len = 0;
        ListNode tail = null;
        while(temp != null){
            len++;
            tail = temp;
            temp = temp.next;
        }
        n = n % len;
        len = len - n;
        if(n == 0 || len == 0) return head;
        temp = head;
        ListNode pre = null;
        while(len > 0){
            pre = temp;
            temp = temp.next;
            len--;
        }
        pre.next = null;
        tail.next = head;
        return temp;
    }
    
    public static void main(String[] args) {
    	RotateList r = new RotateList();
		ListNode ln = new ListNode(1);
		ln.next = new ListNode(2);	
		ln.next.next = new ListNode(3); 
    	ListNode result = r.rotateRight(ln, 4);
    	
    	while(result != null){
    		System.out.print(result.val + "->");
    		result = result.next;
    	}
	}

}
