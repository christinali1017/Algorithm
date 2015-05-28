package leetcode;

/**
 * Given a binary tree, find its minimum depth.

		The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class BinaryTree_MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root){
		if(root == null) return 0;
		if(root.left == null) return minDepth(root.right)+1;
		if(root.right == null) return minDepth(root.left)+1;
		return Math.min(minDepth(root.left) +1, minDepth(root.right)+1);
	}
	
	
    public int minDepth1(TreeNode root) {
        if(root == null) return 0;
        
        TreeNode parent = null;
        return minDepthHelper(root, Integer.MAX_VALUE, 0, parent);
    }
    
    public int minDepthHelper(TreeNode root, int min, int current, TreeNode parent){
    	 if(root == null){
    		 if( parent.left == null && parent.right == null)
    			 min = Math.min(min, current);
    		 return min;
    	 }
    	 
    	return Math.min(minDepthHelper(root.left, min, current +1, root),
    				 minDepthHelper(root.right,min, current +1, root));

         
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(4);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		
		BinaryTree_MinimumDepthOfBinaryTree bt = new BinaryTree_MinimumDepthOfBinaryTree();
		System.out.println(bt.minDepth1(root));
	}
}
