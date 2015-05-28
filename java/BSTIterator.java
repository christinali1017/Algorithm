package leetcode;

import java.util.Stack;
/* O(1) time and uses O(h) memory */
public class BSTIterator {
	Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        helper(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        helper(next.right);
        return next.val;
    }
    
    public void helper(TreeNode root){
    	while(root != null){
    		stack.push(root);
    		root = root.left;
    	}
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.right = new TreeNode(6);
		BSTIterator bsti = new BSTIterator(root);
		while(bsti.hasNext()){
			System.out.println(bsti.next());
		}
	}
}






class BSTIterator1 {
	/* not O(h) memory*/ 
	Stack<TreeNode> stack;
    public BSTIterator1(TreeNode root) {
        stack = new Stack<TreeNode>();
        inorder(stack, root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return stack.pop().val;
    }
    
    public void inorder(Stack<TreeNode> stack, TreeNode root){
    	if(root == null) return;
    	if(root.right != null) inorder(stack, root.right);
    	stack.push(root);
    	if(root.left != null) inorder(stack, root.left);
    }
}
