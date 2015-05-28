package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.

	The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * @author wish
 *
 */

public class BinaryTree_MaximumDepthOfBinaryTree {
	public int maxDepth2(TreeNode root){
		if(root == null) return 0;
		int level = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				TreeNode temp = queue.poll();
				if(temp.left != null) queue.offer(temp.left);
				if(temp.right != null) queue.offer(temp.right);
			}
			level++;
		}
		return level;

	}
	
	/* because we need to calculate the max depth, so it must on one of the leaf node*/
	/* but if we need to calculate the min depth, we can not do like these, we must maintain that the last node is leaf node*/
	public int maxDepth(TreeNode root){
		if(root == null) return 0;
		return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
	}
	
    public int maxDepth1(TreeNode root) {
    	  if(root == null) return 0;
          
          TreeNode parent = null;
          return maxDepthHelper(root, Integer.MIN_VALUE, 0, parent);
      }
      
      public int maxDepthHelper(TreeNode root, int max, int current, TreeNode parent){
      	 if(root == null){
      		 if( parent.left == null && parent.right == null)
      			 max = Math.max(max, current);
      		 return max;
      	 }
      	 
      	return Math.max(maxDepthHelper(root.left, max, current + 1, root),
      				 maxDepthHelper(root.right,max, current + 1, root));

           
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
  		root.left.left.left.left = new TreeNode(22);
  		root.right.right.left = new TreeNode(5);
  		root.right.right.right = new TreeNode(1);
  		
  		BinaryTree_MaximumDepthOfBinaryTree b = new BinaryTree_MaximumDepthOfBinaryTree();
  		System.out.println(b.maxDepth2(root));
	}
}
