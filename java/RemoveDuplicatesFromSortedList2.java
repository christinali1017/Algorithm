package leetcode;
public class RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode feakHead = new ListNode(-1);
        ListNode h1 = feakHead;
        int count = 1;
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                head = head.next;
                count++;
            }else{
                if(count == 1){
                    h1.next = head;
                    head = head.next;
                    h1 = h1.next;
                }else{
                    head = head.next;
                    count = 1;
                }
            }
        }
        if(count == 1) h1.next = head;
        else h1.next = null;
        return feakHead.next;
    }
    
    public static void main(String[] args) {
    	ListNode ln = new ListNode(1);
		ln.next = new ListNode(2);	
		ln.next.next = new ListNode(2); 
		ln.next.next.next= new ListNode(2); 
		ln.next.next.next.next= new ListNode(3);
		ln.next.next.next.next.next= new ListNode(3);
		RemoveDuplicatesFromSortedList2 r = new RemoveDuplicatesFromSortedList2();
		
		ListNode head = r.deleteDuplicates(ln);
		while(head != null){
			System.out.print(head.val+"->");
			head = head.next;
			
		}

	}
}
