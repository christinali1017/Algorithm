package leetcode;

public class SortList{
	   public ListNode sortList(ListNode head){
		    if(head == null || head.next == null) return head;
		    ListNode f = head;
		    ListNode s = head;
		    ListNode pre = null;
		    while(f != null){
		        f = f.next;
		        if(f != null){
		            f = f.next;
		            pre = s;
		            s = s.next;
		        }
		    }
		    pre.next = null;
		    return merge(sortList(s), sortList(head));
		}
		
		public ListNode merge(ListNode l1, ListNode l2){
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
		SortList sl = new SortList();
		ListNode ln = new ListNode(2);
		ln.next = new ListNode(1); 
		ln.next.next = new ListNode(8); 
		ln.next.next.next= new ListNode(3); 
		ln.next.next.next.next= new ListNode(5); 
		ListNode result = sl.sortList(ln);
		while(result != null){
			System.out.println(result.val + "->");
			result = result.next;
		}
	}

}
