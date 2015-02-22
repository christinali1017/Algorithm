package leetcode;
public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        ListNode head1 = new ListNode(-1);
        ListNode h1 = head1;
        while(l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                 if(l1.val < l2.val){
                     h1.next = l1;
                    l1 = l1.next;
                }else{
                    h1.next = l2;
                    l2 = l2.next;
                }
            }else if(l1 != null){
                h1.next = l1;
                l1 = l1.next;
            }else{
                h1.next = l2;
                l2 = l2.next;
            }
          h1 = h1.next;
        }
        return head1.next;
    }
    public static void main(String[] args) {
    	MergeTwoSortedList m = new MergeTwoSortedList();
		
		ListNode ln = null;
		
		ListNode list2 = new ListNode(0);
    	ListNode result = m.mergeTwoLists(ln, list2);
    	
    	while(result != null){
    		System.out.print(result.val + "->");
    		result = result.next;
    	}
	}
}
