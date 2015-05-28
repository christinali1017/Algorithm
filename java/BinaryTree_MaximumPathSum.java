package leetcode;

import java.util.ArrayList;


/*
 * Given a binary tree, find the maximum path sum.

	The path may start and end at any node in the tree.
	
	For example:
	Given the below binary tree,
	
	       1
	      / \
	     2   3
	Return 6.
 */

public class BinaryTree_MaximumPathSum {
	
	public int maxPathSum1(TreeNode root){
		if(root == null) return 0;
		int[] result = new int[1];
		result[0] = root.val;
		findMax(root, result);
		return result[0];
	}

	public int findMax(TreeNode node, int[] result){
		if(node == null) return 0;
		int left = Math.max(0, findMax(node.left, result));
		int right = Math.max(0, findMax(node.right, result));
		result[0] = Math.max(node.val + left + right, result[0]);
		return node.val + Math.max(left, right);	
	}
	
	/* Time limit exceeded 
	 * Also, because node can end at any node in the tree, so not necessarily end at leaf.
	 * So there has some problems with max path to leaf.
	 * */
	 public int maxPathSum(TreeNode root) {
		 if(root == null) return 0;
		 ArrayList<Integer> arr = new ArrayList<Integer>();
		 arr.add(Integer.MIN_VALUE);
		 return maxPathHelper(root, arr);
	        
	 }
	 
	 public int maxPathHelper(TreeNode root, ArrayList<Integer> arr){
		 int sum = root.val;
		 if(root.left != null){
			 sum += maxPathToLeaf(root.left, 0);
			 maxPathHelper(root.left, arr);
		 }
		 if(root.right != null){
			 sum += maxPathToLeaf(root.right, 0);
			 maxPathHelper(root.right, arr);
		 }
		 if( arr.get(0) < sum)
			 arr.add(0, sum);
		 return arr.get(0);
	 }
	 
	 /* The max from a certain node to leaf */
	 public int maxPathToLeaf(TreeNode root, int sum){
		 sum = root.val;
		 if(root.left != null) sum += maxPathToLeaf(root.left, sum);
		 int max = sum;
		 if(root.left != null)
			 sum = root.val;
		 
		 /* calculate left and right subtree*/
		 if(root.right != null)  sum += maxPathToLeaf(root.right, sum);
		 if(max > sum)
			 sum = max;
		 return sum;
	 }
	 
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(20);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(8);
		root.left.right.left = new TreeNode(30);
		root.left.right.right = new TreeNode(50);
		
		BinaryTree_MaximumPathSum bt = new BinaryTree_MaximumPathSum();
		
		System.out.println(bt.maxPathSum(root));
		System.out.println(bt.maxPathSum1(root));
	}
}
