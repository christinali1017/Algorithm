package leetcode;

import java.util.HashSet;
import java.util.Set;

public class WordBreak_1 {
	
	/*
	 * Dynamic Programming
	 * Use arr[i] to store the result before i. That is, if 0~i-1 can be represent use words in dict, 
	 * then arr[i] = true
	 * 
	 */
    public static boolean wordBreak(String s, Set<String> dict) {
    	if(s == null || dict == null) return false;
    	boolean[] arr = new boolean[s.length() + 1];
    	arr[0] = true;
    	int endPos = 0;
    	
    	for(int i = 0, len = s.length(); i < len; i++){
    		if(arr[i] == false) continue;
    		for(String a : dict){
    			endPos = i + a.length();
    			if(endPos > len) continue;
    			if(arr[endPos] == true) continue;
    			if(s.substring(i, endPos).equals(a))  
    				arr[endPos] = true;
    		}
    	}
    	return arr[s.length()];
    }
    
	
	/*
	 * Time limit exception 
	 * 
	 * Recursive
	 */
    public static boolean wordBreak1(String s, Set<String> dict) {
    	if(s == null || dict == null) return false;
    	return wordBreakChecker(s, dict, 0);
    }
    
    public static boolean wordBreakChecker(String s, Set<String> dict, int start){
       if(start == s.length()) return true;
       
        String subs;
        for(String a : dict){
        	if(start + a.length() > s.length()) continue;
        	subs = s.substring(start, start + a.length());
        	if(a.equals(subs)){
        		if(wordBreakChecker(s, dict, start+a.length())) return true;
        	}
        }
    	return false;
    }
    
    public static void main(String[] args) {
    	Set<String> dict = new HashSet<String>();
    	dict.add("a");
    	dict.add("leet");
    	dict.add("code");
    	dict.add("aside");
    	dict.add("side");
    	dict.add("above");
    	
    	String s = "asidesideaabove";
    	
    	System.out.println(wordBreak(s, dict));
    	
	}
    

}
