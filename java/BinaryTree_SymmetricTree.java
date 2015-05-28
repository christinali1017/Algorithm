package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

	For example, this binary tree is symmetric:
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
	Note:
	Bonus points if you could solve it both recursively and iteratively.
 * @author wish
 *
 */
public class BinaryTree_SymmetricTree {
	/*iterative */
	 public boolean isSymmetric(TreeNode root) {
	     if(root == null) return true;
	     
	     Queue<TreeNode> queuel = new LinkedList<TreeNode>();
	     Queue<TreeNode> queuer = new LinkedList<TreeNode>();
	     queuel.offer(root.left);
	     queuer.offer(root.right);
	     
	     while(!queuel.isEmpty() && !queuer.isEmpty()){
	    	 TreeNode l = queuel.poll();
	    	 TreeNode r = queuer.poll();
	    	 
	    	 if((l == null && r != null) || (r == null && l != null)) return false;
	     	 
	     	 if(l != null){
	     		 if(l.val != r.val) return false;
	     		 queuel.offer(l.left);
	     		 queuel.offer(l.right);
	     		 queuer.offer(r.right);
	     		 queuer.offer(r.left);
	     	 }
	     }
	     
	     return true;
	        
	 }
	
	/* recursive */
    public boolean isSymmetric1(TreeNode root) {
        if(root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }
		
    
    public boolean isSymmetricHelper(TreeNode l, TreeNode r){
    	if(l == null) return r == null;
    	if(r == null) return l == null;
    	
    	if(l.val != r.val) return false;
    	
    	if(!isSymmetricHelper(l.left, r.right) || !isSymmetricHelper(l.right, r.left)) return false;
    	
    	return true;
    }
    
    public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.right = new TreeNode(3);
		root.left.right.right = new TreeNode(13);
		
		root.right.left = new TreeNode(3);
		root.right.left.left = new TreeNode(13);

		BinaryTree_SymmetricTree b = new BinaryTree_SymmetricTree();
		System.out.println(b.isSymmetric(root));
		
	}
}
