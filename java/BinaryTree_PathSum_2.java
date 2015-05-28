package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	
	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \    / \
	        7    2  5   1
	return
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
 */
public class BinaryTree_PathSum_2 {
	public List<List<Integer>> pathSum2(TreeNode root, int sum){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(root == null) return list;
		helper2(root, sum, list, new ArrayList<Integer>());
		return list;
	}

	public void helper2(TreeNode root, int sum, List<List<Integer>> list, List<Integer> arr){
		if(root == null) return;
		if(sum == root.val && root.left == null && root.right == null){
			arr.add(root.val);
			list.add(new ArrayList<Integer>(arr));
			arr.remove(arr.size()-1);
		}
		
		arr.add(root.val);
		helper2(root.left, sum-root.val, list, arr);
		arr.remove(arr.size()-1);
		
		arr.add(root.val);
		helper2(root.right, sum-root.val, list, arr);
		arr.remove(arr.size()-1);
		
	}
	
	public List<List<Integer>> pathSum1(TreeNode root, int sum){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(root == null) return list;
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(root.val);
		helper(root, sum-root.val, list, arr);
		return list;
	}

	public void helper(TreeNode root, int sum, List<List<Integer>> list, List<Integer> arr){
		if(root == null) return;
		if(sum == 0 && root.left == null && root.right == null){
			list.add(new ArrayList<Integer>(arr));
		}
		if(root.left != null){
			arr.add(root.left.val);
			helper(root.left, sum-root.left.val, list, arr);
			arr.remove(arr.size()-1);
		}
	
		if(root.right != null){
			arr.add(root.right.val);
			helper(root.right, sum-root.right.val, list, arr);
			arr.remove(arr.size()-1);
		}
	}

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	 List<List<Integer>> list = new ArrayList<List<Integer>>();
    	 if(root == null) return list;
    	 
    	 TreeNode parent = new TreeNode(-1);
    	 TreeNode previous = new TreeNode(-1);
    	 ArrayList<Integer> arr = new ArrayList<Integer>();
         hasPathSumHelper(root, sum, 0, list, arr, parent, previous);
    	 
         return list;
    }

    
    public void hasPathSumHelper(TreeNode root, int sum, int current,  List<List<Integer>> list, ArrayList<Integer> arr, TreeNode parent, TreeNode previous){
    	if(root == null){
    		/* maintain it's root to leaf path */
    		if(current == sum && parent.left == null && parent.right == null && !parent.equals(previous))
    			list.add(arr);
    		return; 
    	}
		current += root.val;
		arr.add(root.val);
		hasPathSumHelper(root.left, sum, current,list, new ArrayList<Integer>(arr), root, root);
		hasPathSumHelper(root.right, sum, current,list, new ArrayList<Integer>(arr), root, root.left);
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
		
		BinaryTree_PathSum_2 p = new BinaryTree_PathSum_2();
		int sum = 22;
		List<List<Integer>> list = p.pathSum2(root, sum);
		for(List<Integer> l : list)
			System.out.println(l);
	}
    
}
