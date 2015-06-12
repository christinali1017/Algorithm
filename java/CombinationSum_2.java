package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CombinationSum_2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        combinationSum(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    public void combinationSum(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur, int start){
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            cur.add(candidates[i]);
            combinationSum(candidates, target-candidates[i], res, cur, i + 1);
            cur.remove(cur.size()-1);
        }
    }
	
    public static void main(String[] args) {
    	CombinationSum_2 c = new CombinationSum_2();
    	int[] candidates = {5, 1, 1, 2,3, 6, 7};
    	List<List<Integer>> list = c.combinationSum2(candidates, 7);
    	for(List<Integer> l : list)
    		System.out.println(l);
	}

}
