package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
    	if(root == null) return list;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	boolean inorder = true;
    	while(!queue.isEmpty()){
    	    List<Integer> cur = new ArrayList<Integer>();
    	    for(int i = 0, size = queue.size(); i < size; i++){
    	        TreeNode temp = queue.poll();
    	        cur.add(temp.val);
    	        if(temp.left != null) queue.offer(temp.left);
    	        if(temp.right != null) queue.offer(temp.right);
    	    }
    	    if(inorder == true){
    	        list.add(cur);
    	        inorder = false;
    	    }else{
    	        Collections.reverse(cur);
    	        list.add(cur);
    	        inorder = true;
    	    }
    	}
    	return list;
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
		root.left.left.left.left = new TreeNode(77);
		root.left.left.right.left = new TreeNode(22);
		root.right.right.left.left = new TreeNode(55);
		root.right.right.right.left = new TreeNode(11);
		
		BinaryTreeZigzagLevelOrderTraversal b = new BinaryTreeZigzagLevelOrderTraversal();
		List<List<Integer>> list = b.zigzagLevelOrder(root);
		for(List<Integer> l : list)
			System.out.println(l);
		
	}
}
