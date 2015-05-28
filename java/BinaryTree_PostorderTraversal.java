package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree_PostorderTraversal {
	
	public static List<Integer> postorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> statusStack = new Stack<Integer>();
		int status = 0;
		statusStack.push(0);
		if(root == null) return list;
		
		while(!stack.isEmpty() || root != null){
			if(status == 0){
				if(root == null){
					status = statusStack.pop();
					continue;
				}
				stack.push(root);
				statusStack.push(1);
				root = root.left;
				status = 0;
			}else if(status == 1){
				root = stack.peek();
				root = root.right;
				statusStack.push(2);
				status = 0;
			}else{
				list.add(stack.pop().val);
				status = statusStack.pop();
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		List<Integer> list = new ArrayList<Integer>();
		list = postorderTraversal(root);
		for(int i : list){
			System.out.println(i);
		}
	}

}
