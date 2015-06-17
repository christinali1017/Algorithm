package leetcode;

public class BinaryTree_MaximumPathSum {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxSum = new int[1];
        maxSum[0] = root.val;
        maxPathSum(root, maxSum);
        return maxSum[0];
    }
    
    public int maxPathSum(TreeNode root, int[] maxSum) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(maxPathSum(root.left, maxSum), 0);
        int right = Math.max(maxPathSum(root.right, maxSum), 0);
        maxSum[0] = Math.max(root.val + left + right, maxSum[0]);
        return root.val + Math.max(left, right);
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
