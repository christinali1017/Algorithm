package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

	For example:
	Given binary tree {3,9,20,#,#,15,7},
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its level order traversal as:
	[
	  [3],
	  [9,20],
	  [15,7]
	]
 */
public class BinaryTreeLevelOrderTraversal_1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
    	 List<List<Integer>> list = new ArrayList<List<Integer>>();
    	 if(root == null) return list;
    	 
    	 Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	 queue.offer(root);
    	 
    	 while(!queue.isEmpty()){
    		 TreeNode temp = null;
    		 int size = queue.size();
    		 ArrayList<Integer> currentLevel = new ArrayList<Integer>();
    		 for(int i=0; i < size; i++){
    			 temp = queue.poll();
    			 currentLevel.add(temp.val);
    			 if(temp.left != null)
    				 queue.offer(temp.left);
    			 if(temp.right != null)
    				 queue.offer(temp.right);
    		 }
    		 list.add(currentLevel);
    	 }
    	return list; 
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(4);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		
		BinaryTreeLevelOrderTraversal_1 b = new BinaryTreeLevelOrderTraversal_1();
		List<List<Integer>> list = b.levelOrder(root);
		for(List<Integer> l : list)
			System.out.println(l);
		
	}
}
