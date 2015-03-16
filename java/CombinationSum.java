package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    public void helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur, int start){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            cur.add(candidates[i]);
            helper(candidates, target-candidates[i], res, cur, i);
            cur.remove(cur.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0) return res;
        helper(candidates, target, res, new ArrayList<Integer>());
        return res;
    }
    public void helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur){
        if(target < 0) return;
        if(target == 0){
            List<Integer> temp = new ArrayList<Integer>(cur);
            Collections.sort(temp);
            if(!res.contains(temp)) res.add(temp);
            return;
        }
        for(int i = 0; i < candidates.length; i++){
            cur.add(candidates[i]);
            helper(candidates, target-candidates[i], res, cur);
            cur.remove(cur.size()-1);
        }
    }
	
    public static void main(String[] args) {
    	CombinationSum c = new CombinationSum();
    	int[] candidates = {5, 1, 1, 2,3, 6, 7};
    	List<List<Integer>> list = c.combinationSum(candidates, 7);
    	for(List<Integer> l : list)
    		System.out.println(l);
	}

}
