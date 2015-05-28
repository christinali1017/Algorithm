package leetcode;
/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
	Note:
	You may assume that duplicates do not exist in the tree.
 * @author wish
 *
 */
public class BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if(preorder == null || inorder == null) return null;
    	if(preorder.length != inorder.length) return null;
    	return buildTreeHelper(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    
    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int prestart, int preend, int instart, int inend ){
    	if(prestart > preend || instart > inend) return null;
        int rootVal = preorder[prestart];
        int rootIndex = 0;
        for(int i = instart; i <= inend; i++){
        	if(inorder[i] == rootVal) rootIndex = i;
        }
        TreeNode left = buildTreeHelper(preorder, inorder, prestart+1, prestart+rootIndex-instart, instart, rootIndex-1);
        TreeNode right = buildTreeHelper(preorder, inorder, prestart+rootIndex-instart+1, preend, rootIndex+1, inend);
        TreeNode root = new TreeNode(preorder[prestart]);
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
    	int[] preorder = {3,2,1};
    	int[] inorder = {1,2,3};
    	
    	BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal b = new BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal();
    	
    	TreeNode root = b.buildTree(preorder, inorder);
    	b.inorderRecursive(root);
	}
}
