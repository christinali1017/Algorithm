package leetcode;

public class BinaryTree_BalancedBinaryTree {
  public boolean isBalanced(TreeNode root) {
    // Write your solution here.
    if (root == null) {
      return true;
    }
    if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
      return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
  }
  
  public int getHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
  }
    
    //solution 2
    public boolean isBalanced(TreeNode root){
        return helper(root) >= 0;
    }

    public int helper(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		//root.left.left = new TreeNode(3);
		root.right = new TreeNode(8);
		//root.left.left = new TreeNode(11);
		//root.left.right = new TreeNode(12);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		//root.left.left.left = new TreeNode(7);
		//root.left.left.right = new TreeNode(2);
		root.right.right.right = new TreeNode(1);
		
		
		BinaryTree_BalancedBinaryTree bt = new BinaryTree_BalancedBinaryTree();
		System.out.println(bt.isBalanced1(root));
	}
    
}
