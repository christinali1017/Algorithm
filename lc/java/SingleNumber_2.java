package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers, every element appears three times except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class SingleNumber_2 {
	
	/*
	 * no extra space, use bit manipulation.
	 * 
	 * every bit with number of 1 must be multiple of 3 without that number.
	 */
    public static int singleNumber(int[] A) {
    	int result = 0,
    		count = 0,
    		pos = 0;
    
    	for(int i = 0; i < 32; i++){
    		count = 0;
    		pos = 1 << i;
    		for(int j = 0, len = A.length; j < len; j++){
    			if((pos & A[j]) != 0) count++;
    		}
    		if((count % 3) != 0) result |= pos;
    	}
    	return result;
    }
	
	/*
	 * HashMap
	 */
    public static int singleNumber1(int[] A) {
    	if(A.length == 0) return -1;
    	if(A.length == 1) return A[0];
    	
    	int count = 0;
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i = 0, len = A.length; i < len; i++){
    		if(map.containsKey(A[i])){
    			count = map.get(A[i]);
    			map.put(A[i], count+1);
    		}else{
    			map.put(A[i], 1);
    		}
    	}
    	
    	for(Map.Entry<Integer, Integer> entry : map.entrySet()){
    		if(entry.getValue() != 3)
    			return entry.getKey();
    	}
    	
        return -1;
    }
    
    public static void main(String[] args) {
		int[] A = {1, 1, 1, 2, 3, 2, 2, 3, 3, 3, 4, 4, 5, 5, 4, 5};
		System.out.println(singleNumber(A));
	}

}
