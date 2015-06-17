package leetcode;
public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    if(l1 == null) return l2;
	    if(l2 == null) return l1;
	    ListNode fakeHead = new ListNode(-1);
	    ListNode temp = fakeHead;
	    while(l1 != null && l2 != null){
	        if(l1.val > l2.val){
	            temp.next = l2;
	            l2 = l2.next;
	        }else{
	            temp.next = l1;
	            l1 = l1.next;
	        }
	        temp = temp.next;
	    }
	    if(l1 != null) temp.next = l1;
	    if(l2 != null) temp.next = l2;
	    return fakeHead.next;
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
