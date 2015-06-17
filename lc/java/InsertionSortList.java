package leetcode;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        ListNode cur = head;
        ListNode temp = fakeHead;
        while(cur != null){
            ListNode next = cur.next;
            ListNode pre = fakeHead;
            temp = fakeHead.next;
            while(temp != null && temp.val < cur.val){
                pre = temp;
                temp = temp.next;
            }
            pre.next = cur;
            cur.next = temp;
            cur = next;
        }
        return fakeHead.next;
    }
	 public ListNode insertionSortList1(ListNode head) {
	        if(head == null) return null;
	        ListNode cur = head.next;
	        ListNode preCur = head;
	        while(cur != null){
	            ListNode next = cur.next;
	            if(cur.val >= preCur.val){
	                preCur = cur;
	            }else if(cur.val <= head.val){
	                cur.next = head;
	                head = cur;
	                preCur.next = next;
	            }else{
	                ListNode insertPre = head;
	                ListNode temp = head.next;
	                while(temp != null && temp.val < cur.val){
	                    insertPre = temp;
	                    temp = temp.next;
	                }
	                insertPre.next = cur;
	                cur.next = temp;
	                preCur.next = next;
	            }
	            cur = next;
	        }
	        return head;
	    }
}
