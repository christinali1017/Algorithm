package leetcode;

import java.util.ArrayList;
import java.util.List;
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        res.add("");
        if(n <= 0) return res;
        for(int i = 0; i < n; i++){
            List<String> cur = new ArrayList<String>();
            for(String s : res){
                for(int j = 0; j <= s.length(); j++){
                    String temp =  s.substring(0, j) + "()" + s.substring(j, s.length());
                    if(!cur.contains(temp)) cur.add(temp);
                }
            }
            res = cur;
        }
        return res;
    }
    
    
    public List<String> generateParenthesis1(int n) {
    	List<String> list = new ArrayList<String>();
    	if(n == 0){
    		list.add("");
    		return list;
    	}else if (n == 1){
    		list.add("()");
    		return list;
    	}
    	
    	return helper(n);
    	
    }
    
    public List<String> helper(int n){
    	if(n == 0){
    		List<String> list = new ArrayList<String>();
    		list.add("");
    		return list;
    	}
    	List<String> list  = helper(n-1);
    	List<String> result = new ArrayList<String>();
    	for(String s : list){
    		for(int i = 0, len = s.length(); i <= len; i++){
    			String newS = s.substring(0, i) + "()" + s.substring(i, s.length());
    			if(!result.contains(newS)) result.add(newS);
    		}
    	}
    	return result;
    }
    
    public static void main(String[] args) {
    	GenerateParentheses g = new GenerateParentheses();
    	System.out.println(g.generateParenthesis(2));
	}
}
