package leetcode;

public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode pre = head;
        ListNode temp = head.next;
        while(temp != null){
            if(temp.val == pre.val) {
                pre.next = temp.next;
                temp = temp.next;
            }else{
                pre = temp;
                temp = temp.next;
            }
        }
        return head;
    }
    public static void main(String[] args) {
		ListNode ln = new ListNode(1);
		ln.next = new ListNode(1);	
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(3);
		RemoveDuplicatesFromSortedList r = new RemoveDuplicatesFromSortedList();
		
		ListNode head = r.deleteDuplicates(ln);
		while(head != null){
			System.out.print(head.val+"->");
			head = head.next;
			
		}
		
	}
}
