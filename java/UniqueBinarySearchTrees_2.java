package leetcode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees_2 {
  public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (n < 0) {
           return res;
        }  
        return generateTrees(1, n);
    }
    public List<TreeNode> generateTrees(int l,  int r) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (l > r) {
            res.add(null);
            return res;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> lList = generateTrees(l, i-1);
            List<TreeNode> rList = generateTrees(i+1, r);
            for (int j = 0; j < lList.size(); j++) {
                for (int k = 0; k < rList.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = lList.get(j);
                    root.right = rList.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }
}
