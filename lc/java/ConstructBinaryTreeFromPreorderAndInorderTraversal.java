package leetcode;

public class BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    //Solution 1: O(ngn)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int rootIndex = 0;
        for (int i = iStart; i <= iEnd; i++) {
            if (preorder[pStart] == inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        int leftLen = rootIndex - iStart;
        root.left  = buildTree(preorder, pStart + 1, pStart + leftLen, inorder, iStart, rootIndex - 1);
        root.right = buildTree(preorder, pStart + leftLen + 1, pEnd, inorder, rootIndex + 1, iEnd);
        return root;
    }

    //solution 2 : O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, Map<Integer, Integer> map) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int rootIndex = map.get(preorder[pStart]);
        int leftLen = rootIndex - iStart;
        root.left  = buildTree(preorder, pStart + 1, pStart + leftLen, inorder, iStart, rootIndex - 1, map);
        root.right = buildTree(preorder, pStart + leftLen + 1, pEnd, inorder, rootIndex + 1, iEnd, map);
        return root;
    }
    
    public static void main(String[] args) {
    	int[] preorder = {3,2,1};
    	int[] inorder = {1,2,3};
    	
    	BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal b = new BinaryTree_ConstructBinaryTreeFromPreorderAndInorderTraversal();
    	
    	TreeNode root = b.buildTree(preorder, inorder);
    	b.inorderRecursive(root);
	}
}
