package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> res = new  ArrayList<String>();
    	if(s.length() <= 10) return res;
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	
    	int current = 0,
    		i = 0;
    	while(i < 9)
    		current = (current << 3) | (s.charAt(i++) & 7);
    	while(i < s.length()){
    		current = ((current & 0x7FFFFFF) << 3) | (s.charAt(i++) & 7);
    		if(map.containsKey(current)){
    			// make sure not add the duplicate
    			if(map.get(current) == 1)
    				res.add(s.substring(i-10, i));
    			map.put(current, map.get(current)+1);
    			
    		}else
    		    map.put(current, 1);
    	}
        return res;
    }
    
    
    
    public static void main(String[] args) {
    	RepeatedDNASequence r = new RepeatedDNASequence();
    	System.out.println(r.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
	}
	

}
