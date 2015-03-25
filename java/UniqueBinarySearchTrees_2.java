package leetcode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees_2 {
	  public List<TreeNode> generateTrees(int n) {
	        List<TreeNode> res = new ArrayList<TreeNode>();
	        if(n < 0) return res;
	        return helper(1, n);
	    }
	    
	    public List<TreeNode> helper(int l, int r){
	        List<TreeNode> res = new ArrayList<TreeNode>();
	        if(l > r){
	            res.add(null);
	            return res;
	        }
	        
	        for(int i = l; i <= r; i++){
	            List<TreeNode> llist = helper(l, i-1);
	            List<TreeNode> rlist = helper(i+1, r);
	            for(int j = 0; j < llist.size(); j++){
	                for(int k = 0; k < rlist.size(); k++){
	                    TreeNode root = new TreeNode(i);
	                    root.left = llist.get(j);
	                    root.right = rlist.get(k);
	                    res.add(root);
	                }
	            }
	        }
	        return res;
	    }
}
