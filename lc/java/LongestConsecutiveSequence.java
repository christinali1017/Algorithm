package leetcode;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	
	Your algorithm should run in O(n) complexity.
 */

public class LongestConsecutiveSequence {
	/*
	 * remove elements from set which has been visited.
	 */
    public static int longestConsecutive(int[] num) {
    	if(num == null || num.length == 0) return 0;
    	if(num.length == 1) return 1;
    	
     	Set<Integer> seq = new HashSet<Integer>();
    	for(int i : num) seq.add(i);
    	
    	int longest = 1;
    	for(int i : num){
    		int count = 1;
    		int j = i;
    		seq.remove(i);
    		while(seq.contains(j-1)){
    			seq.remove(j-1);
    			count ++;
    			j --;
    		}
    		j = i;
    		while(seq.contains(j+1)){
    			seq.remove(j+1);
    			count ++;
    			j ++;
    		}
    		longest = Math.max(count, longest);
    	}
    	return longest;
    	
    }

	
	/*O(n * m), m is the longest consecutive sequence */
	/* Time limit exceeded */
    public static int longestConsecutive1(int[] num) {
    	if(num == null || num.length == 0) return 0;
    	if(num.length == 1) return 1;
    	
    	Set<Integer> seq = new HashSet<Integer>();
    	for(int i : num) seq.add(i);
    	
    	
    	int longest = 1;
    	for(int i : num){
    		int count = 1;
    		int j = i;
    		while(seq.contains(j-1)){
    			count ++;
    			j --;
    		}
    		j = i;
    		while(seq.contains(j+1)){
    			count ++;
    			j ++;
    		}
    		longest = Math.max(count, longest);
    	}
    	return longest;
    }

    public static void main(String[] args) {
		int[] num = {100, 4, 200, 1, 3, 2, 101, 201, 102, 202, 103, 99, 204};
		System.out.println(longestConsecutive(num));
	}
}
