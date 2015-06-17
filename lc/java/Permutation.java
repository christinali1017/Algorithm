package leetcode;

import java.util.ArrayList;
import java.util.List;
public class Permutation {	
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        List<Integer> first = new ArrayList<Integer>();
        first.add(num[0]);
        res.add(first);
        for (int i = 1; i < num.length; i++) {
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            for (List<Integer> l : res) {
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> temp = new ArrayList<Integer>(l);
                    temp.add(j, num[i]);
                    cur.add(temp);
                }
            }
            res = cur;
        }
        return res;
    } 


    //recursive
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return list;
        }
        return permute(list, num, 0);
    }
    
    public List<List<Integer>> permute(List<List<Integer>> list, int[] num, int i){
        if (i == num.length) {
            List<Integer> arr = new ArrayList<Integer>();
            list.add(arr);
            return list;
        }
        
        list = permute(list, num, i+1);
        List<List<Integer>> current = new ArrayList<List<Integer>>();
        for (List<Integer> l : list) {
            if (l.size() == 0) {
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
    	Permutation p = new Permutation();
    	int[] num = {1,2,3};
    	List<List<Integer>> result = p.permute(num);
    	for(List<Integer> l : result) System.out.println(l);
	}

}
