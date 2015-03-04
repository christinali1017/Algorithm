package leetcode;
public class ValidateBinarySearchTree {
	   public boolean isValidBST(TreeNode root) {
	       if(root == null) return true;
	       boolean[] result = new boolean[1];
	       result[0] = true;
	       helper(root, new TreeNode[1], result);
	       return result[0];
	   }
	   
	   public void helper(TreeNode root, TreeNode[] pre, boolean[] result){
	       if(root == null) return;
	       helper(root.left, pre, result);
	       if(pre[0] != null && pre[0].val >= root.val) result[0] = false;
	       pre[0] = root;
	       helper(root.right, pre, result);
	   }
    
    public boolean isValidBST1(TreeNode root) {  
        return helper1(root, Integer.MIN_VALUE, Integer.MAX_VALUE);   
    }  
    boolean helper1(TreeNode root, int min, int max)     
    {    
        if(root == null)    
           return true;    
        if(root.val <= min || root.val >= max)  
             return false;    
         return helper1(root.left, min, root.val) && helper1(root.right, root.val, max);  
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
