package leetcode;

public class LinkedListCycle_2 {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode f = head;
        ListNode s = head;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            s = s.next;
            if(f == null) return null;
            if(f.equals(s)) break;
        }
        f = head;
        while(f != null){
            if(f.equals(s)) return s;
            f = f.next;
            s = s.next;
        }
        return null;
    }
    
    
    
	public static void main(String[] args) {
		LinkedListCycle_2 l = new LinkedListCycle_2();
		ListNode ln = new ListNode(2);
		ln.next = new ListNode(1);	
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(5);
		ln.next.next.next.next.next= new ListNode(6);
		ln.next.next.next.next.next.next= new ListNode(11);
		ln.next.next.next.next.next.next.next= new ListNode(12);
		ln.next.next.next.next.next.next.next.next= new ListNode(33);
		ln.next.next.next.next.next.next.next.next.next= ln.next.next.next.next.next.next;
		System.out.println(l.detectCycle(ln).val);
		
	}

}
