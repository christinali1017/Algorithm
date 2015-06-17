package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
    
    /*Morris Traveral */
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    TreeNode pre = null;
    while (root != null) {
        if (root.left == null) {
            res.add(root.val);
            root = root.right;
        } else {
            pre = root.left;
            while (pre.right != null && pre.right != root) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = root;
                root = root.left;
            } else {
                pre.right = null;
                res.add(root.val);
                root = root.right;
            }
        }
    }
    return res;
}
	 
	 /*recursive*/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        inorderTraversal(root, res);
        return res;
    }
    public void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, res);
        res.add(root.val);
        inorderTraversal(root.right, res);
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		List<Integer> list = new ArrayList<Integer>();
		list = inorderTraversal(root);
		for(int i : list){
			System.out.println(i);
		}
	}

}
