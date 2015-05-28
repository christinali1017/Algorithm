package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree_PreorderTraversal {
	public static List<Integer> preorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int status = 0;
		if(root == null) return list;
		
		while(!stack.isEmpty() || root != null){
			if(status == 0){
				if(root == null){
					status = 1;
					continue;
				}
				list.add(root.val);
				stack.push(root);
				root = root.left;
				status = 0;
			}else{
				root = stack.pop();
				root = root.right;
				status = 0;
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
		list = preorderTraversal(root);
		for(int i : list){
			System.out.println(i);
		}
	}


}
