package leetcode;

public class BinaryTreeUpsideDown {

	//Iterative
	public TreeNode upsideDownBinaryTree(TreeNode root){
		TreeNode parent = null;
	    TreeNode right = null;
	    TreeNode left = null;
		while (root != null) {
			left = root.left;
			root.left = right; //  Right child turn to left child
			right = root.right;
			root.right = parent;   //Parant becomes right child
			parent = root;
			root = left;  //Left child becomes root
		}
		return parent;	
	}

	//recursive
	public TreeNode UpsideDownBinaryTree(TreeNode root) {  
	    if (root == null) {
	    	return null;
	    }  
	    TreeNode parent = root;
	    TreeNode left = root.left;
	    TreeNode right = root.right;  
	    if (left != null) {  
	        TreeNode ret = upsideDownBinaryTree(left);  
	        left.left = right;  
	        left.right = parent;  
	        root.left = null;
	        root.right = null;
	        return ret;  
	    } 
	 
	    return root;  
	}  	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		BinaryTreeUpsideDown b = new BinaryTreeUpsideDown();
		b.inorder(b.upsideDownBinaryTree(root));
	}
}
