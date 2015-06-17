package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        if(S == null || T == null ||  S.length() < T.length()) return "";
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < T.length(); i++){
        	if(map.containsKey(T.charAt(i))) map.put(T.charAt(i), map.get(T.charAt(i))+1);
        	else map.put(T.charAt(i), 1);
        }
        
        int start = 0;
        int count = 0;
        int min = S.length()+1;
        int minIndex = 0;
        for(int i = 0; i < S.length(); i++){
        	if(map.containsKey(S.charAt(i))) {
        		map.put(S.charAt(i), map.get(S.charAt(i))-1);
        		if(map.get(S.charAt(i)) >= 0) count++;
	        	while(count == T.length()){
	        		if(i-start+1 < min ){
	        			min = i-start+1;
	        			minIndex = start;
	        		}
	        		
	        		if(map.containsKey(S.charAt(start))){
	            		map.put(S.charAt(start), map.get(S.charAt(start))+1);
	            		if(map.get(S.charAt(start)) > 0)count--;
	            	}
	        		start++;
	        	}
	        	
        	}
        }
        if(min > S.length()) return "";
        return S.substring(minIndex, min+minIndex);
    }
    
    
    
    public static void main(String[] args) {
    	MinimumWindowSubstring m = new MinimumWindowSubstring();
    	System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));
	}
}
