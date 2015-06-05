package leetcode;
public class SameTree {
  public boolean isSameTree(TreeNode one, TreeNode two) {
    if (one == null && two == null) {
      return true;
    }
    if (one == null || two == null || one.val != two.val) {
      return false;
    }
    return isSameTree(one.left, two.left) && isSameTree(one.right, two.right);
  }
    
}
