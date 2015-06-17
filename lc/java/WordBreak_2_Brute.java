package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak_2_Brute {
    public static List<String> wordBreak(String s, Set<String> dict) {
		List<String> list = new ArrayList<String>();
    	if(s == null || dict == null || s.length()==0) return list;
    	wordBreakChecker(s, dict, 0, "", list);
    	return list;
    }
    
    public static void wordBreakChecker(String s, Set<String> dict, int start, String result, List<String> list ){
        if(start == s.length()){
        	list.add(result);
        	return;
        }
        
        StringBuilder str = new StringBuilder();
        for(int i = start, len = s.length(); i < len; i++){
        	str.append(s.charAt(i));
        	if(dict.contains(str.toString())){
        		String newS = result.length() > 0 ? (result + " " + str.toString()) : str.toString();
        		wordBreakChecker(s, dict, i+1, newS, list);
        	}
        }  
     }
    
    
//    public List<String> wordBreak(String s, Set<String> dict) {
//        Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();
//
//        // initialize the valid values
//        List<String> l = new ArrayList<String>();
//        l.add("");
//        validMap.put(s.length(), l);
//
//        // generate solutions from the end
//        for(int i = s.length() - 1; i >= 0; i--) {
//            List<String> values = new ArrayList<String>();
//            for(int j = i + 1; j <= s.length(); j++) {
//                if (dict.contains(s.substring(i, j))) {
//                    for(String word : validMap.get(j)) {
//                        values.add(s.substring(i, j) + (word.isEmpty() ? "" : " ") + word);
//                    }
//                }
//            }
//            validMap.put(i, values);
//        }
//        return validMap.get(0);
//    }
    public static void main(String[] args) {
    	Set<String> dict = new HashSet<String>();
    	dict.add("a");
    	dict.add("leet");
    	dict.add("code");
    	dict.add("aside");
    	dict.add("side");
    	dict.add("above");
    	
    	String s = "asidesideaabove";
    	
    	List<String> list = wordBreak(s, dict);
    	
    	for(String str : list)
    		System.out.println(str);
    	
	}
}
