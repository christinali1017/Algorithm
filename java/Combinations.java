package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations Total Accepted: 29812 Total Submissions: 98521 My Submissions Question Solution 
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 * @author wish
 *
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(k == n){
        	 List<List<Integer>> basic = new ArrayList<List<Integer>>();
        	 basic.add( new ArrayList<Integer>());
        	 for(int i = 1; i <= n; i++) basic.get(0).add(i);
        	 return basic;
        }
        if(n < k) return list;
        if(k == 1) {
        	for(int i = 1; i <= n; i++){
        		List<Integer> item = new ArrayList<Integer>();
        		item.add(i);
        		list.add(item);
        	}
        	return list;
        }
        List<List<Integer>> temp = combine(n, k-1);
        for(List<Integer> l : temp){
        	for(int i = l.get(l.size()-1) + 1; i <= n; i++){
        		List<Integer> item = new ArrayList<Integer>(l);
        		item.add(i);
        		list.add(item);
        	}
        }
        return list;
    }
    
    public ArrayList<ArrayList<Integer>> combine1(int n, int k) {  
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
        if(n<=0 || n<k)  
            return res;  
        helper(n,k,1,new ArrayList<Integer>(), res);  
        return res;  
    }  
    private void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)  
    {  
        if(item.size()==k)  
        {  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }  
        for(int i=start;i<=n;i++)  
        {  
            item.add(i);  
            helper(n,k,i+1,item,res);  
            item.remove(item.size()-1);  
        }  
    } 
    
    public static void main(String[] args) {
    	Combinations c = new Combinations();
    	System.out.println(c.combine(6, 2));
    	System.out.println(c.combine(6, 2).size());
	}
}
