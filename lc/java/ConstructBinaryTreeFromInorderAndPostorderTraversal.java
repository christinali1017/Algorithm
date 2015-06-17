package leetcode;

public class BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    public TreeNode buildTree(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> map) {
        if (iStart > iEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        int rootIndex = map.get(postorder[pEnd]);
        root.left = buildTree(inorder, iStart, rootIndex - 1, postorder, pStart, pStart + (rootIndex - 1 - iStart), map);
        root.right = buildTree(inorder, rootIndex + 1, iEnd, postorder, pStart + (rootIndex - iStart), pEnd - 1, map);
        return root;
    }
    
    public static void main(String[] args) {
    	int[] postorder = {1, 2, 4, 6, 5, 3, 8, 9, 11, 13, 12, 10, 7};
    	int[] inorder = {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    	
    	BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal b = new BinaryTree_ConstructBinaryTreeFromInorderAndPostorderTraversal();
    	
    	TreeNode root = b.buildTree(inorder, postorder);
    	b.inorderRecursive(root);
	}
}
