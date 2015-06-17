package leetcode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \      \
	        7    2      1
	return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class BinaryTree_PathSum {
	public boolean hasPathSum1(TreeNode root, int sum){
		if(root == null) return false;
		if(root.left == null && root.right == null && root.val == sum) return true;
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
	}
	
	
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        boolean[] arr = new boolean[1];
        TreeNode parent = new TreeNode(-1);
        hasPathSumHelper(root, sum, 0, arr, parent);
        return arr[0];
    }
    
    public void hasPathSumHelper(TreeNode root, int sum, int current, boolean[] arr, TreeNode parent){
    	if(root == null){
    		/* maintain it's root to leaf path */
    		if(current == sum && parent.left == null && parent.right == null)
    			arr[0] = true;
    		return; 
    	}
		current += root.val;
		hasPathSumHelper(root.left, sum, current, arr, root);
		hasPathSumHelper(root.right, sum, current, arr, root);
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.right.right = new TreeNode(1);
		
		BinaryTree_PathSum p = new BinaryTree_PathSum();
		int sum = 13;
		System.out.println(p.hasPathSum(root, sum));
	}
    
}
