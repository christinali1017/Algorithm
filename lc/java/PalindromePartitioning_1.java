package leetcode;

import java.util.ArrayList;
/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.

	Return all possible palindrome partitioning of s.
	
	For example, given s = "aab",
	Return

  	[
    ["aa","b"],
    ["a","a","b"]
  ]
 */
import java.util.List;

public class PalindromePartitioning_1 {
    public static List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<List<String>>();
        if(s.length() == 0 || s == null) return list;
        partitionHelper(s, 0, new ArrayList<String>(), list);
        return list;
    }
    
    public static void partitionHelper(String s,int start, ArrayList<String> result, List<List<String>> list){
    	if(start == s.length()){
    		list.add(result);
    		return;
    	}
    	StringBuilder sBuilder = new StringBuilder();
        for(int i = start, len = s.length(); i < len; i++){
    		sBuilder.append(s.charAt(i));
    		if(!isPalindrome(sBuilder.toString())) continue;
    		ArrayList<String> arr = new ArrayList<String>(result);
    		arr.add(sBuilder.toString());
            partitionHelper(s, i+1, arr, list);
    	}
    }
	
	public static boolean isPalindrome(String s){
		if(s.length() == 0 || s.length() == 1) return true;
		for(int i = 0, len = s.length()/2; i < len; i++){
			if(s.charAt(i) != s.charAt(s.length() - i - 1))
				return false;
		}
		return true;
				
	}
	
	public static void main(String[] args) {
		String s1 = "aba";
		List<List<String>> list = partition(s1);
		
		for(List<String> l : list)
			System.out.println(l);
	}

}
