package leetcode;
public class ReorderList {
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode copy = new ListNode(-1);
        int count = 0;
        ListNode temp = head;
        while(temp != null){
            ListNode current = new ListNode(temp.val);
            current.next = copy;
            copy = current;
            temp = temp.next;
            count++;
        }
        if(count <= 2) return;
        temp = head;
        ListNode next = null, copyNext = null, pre = null;
        for(int i = 0; i < count/2; i++){
            next = temp.next;
            copyNext = copy.next;
            temp.next = copy;
            copy.next = next;
            pre = copy;
            temp = next;
            copy = copyNext;
        }
        if(count % 2 != 0) temp.next = null;
        else pre.next = null;
    }
	
    
    public void reorderList1(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode f = head;
        ListNode s = head;
        ListNode pre = null;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            pre = s;
            s = s.next;
        }
        pre.next = null;
        ListNode lastHalf = null;
        ListNode next = null;
        while(s != null){
        	next = s.next;
            if(lastHalf == null) {
            	lastHalf = s;
            	lastHalf.next = null;
            }else{
                s.next = lastHalf;
                lastHalf = s;
            }
            s = next;
        }
        ListNode temp = head;
        ListNode lastHalfNext = null;
        while(temp != null && lastHalf != null){
            next = temp.next;
            lastHalfNext = lastHalf.next;
            temp.next = lastHalf;
            lastHalf.next = next;
            temp = next;
            lastHalf = lastHalfNext;
        }
    }
	public static void main(String[] args) {
		ReorderList r = new ReorderList();
		ListNode ln = new ListNode(2);
		ln.next = new ListNode(1); 
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(5); 
		r.reorderList1(ln);
		
		while(ln != null){
			System.out.println(ln.val);
			ln = ln.next;
		}
	}

}
