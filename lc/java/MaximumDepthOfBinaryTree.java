package leetcode;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree_MaximumDepthOfBinaryTree {

  //recursive solution	
  public int maxDepth(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  //iterative solution
  public int maxDepth(TreeNode root){
    if(root == null) {
        return 0;
    }
    int level = 0;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode temp = queue.poll();
        if (temp.left != null) {
            queue.offer(temp.left);
        }
        if (temp.right != null) {
            queue.offer(temp.right);
        }
      }
      level++;
    }
    return level;
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
  		root.left.left.left.left = new TreeNode(22);
  		root.right.right.left = new TreeNode(5);
  		root.right.right.right = new TreeNode(1);
  		
  		BinaryTree_MaximumDepthOfBinaryTree b = new BinaryTree_MaximumDepthOfBinaryTree();
  		System.out.println(b.maxDepth2(root));
	}
}
