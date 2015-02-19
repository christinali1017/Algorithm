package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int[] result = new int[2];
    	for(int i = 0, len = numbers.length; i < len; i++){
    		int current = numbers[i];
    		if(map.containsKey(target-current)){
    			result[0] = map.get(target-current)+1;
    			result[1] = i+1;
    		}else map.put(current, i);
    	}
    	return result;
    }
    
    /* if we only want to know the numbers, not the index of them */
    public int[] twoSum1(int[] numbers, int target) {
		int[] result = new int[2];
		if(numbers == null || numbers.length <= 1) return result;
	    Arrays.sort(numbers);
	
		int l = 0;
		int  r = numbers.length -1;
		while(l < r){
			if(numbers[l] + numbers[r] == target){
				result[0] = numbers[l];
				result[1] = numbers[r];
				return result;
			}else if(numbers[l] + numbers[r] < target) l++;
			else r--;
		}   
			
		return result;	
    }    
    
    
    public static void main(String[] args) {
    	TwoSum t = new TwoSum();
    	int[] numbers = {1, 2, 2, 4};
    	int[] result = t.twoSum1(numbers, 5);
    	System.out.println(Arrays.toString(result));
	}

}
