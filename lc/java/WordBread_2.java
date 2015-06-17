package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBread_2 {
	public static List<String> wordBreak(String s, Set<String> dict) {
		List<String> list = new ArrayList<String>();
		if(s == null || dict == null || s.length() == 0) return list;
		boolean[] arr = new boolean[s.length() + 1];
		for (int i = 0; i < s.length()+1; i ++) arr[i] = true;
		
		StringBuilder sbuilder = new StringBuilder();
    	wordBreakChecker(s, dict, 0, sbuilder, list, arr);
    	return list;
		
	}
	
	  public static void wordBreakChecker(String s, Set<String> dict, int start, StringBuilder result, List<String> list, boolean[] arr ){
		  if(start >= s.length()){
	        	list.add(result.toString().trim());
	        	return;
	        }
	        
	        int listSize = 0;
	        for(int i = start, len = s.length(); i < len; i++){
	        	if(arr[i] = false) continue; 
	        	String current = s.substring(start, i+1);
	        	
	        	if(dict.contains(current)){
	        		result.append(current + " ");
	        		//result = result.length() > 0 ? (result.append(" " +current)) : result.append(current);
	        		listSize = list.size();
	        		wordBreakChecker(s, dict, i+1, result, list, arr);
	        		if(listSize == list.size()) arr[i] = false;
	        		result.setLength(result.length() - current.length() - 1);
	        	}
	        }  
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
	    	
	    	List<String> list = wordBreak(s, dict);
	    	
	    	for(String str : list)
	    		System.out.println(str);
	    	
		}
//    public static List<String> wordBreak(String s, Set<String> dict) {
//		List<String> list = new ArrayList<String>();
//	   	if(s == null || dict == null) return list;
//	   	List<ArrayList<String>> listString = wordBreakHelper(s, dict);
//	   	for(ArrayList<String> arr : listString) System.out.println(arr);
//	   	for(int i = 0, len = listString.size(); i < len; i++){
//	   		if(listString.get(i) == null) continue;
//	   		StringBuffer sb = new StringBuffer();
//	   		if(listString.get(i).size() == 1) sb.append(" "+listString.get(i).get(0));
//	   		else{
//	   			//StringBuffer sbReplicate = s;
//	   			
//	   		}
//	   		
//	   	}
//	 
//		return list;
//    }
//    
//    public static List<ArrayList<String>> wordBreakHelper(String s, Set<String> dict){
//    	List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//    	for(int i = 0; i <= s.length(); i++)
//    		list.add(i, null);
//    	boolean[] arr = new boolean[s.length() + 1];
//    	arr[0] = true;
//    	int endPos = 0;
//    	
//    	for(int i = 0, len = s.length(); i < len; i++){
//    		if(arr[i] == false) continue;
//    		for(String a : dict){
//    			ArrayList<String> strs = new ArrayList<String>(); 
//    			endPos = i + a.length();
//    			if(endPos > len) continue;
//    			if(s.substring(i, endPos).equals(a)){
//    				arr[endPos] = true;
//    				if(list.get(endPos) == null){
//    					list.remove(endPos);
//    					strs.add(a);
//    					list.add(endPos,strs);
//    				}else{
//    					list.get(endPos).add(a);
//    				}
//    			}  
//    					
//    		}
//    	}
//    	return list;
//    }

    
    


}
