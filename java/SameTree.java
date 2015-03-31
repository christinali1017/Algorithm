package leetcode;
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if((p == null || q == null) || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        boolean[] res = new boolean[1];
        res[0] = true;
        helper1(p, q, res);
        return res[0];
    }
    
    public void helper1(TreeNode p, TreeNode q, boolean[] res){
         if(p == null && q == null) return;
         if((p == null || q == null)|| (p.val != q.val)) {
             res[0] = false;
             return;
         }     
       helper1(p.left, q.left, res);
       helper1(p.right, q.right, res);
    }
    
    public static void main(String[] args) {
		TreeNode p = new TreeNode(1);
		p.left = new TreeNode(2);
		p.right = new TreeNode(2);
		p.left.right = new TreeNode(3);
		p.left.right.right = new TreeNode(13);
		
		p.right.left = new TreeNode(3);
		p.right.left.left = new TreeNode(13);
		
		TreeNode q = new TreeNode(1);
		q.left = new TreeNode(2);
		q.right = new TreeNode(2);
		q.left.right = new TreeNode(3);
		q.left.right.right = new TreeNode(13);
		
		q.right.left = new TreeNode(3);
		q.right.left.left = new TreeNode(13);
		
		SameTree b = new SameTree();
		System.out.println(b.isSameTree(p, q));
		
	}
}
