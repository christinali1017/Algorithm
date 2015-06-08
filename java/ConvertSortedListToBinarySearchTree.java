package leetcode;
public class ConvertSortedListToBinarySearchTree {
	/**
	 * Bottom up, then we can visit the list in order. 
	 */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        ListNode[] h = new ListNode[1];
        h[0] = head;
        return sortedListToBST(h, 0, len - 1);
    }
    
    public TreeNode sortedListToBST(ListNode[] h, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode left = sortedListToBST(h, start, mid - 1);
        TreeNode root = new TreeNode(h[0].val);
        h[0] = h[0].next;
        root.left = left;
        root.right = sortedListToBST(h, mid + 1, end);
        return root;
    }
	 
		 
	/*top-down*/
 	public TreeNode sortedListToBST(ListNode head) {
	    if(head == null) {
	        return null;
	    }
	    if(head.next == null) {
	        return new TreeNode(head.val);
	    }
	    ListNode pre = getMidPre(head);
	    ListNode mid = pre.next;
	    pre.next = null;
	    TreeNode root= new TreeNode(mid.val);
	    root.left = sortedListToBST(head);
	    root.right = sortedListToBST(mid.next);
	    return root;
	 }
	 
	 public ListNode getMidPre(ListNode head) {
        ListNode fast = head;  
        ListNode pre = head;  
        while (fast!=null) {  
            fast = fast.next;  
            if (fast != null) {
                fast = fast.next;  
                pre = head;  
                head = head.next;  
            } 
        }  
        return pre;  
	 }
	 

	 public static void main(String[] args) {
			ListNode ln = new ListNode(1);
			ln.next = new ListNode(2);	
			ln.next.next = new ListNode(3); 
			ln.next.next.next= new ListNode(4); 
			ln.next.next.next.next= new ListNode(5);
			ln.next.next.next.next.next= new ListNode(6);
			ln.next.next.next.next.next.next= new ListNode(7);
			ConvertSortedListToBinarySearchTree l = new ConvertSortedListToBinarySearchTree();
			l.sortedListToBST1(ln);
	}
}
