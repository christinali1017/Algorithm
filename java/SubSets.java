package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SubSets {
	/* recursive */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        helper(nums, 0, res);
        return res;
    }
    
    public void helper(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            return;
        }
        List<List<Integer>> cur = new ArrayList<List<Integer>>();
        for (List<Integer> l : res) {
            List<Integer> temp = new ArrayList<Integer>(l);
            temp.add(nums[start]);
            cur.add(temp);
        }
        res.addAll(cur);
        helper(nums, start + 1, res);
    }
    
    /* none-recursive */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            for (List<Integer> l : res) {
                List<Integer> temp = new ArrayList<Integer>(l);
                temp.add(nums[i]);
                cur.add(temp);
            }
            res.addAll(cur);
        }
        return res;
    }
    
    public static void main(String[] args) {
    	SubSets s = new SubSets();
    	int[] S = {2};
    	List<List<Integer>> list = s.subsets1(S);
    	for(List<Integer> l : list)
    		System.out.println(l);
	}
}
