package leetcode;

import java.util.ArrayList;
import java.util.List;
public class Permutation {	
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0) return res;
        List<Integer> first = new ArrayList<Integer>();
        first.add(num[0]);
        res.add(first);
        for(int i = 1; i < num.length; i++){
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            for(List<Integer> l : res){
                for(int j = 0; j <= l.size(); j++){
                    List<Integer> temp = new ArrayList<Integer>(l);
                    temp.add(j, num[i]);
                    cur.add(temp);
                }
            }
            res = cur;
        }
        return res;
    } 
    
    public static void main(String[] args) {
    	Permutation p = new Permutation();
    	int[] num = {1,2,3};
    	List<List<Integer>> result = p.permute(num);
    	for(List<Integer> l : result) System.out.println(l);
	}

}
