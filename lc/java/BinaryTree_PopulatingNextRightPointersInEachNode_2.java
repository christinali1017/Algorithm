package leetcode;

import java.util.LinkedList;
import java.util.Queue;
/**
	 * Populating Next Right Pointers in Each Node II Total Accepted: 24711 Total Submissions: 79790 My Submissions Question Solution 
	Follow up for problem "Populating Next Right Pointers in Each Node".
	
	What if the given tree could be any binary tree? Would your previous solution still work?
	
	Note:
	
	You may only use constant extra space.
	For example,
	Given the following binary tree,
	         1
	       /  \
	      2    3
	     / \    \
	    4   5    7
	After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \    \
	    4-> 5 -> 7 -> NULL
	 * 
 */

public class BinaryTree_PopulatingNextRightPointersInEachNode_2 {
	/*
	 * O(1) space
	 */
    public void connect(TreeLinkNode root) {
    	if(root == null) return ;
    	
    	TreeLinkNode head = null, 
    				 current = null,
    				 pre = null;
    	
    	/* identify if it is the first element of a certain level*/
    	boolean status = true;
    	
    	/* root != null loop every level */
    	while(root != null){
    		
    		/*traverse all nodes in certain level */
    		current = root;
    		while(current != null){
    			if(current.left != null && current.right != null){
    				if(status == true){
        				head = current.left;
        				pre = head;
    				}else{
    					pre.next = current.left;
    					pre = pre.next;
    				}
    				pre.next = current.right;
    				pre = pre.next;
    			}else if(current.left != null){
    				if(status == true){
    					head = current.left;
    					pre = head;
    				}else{
    					pre.next = current.left;
    					pre = pre.next;
    				}
    			}
    			else if(current.right != null){
    				if(status == true){
    					head = current.right;
    					pre = head;
    				}else{
    					pre.next = current.right;
    					pre = pre.next;
    				}
    			}
    			current = current.next;
    			if(head != null)
    				status = false;
    		}
    		status = true;
    		
    		/* the last level */
    		if(root.equals(head)) return;
    		root = head;
    		head = null;
    	}
    }
	
	/*
	 * O(n) space
	 */
    public void connect1(TreeLinkNode root) {
    	if(root == null) return ;
    	
    	Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
        	TreeLinkNode node = queue.poll();
        	if(node.left != null){
        		node.left.next = node.right;
        		queue.offer(node.left);
        		queue.offer(node.right);
        		if(node.next != null){
        			node.right.next = node.next.left;
        		}
        	}
        }
    }
    
    public static void main(String[] args) {
    	TreeLinkNode root = new TreeLinkNode(3);
  		root.left = new TreeLinkNode(9);
  		root.right = new TreeLinkNode(20);
  		root.left.left = new TreeLinkNode(4);
  		root.left.right = new TreeLinkNode(5);
  		root.right.left = new TreeLinkNode(15);
  		root.right.right = new TreeLinkNode(7);
  		root.left.left.left = new TreeLinkNode(8);
  		root.right.right.right = new TreeLinkNode(9);
  		root.left.left.left.left = new TreeLinkNode(10);
  		root.left.left.left.right = new TreeLinkNode(11);
  		root.right.right.right.left = new TreeLinkNode(12);
  		root.right.right.right.right = new TreeLinkNode(13);
  		
  		BinaryTree_PopulatingNextRightPointersInEachNode_2 p = new BinaryTree_PopulatingNextRightPointersInEachNode_2();
  		p.connect(root);
  		System.out.println(" node is : "+root.val + "   next is: " + root.next);
  		System.out.println(" node is : "+root.left.val + "   next is: " + root.left.next.val);
  		System.out.println(" node is : "+root.right.val + "   next is: " + root.right.next);
  		System.out.println(" node is : "+root.right.left.val + "   next is: " + root.right.left.next.val);
  		System.out.println(" node is : "+root.right.right.val + "   next is: " + root.right.right.next);
  		System.out.println(" node is : "+root.left.val + "   next is: " + root.left.next.val);
  		System.out.println(" node is : "+root.left.left .val + "   next is: " + root.left.left .next.val);
  		System.out.println(" node is : "+root.left.right .val + "   next is: " + root.left.right.next.val);
  		System.out.println(" node is : "+root.left.left.left .val + "   next is: " + root.left.left.left.next.val);
  		System.out.println(" node is : "+root.left.left.left.left .val + "   next is: " + root.left.left.left.left.next.val);
  		System.out.println(" node is : "+root.left.left.left.right .val + "   next is: " + root.left.left.left.right.next.val);
  		System.out.println(" node is : "+root.right.right.right.left .val + "   next is: " + root.right.right.right.left.next.val);
	}
}
