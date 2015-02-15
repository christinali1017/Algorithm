package leetcode;

public class BinaryTreeUpsideDown {

	public TreeNode upsideDownBinaryTree(TreeNode root){
		TreeNode parent = null, rightChild = null, leftChild;
		while(root != null){
			leftChild = root.left;
			root.left = rightChild;
			rightChild = root.right;
			root.right = parent;
			parent = root;
			root = leftChild;
		}
		return parent;	
	}	
	
	public void inorder(TreeNode root){
		if(root == null) return;
		inorder(root.left);
		System.out.println(root.val);
		inorder(root.right);
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
