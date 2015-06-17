package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        List<Integer> first = new ArrayList<Integer>();
        first.add(num[0]);
        res.add(first);
        Set<String> set = new HashSet<String>();
        for (int i = 1; i < num.length; i++) {
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            for (List<Integer> l : res) {
                for(int j = 0; j <= l.size(); j++) {
                    List<Integer> temp = new ArrayList<Integer>(l);
                    temp.add(j, num[i]);
                    if(set.add(temp.toString())) {
                        cur.add(temp);
                    }
                }
            }
            res = cur;
        }
        return res;
    }
	 
	/* use set to maintain unique*/
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return list;
        }
        return permuteUnique(list, num, 0);
    }
    
    public List<List<Integer>> permuteUnique(List<List<Integer>> list, int[] num, int i){
        if (i == num.length) {
            List<Integer> arr = new ArrayList<Integer>();
            list.add(arr);
            return list;
        }
        
        list = permuteUnique(list, num, i+1);
        Set<List<Integer>> current = new HashSet<List<Integer>>();
        for (List<Integer> l : list) {
            if(l.size() == 0){
                List<Integer> arr = new ArrayList<Integer>();
                arr.add(num[i]);
                current.add(arr);
            } else {
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> arr = new ArrayList<Integer>(l);
                    arr.add(j, num[i]);
                    current.add(arr);
                }
            }
        }
        list = new ArrayList<List<Integer>>(current);
        return list;
    }
    public static void main(String[] args) {
    	Permutation2 p = new Permutation2();
    	int[] num = {1,2,3, 2, 2};
    	List<List<Integer>> result = p.permuteUnique(num);
    	for(List<Integer> l : result) System.out.println(l);
    	System.out.println(result.size());
    	

	}
}
