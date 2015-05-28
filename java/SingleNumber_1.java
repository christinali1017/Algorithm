package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers, every element appears twice except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber_1 {
	/*
	 * no extra space
	 * use XOR
	 * A ^ A = 0
	 * A ^ B ^ A = B
	 */
	public static int singleNumber(int[] A){
		int result = A[0];
		for(int i = 1, len = A.length; i < len; i++)
			result = result ^ A[i];
		return result;
	}
	
	/*
	 * use Hashmap
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
    		if(entry.getValue() == 1)
    			return entry.getKey();
    	}
    	
        return -1;
    }
    
    public static void main(String[] args) {
		int[] A = {1, 1, 2, 3, 5, 4, 3, 5, 2};
		System.out.println(singleNumber(A));
	}
}
