package leetcode;

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0) return null;
        return helper(num, 0, num.length-1);
    }
    public TreeNode helper(int[] num, int start, int end){
        if(start > end) return null;
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, start, mid-1);
        root.right = helper(num, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
		int num[] = {1, 2, 3, 4, 5, 6, 7};
		ConvertSortedArrayToBinarySearchTree b = new ConvertSortedArrayToBinarySearchTree();
		b.sortedArrayToBST(num);
	}
}
