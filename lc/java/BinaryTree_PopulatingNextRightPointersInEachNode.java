package leetcode;

import java.util.LinkedList;
/*
 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
	Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
	
	Initially, all next pointers are set to NULL.
	
	Note:
	
	You may only use constant extra space.
	You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	For example,
	Given the following perfect binary tree,
	         1
	       /  \
	      2    3
	     / \  / \
	    4  5  6  7
	After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \  / \
	    4->5->6->7 -> NULL
 */
import java.util.Queue;


public class BinaryTree_PopulatingNextRightPointersInEachNode {
	/*
	 * O(1) space
	 */
    public void connect(TreeLinkNode root) {
    	if(root == null) return ;
    	
    	TreeLinkNode head = null, 
    				 current = null;
    	/* root != null loop every level */
    	while(root != null){
    		
    		/*traverse all nodes in certain level */
    		current = root;
    		while(current != null){
    			if(current.left != null){
    				head = root.left;
    				current.left.next = current.right;
    				if(current.next != null){
    					current.right.next = current.next.left;
    				}
    			}else
    				head = null;
    			current = current.next;
    		}
    		root = head;
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
    	TreeLinkNode root = new TreeLinkNode(1);
  		root.left = new TreeLinkNode(2);
  		root.right = new TreeLinkNode(3);
  		root.left.left = new TreeLinkNode(4);
  		root.left.right = new TreeLinkNode(5);
  		root.right.left = new TreeLinkNode(6);
  		root.right.right = new TreeLinkNode(7);
  		
  		BinaryTree_PopulatingNextRightPointersInEachNode p = new BinaryTree_PopulatingNextRightPointersInEachNode();
  		p.connect(root);
  		System.out.println(" node is : "+root.left.val + "   next is: " + root.left.next.val);
  		System.out.println(" node is : "+root.left.left .val + "   next is: " + root.left.left .next.val);
  		System.out.println(" node is : "+root.left.right .val + "   next is: " + root.left.right .next.val);
  		System.out.println(" node is : "+root.right.left .val + "   next is: " + root.right.left .next.val);
	}
}
