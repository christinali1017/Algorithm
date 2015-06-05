package leetcode;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree_SymmetricTree {
	/*iterative */
	public boolean isSymmetric(TreeNode root) {
	    if (root == null || (root.left == null && root.right == null)) {
	      return true;
	    }
	    Queue<TreeNode> queueL = new LinkedList<TreeNode>();
	    Queue<TreeNode> queueR = new LinkedList<TreeNode>();
	    queueL.offer(root.left);
	    queueR.offer(root.right);
	    while (!queueL.isEmpty() && !queueR.isEmpty()) {
	        TreeNode l = queueL.poll();
	        TreeNode r = queueR.poll();
	        if (l == null && r == null) {
	            continue;
	        }
	        if ((l == null && r != null) || (l != null && r == null) || l.val != r.val) {
	            return false;
	        }
	        queueL.offer(l.left);
	        queueR.offer(r.right);
	        queueL.offer(l.right);
	        queueR.offer(r.left);
	    } 
	    return true;
	  }
	  
	/* recursive */
  public boolean isSymmetric(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) {
      return true;
    }
    return isSymmetric(root.left, root.right);
  }
  
  public boolean isSymmetric(TreeNode l, TreeNode r) {
    if (l == null && r == null) {
      return true;
    }
    if (l == null || r == null || l.val != r.val) {
      return false;
    }
    return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    
  }
    

}
