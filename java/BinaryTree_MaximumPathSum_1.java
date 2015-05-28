package leetcode;

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

public class BinaryTree_MaximumPathSum_1 {
	int maxSum = 0;
	
	public int maxPathSum(TreeNode root) {
		if(root == null) return 0;
		maxSum = root.val;
		
		findMax(root);
		return maxSum;
	}
	
	public int findMax(TreeNode node){
		if(node == null)
			return 0;
		
		/* Can end at any node , so compare with 0 */
		int left = Math.max(findMax(node.left), 0);
		int right = Math.max(findMax(node.right), 0);
		
		maxSum = Math.max(node.val + left + right, maxSum);
		
		return node.val + Math.max(left, right);
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(70);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(-40);
		root.right.right = new TreeNode(-50);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(8);
		root.left.right.left = new TreeNode(30);
		root.left.right.right = new TreeNode(50);
		
		BinaryTree_MaximumPathSum_1 bt = new BinaryTree_MaximumPathSum_1();
		
		System.out.println(bt.maxPathSum(root));
	}
}
