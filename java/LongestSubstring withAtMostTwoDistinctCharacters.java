package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringCantions2UniqueCharacters {
	/*
	 * This is a problem asked by Google.
	 Problem
	 Given a string, find the longest substring that contains only two unique characters.
	 Given a string, find the length of the longest substring T that contains at most 2 distinct characters. 
	 For example, given "abcbbbbcccbdddadacb",
	 the longest substring that contains 2 unique character is "bcbbbbcccb".
	 */
	
	/* O(n) map contains only two elements. Here, we can use two variables to replace the hashmap, if we extend the problem to k characters, then hashmap maybe more convenient */
	public String subString(String s){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			//check for string contains <= 2 characters. Also checking for longest substring ends at the end of string 
			if(i == s.length()-1 && map.size() <= 2 && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == 2 && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		
		return s.substring(start, end+1);
	}
	
	/* contains at most 2 unique charcters */
	public String subString(String s, int k){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			/*check for string contains <= 2 characters.
			  && check for longest substring ends at the end of string 
			*/
			if(i == s.length()-1 && map.size() <= k && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == k && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		return s.substring(start, end+1);
	}
	
	public static void main(String[] args) {
		LongestSubstringCantions2UniqueCharacters l = new LongestSubstringCantions2UniqueCharacters();
		System.out.println(l.subString("cccccccaa"));
		System.out.println(l.subString("abbcceeabbggggggggnmc", 4));
	}

}
