package leetcode;

public class BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if(postorder == null || inorder == null) return null;
    	if(postorder.length != inorder.length) return null;
    	return buildTreeHelper(postorder, inorder, 0, postorder.length-1, 0, inorder.length-1);
    }
    
    public TreeNode buildTreeHelper(int[] postorder, int[] inorder, int poststart, int postend, int instart, int inend ){
    	if(poststart > postend || instart > inend) return null;
        int rootVal = postorder[postend];
        int rootIndex = 0;
        for(int i = instart; i <= inend; i++){
        	if(inorder[i] == rootVal) rootIndex = i;
        }
        TreeNode left = buildTreeHelper(postorder, inorder, poststart, poststart+rootIndex-instart-1, instart, rootIndex-1);
        TreeNode right = buildTreeHelper(postorder, inorder, poststart+rootIndex-instart, postend-1, rootIndex+1, inend);
        TreeNode root = new TreeNode(postorder[postend]);
        root.left = left;
        root.right = right;
        return root;
    }
    
	public void inorderRecursive(TreeNode root){
		if(root == null) return;
		inorderRecursive(root.left);
		System.out.println(root.val);
		inorderRecursive(root.right);
	}
    
    public static void main(String[] args) {
    	int[] postorder = {1, 2, 4, 6, 5, 3, 8, 9, 11, 13, 12, 10, 7};
    	int[] inorder = {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    	
    	BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal b = new BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal();
    	
    	TreeNode root = b.buildTree(inorder, postorder);
    	b.inorderRecursive(root);
	}
}
