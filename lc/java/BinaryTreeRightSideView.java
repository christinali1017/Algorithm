package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.offer(root);
        while(!deque.isEmpty()){
            TreeNode cur = null;
            for(int i = 0, size = deque.size(); i < size; i++){
                cur = deque.pollLast();
                if(cur.right != null){
                    deque.addFirst(cur.right);
                }
                if(cur.left != null){
                    deque.addFirst(cur.left);
                }
                if(i == 0){
                    res.add(cur.val);
                }
            }
        }
        return res;
    }
}
