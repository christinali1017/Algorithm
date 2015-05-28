package leetcode;

import java.util.ArrayList;

/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

	An example is the root-to-leaf path 1->2->3 which represents the number 123.
	
	Find the total sum of all root-to-leaf numbers.
	
	For example,
	
	    1
	   / \
	  2   3
	The root-to-leaf path 1->2 represents the number 12.
	The root-to-leaf path 1->3 represents the number 13.
	
	Return the sum = 12 + 13 = 25.
 * 
 * 
 */

/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BinaryTree_SumRootToLeafNumbers {
	public int sumNumbers1(TreeNode root){
		return helper(root, 0);
	}
	public int helper(TreeNode root, int sum){
		if(root == null) return 0;
		if(root.left == null && root.right == null)
			return sum * 10 + root.val;
		return helper(root.left, sum * 10 + root.val) + helper(root.right, sum * 10 + root.val);
	}
	
   public static int sumNumbers(TreeNode root) {
	   if(root == null) return 0;
	   ArrayList<Integer> arr = printPaths(root);
	   int sum = 0;
	   for(int i : arr) sum += i;
       return sum;
    }

   public static  ArrayList<Integer> printPaths(TreeNode node){
	   ArrayList<Integer> arr = new ArrayList<Integer>();
	   ArrayList<Integer> sum = new ArrayList<Integer>();
	   printPaths(node, arr, sum);
	   return sum;
   }
   public static void printPaths(TreeNode node, ArrayList<Integer> arr,  ArrayList<Integer> sum){
	   if(node == null) return;
	   arr.add(node.val);
	   if(node.left == null && node.right == null){
		   String pathValue = "";
		   for(int i : arr)
			   pathValue += ""+i;
		   sum.add(Integer.valueOf(pathValue));
	   }
	   printPaths(node.left, new ArrayList<Integer>(arr), sum );
	   printPaths(node.right, new ArrayList<Integer>(arr), sum);
   }
   
   public static void main(String[] args) {
	   TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		System.out.println(sumNumbers(root));
		BinaryTree_SumRootToLeafNumbers b = new BinaryTree_SumRootToLeafNumbers();
		System.out.println(b.sumNumbers1(root));
   }
   
}
