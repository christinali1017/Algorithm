package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int start = 0;
        int current = 0;
        int max = 0;
        Set<Character> set = new HashSet<Character>();
        while(current < s.length()){
            if(set.contains(s.charAt(current))){
                max = Math.max(max, current - start);
                while(s.charAt(start) != s.charAt(current)){
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
            }else{
                set.add(s.charAt(current));
            }
            current++;
        }
        max = Math.max(current- start, max);
        return max;
    }
    
    public static void main(String[] args) {
    	LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
    	String s = "asljlj";
    	System.out.println(l.lengthOfLongestSubstring(s));
	}
}
