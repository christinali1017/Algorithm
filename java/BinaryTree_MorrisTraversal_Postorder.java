package leetcode;

public class BinaryTree_MorrisTraversal_Postorder {
	public void morrisTraversal(TreeNode root){
		if(root == null) return;
		
		TreeNode pre = null;
		TreeNode dummy = new TreeNode(0);
		dummy.left = root;
		root = dummy;
		while(root != null){
			if(root.left == null){
				root = root.right;
			}else{
				/* find predecessor */
				pre = root.left;
				while(pre.right != null && pre.right != root)
					pre = pre.right;
				
				/* if predecessor's right == null, set current node as its right child */
				if(pre.right == null){
					pre.right = root;
					root = root.left;
				}else{
					/* recover tree, when return to parent node the second time */
					pre.right = null;
					printPathReverse(root.left, pre);
					root = root.right;
				}
			}
		}
	}
	
	public void printPathReverse(TreeNode left, TreeNode pre){
		reversePath(left, pre);
		
		TreeNode node = pre;
		while(true){
			System.out.println(node.val);
			if(node.equals(left)) break;
			node = node.right;
		}
		
		/* restore tree */
		reversePath(pre, left);
	}
	
	public void reversePath(TreeNode left, TreeNode pre){
		if(left.equals(pre)) return;
	
		TreeNode end = left,
				 mid = left.right,
				 start = null;
		while(true){
			start = mid.right;
			mid.right = end;
			end = mid;
			mid = start;
			if(end.equals(pre)) break;
			
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.right = new TreeNode(6);
		
		BinaryTree_MorrisTraversal_Postorder b = new BinaryTree_MorrisTraversal_Postorder();
		b.morrisTraversal(root);
	}

}
