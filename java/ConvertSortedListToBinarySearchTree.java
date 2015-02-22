package leetcode;
public class ConvertSortedListToBinarySearchTree {
	/**
	 * Bottom up, then we can visit the list in order. 
	 */
	 public TreeNode sortedListToBST(ListNode head) {
		    if(head == null) return null;
		    int len = 0;
		    ListNode temp = head;
		    while(temp != null){
		        temp = temp.next;
		        len++;
		    }
		    ListNode[] saveHead = new ListNode[1];
		    saveHead[0] = head;
		    return helper(saveHead, 0, len-1);
		 }
		 public TreeNode helper(ListNode[] saveHead, int start, int end){
		    if(start > end) return null;
		    int mid = (start + end)/2;
		    TreeNode left = helper(saveHead, start, mid-1);
		    TreeNode root = new TreeNode(saveHead[0].val);
		    root.left = left;
		    saveHead[0] = saveHead[0].next;
		    root.right = helper(saveHead, mid + 1, end);
		    return root;
		 }
	 
		 
	/*top-down*/
	 public TreeNode sortedListToBST1(ListNode head) {
		    if(head == null) return null;
		    if(head.next == null) return new TreeNode(head.val);
		    ListNode pre = getMidPre(head);
		    ListNode mid = pre.next;
		    pre.next = null;
		    TreeNode root= new TreeNode(mid.val);
		    root.left = sortedListToBST(head);
		    root.right = sortedListToBST(mid.next);
		    return root;
		 }
		 
	 public ListNode getMidPre(ListNode head){
        ListNode fast = head;  
        ListNode pre = head;  
        while(fast!=null) {  
            fast = fast.next;  
            if(fast != null){
                fast = fast.next;  
                pre = head;  
                head = head.next;  
            } 
        }  
        return pre;  
	 }
	 
	 /*Solution 3, use array to create BST*/
	 public TreeNode sortedListToBST2(ListNode head) {
		    if(head == null) return null;
		    if(head.next == null) return new TreeNode(head.val);
	        int count = 0;
	        ListNode temp = head;
	        while(temp != null){
	            count++;
	            temp = temp.next;
	        }
	        int[] num = new int[count];
	        temp = head;
	        count = 0;
	        while(temp != null){
	            num[count++] = temp.val;
	            temp = temp.next;
	        }
	        return sortedArrayToBST(num);
		 }
		 
	    public TreeNode sortedArrayToBST(int[] num) {
	        if(num == null || num.length == 0) return null;
	        return helper(num, 0, num.length-1);
	    }
	    public TreeNode helper(int[] num, int start, int end){
	        if(start > end) return null;
	        int mid = (start + end)/2;
	        TreeNode root = new TreeNode(num[mid]);
	        root.left = helper(num, start, mid-1);
	        root.right = helper(num, mid + 1, end);
	        return root;
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
