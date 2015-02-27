package leetcode;
import java.util.Stack;
public class FlattenBinaryTreeToLinkedList {
	public void flatten(TreeNode root){
		   if(root == null) return;
		   TreeNode pre = new TreeNode(-1);
		   Stack<TreeNode> stack = new Stack<TreeNode>();
		   int status = 0;
		   while(!stack.isEmpty() || root != null){
		       if(status == 0){
		           if(root == null){
		               status = 1;
		               continue;
		           }
		           pre.right = root;
		           pre.left = null;
		           pre = root;
		           stack.push(root.right);
		           root = root.left;
		       }else{
		           root = stack.pop();
		           status = 0;
		       }
		   }
		}
    
	public void flatten1(TreeNode root){
		   if(root == null) return;
		   TreeNode[] pre  = new TreeNode[1];
		   pre[0] = new TreeNode(-1);
		   helper(root, pre);
		}
		
		public void helper(TreeNode root, TreeNode[] pre){
		    if(root == null) return;
		    TreeNode right = root.right;
		    pre[0].left = null;
		    pre[0].right = root;
		    pre[0] = root;
		    helper(root.left, pre);
		    helper(right, pre);
		}
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		FlattenBinaryTreeToLinkedList ft = new FlattenBinaryTreeToLinkedList();
		ft.flatten(root);
		while(root != null){
			System.out.print(root.val + "->");
			root = root.right;
		}
	}

}
