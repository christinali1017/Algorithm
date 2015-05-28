package leetcode;

import java.util.LinkedList;

public class MaximumGap {
	/*
	 * Try to solve it in linear time/space.

	Return 0 if the array contains less than 2 elements.

	You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range
	 */
    public int maximumGap(int[] num) {
        if(num == null || num.length <= 1) return 0;
        @SuppressWarnings("unchecked")
		LinkedList<Integer>[] bucket = new LinkedList[10];
        for(int i = 0; i <10; i++)
        	bucket[i] = new LinkedList<Integer>();
        int mod = 10,
        	divider = 1;
        
        /* sort the array use radix sort*/
        for(int i = 0; i < 10; i++, mod *= 10, divider *= 10){
        	for(int j = 0; j < num.length; j++){
        		bucket[(num[j] % mod)/divider].add(num[j]);
        	}
        	int pos = 0;
        	for(int j = 0; j < 10; j++){
        		while(bucket[j].peek() != null)
        			num[pos++] = bucket[j].poll();
        	}
        }
        
        /* find the maximumGap */
        int max = 0;
        for(int i = 1; i < num.length; i++){
        	max = Math.max (max, Math.abs(num[i] - num[i-1]));
        }
        return max;
    }
    
    public static void main(String[] args) {
    	MaximumGap m = new MaximumGap();
    	int[] num = {21, 3, 5, 2, 7, 9, 10, 22, 33, 44, 37};
    	System.out.println(m.maximumGap(num));
	}
}
