package leetcode;
public class ValidateBinarySearchTree {
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean[] res = new boolean[1];
    res[0] = true;
    isBST(root, res, new TreeNode[1]);
    return res[0];
  }
  
  public void isBST(TreeNode root, boolean[] res, TreeNode[] pre) {
    if (root == null) {
      return;
    }
    isBST(root.left, res, pre);
    if (pre[0] != null && pre[0].val >= root.val) {
      res[0] = false;
    }
    pre[0] = root;
    isBST(root.right, res, pre);
  }
    
    public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(11);
		root.right.left.right = new TreeNode(14);
		root.right.right = new TreeNode(20);
		
		ValidateBinarySearchTree v = new ValidateBinarySearchTree();
		System.out.println(v.isValidBST(root));
	}
}
