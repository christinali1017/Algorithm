package leetcode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        ListNode feakHead = new ListNode(-1);
        ListNode h = feakHead;
        ListNode tail = feakHead;
        while(head != null){
            if(head.val >= x){
                tail.next = head;
                head = head.next;
                tail = tail.next;
                tail.next = null;
            }else{
                 ListNode saveH = h;
                 ListNode temp = h.next;
                 h.next = head;
                 head = head.next;
                 h = h.next;
                 h.next = temp;
                if(tail.equals(saveH)) tail = h;
            }
        }
        return feakHead.next;
    }
    
    public static void main(String[] args) {
    	PartitionList p = new PartitionList();
    	ListNode ln = new ListNode(1);
		ln.next = new ListNode(4);	
		ln.next.next = new ListNode(3); 
		ln.next.next.next= new ListNode(2); 
		ln.next.next.next.next= new ListNode(5);
		ln.next.next.next.next.next= new ListNode(2);
		ListNode result = p.partition(ln, 3);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
			
		}
	}
    
}
