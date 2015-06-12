package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    
    public List<List<Integer>> combine(int n, int k) {  
        List<List<Integer>> res = new ArrayList<List<Integer>>();  
        if (n <= 0 || n < k) {
            return res;
        }
        combine(n, k, 1, new ArrayList<Integer>(), res);  
        return res;  
    }  
    private void combine(int n, int k, int start, List<Integer> item, List<List<Integer>> res)  {  
        if (item.size() == k) {  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }  
        for (int i = start; i <= n; i++) {  
            item.add(i);  
            combine(n, k, i + 1, item, res);  
            item.remove(item.size() - 1);  
        }  
    } 
    
    public static void main(String[] args) {
    	Combinations c = new Combinations();
    	System.out.println(c.combine(6, 2));
    	System.out.println(c.combine(6, 2).size());
	}
}
