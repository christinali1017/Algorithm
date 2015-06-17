package leetcode;

import java.util.ArrayList;
/*
	Given a string s, partition s such that every substring of the partition is a palindrome.
	
	Return the minimum cuts needed for a palindrome partitioning of s.
	
	For example, given s = "aab",
	Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
import java.util.List;

public class PalindromePartitioning_2_1 {
	
	/*
	 * time limit exceeded
	 */
	public static int minCut(String s) {
		if(s == null || s.length() == 0) return 0;
		List<List<String>> list = new ArrayList<List<String>>(); 
		list = partition(s);
		int min = Integer.MAX_VALUE;
		for(List<String> l : list){
			if(l.size()-1 < min)
				min = l.size() - 1;
		}
		return min;
	}
    public static List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<List<String>>();
        if(s == null || s.length() == 0 ) return list;
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
		String s1 = "abaababc";
		List<List<String>> list = partition(s1);
		int min = minCut(s1);
		for(List<String> l : list)
			System.out.println(l);
		System.out.println();
		System.out.println(min);
	}

}
