package leetcode;

public class BinaryTree_MorrisTraversal_Preorder {
	public void morrisTraversal(TreeNode root){
		if(root == null) return;
		
		TreeNode pre = null;
		while(root != null){
			if(root.left == null){
				System.out.println(root.val);
				root = root.right;
			}else{
				/* find predecessor */
				pre = root.left;
				while(pre.right != null && pre.right != root)
					pre = pre.right;
				
				/* if predecessor's right == null, set current node as its right child */
				if(pre.right == null){
					pre.right = root;
					System.out.println(root.val);
					root = root.left;
				}else{
					/* recover tree, when return to parent node the second time */
					pre.right = null;
					root = root.right;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.right = new TreeNode(6);
		
		BinaryTree_MorrisTraversal_Preorder b = new BinaryTree_MorrisTraversal_Preorder();
		b.morrisTraversal(root);
	}

}
