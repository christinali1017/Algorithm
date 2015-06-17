package leetcode;
public class LinkedListCycle_1 {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode f = head;
        ListNode s = head;
        while(f != null){
            f = f.next;
            if(f != null) f = f.next;
            s = s.next;
            if(f == null) return false;
            if(f.equals(s)) return true;
        }
        return false;
    }
    
	public static void main(String[] args) {
		LinkedListCycle_1 l = new LinkedListCycle_1();
		ListNode ln = new ListNode(2);
		ln.next = new ListNode(1);	
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(5);
		ln.next.next.next.next.next = ln.next.next;
		System.out.println(l.hasCycle(ln));
		
	}

}
