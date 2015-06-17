package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Subsets_2 {
	/*Resursion*/
	 public List<List<Integer>> subsetsWithDup(int[] nums) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        res.add(new ArrayList<Integer>());
	        if (nums == null || nums.length == 0) {
	            return res;
	        }
	        Arrays.sort(nums);
	        helper(nums, 0, res, 0);
	        return res;
	    }
	    
	    public void helper(int[] nums, int start, List<List<Integer>> res, int lastSize) {
	        if (start == nums.length) {
	            return;
	        }
	        List<List<Integer>> cur = new ArrayList<List<Integer>>();
	        int insertPos = 0;
	        if (start > 0 && nums[start] == nums[start-1]) {
	            insertPos = lastSize;
	        }
	        for (; insertPos < res.size(); insertPos++) {
	            List<Integer> temp = new ArrayList<Integer>(res.get(insertPos));
	            temp.add(nums[start]);
	            cur.add(temp);
	        }
	        lastSize = res.size();
	        res.addAll(cur);
	        helper(nums, start + 1, res, lastSize);
	    }
    
    /* none-recursive */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int prevSize = 0;
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            int startPos = 0;
            if (i > 0 && nums[i] == nums[i-1]) {
                startPos = prevSize;
            } 
           for (;startPos< res.size(); startPos++) {
                List<Integer> temp = new ArrayList<Integer>(res.get(startPos));
                temp.add(nums[i]);
                cur.add(temp);
            }
            prevSize = res.size();
            res.addAll(cur);
        }
        return res;
    }
    
    
    public static void main(String[] args) {
    	Subsets_2 s = new Subsets_2();
    	int[] S = {1,1, 2, 3, 2};
    	List<List<Integer>> list = s.subsetsWithDup1(S);
    	for(List<Integer> l : list)
    		System.out.println(l);
	}

}
