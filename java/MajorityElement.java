package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] num) {
       Map<Integer, Integer> map = new HashMap<Integer, Integer>();
       if(num.length == 1) return num[0];
       for(int i = 0; i < num.length; i++){
    	   if(!map.containsKey(num[i])) map.put(num[i], 1);
    	   else{
    		   int count = map.get(num[i]) + 1;
    		   if(count > num.length/2 ) return num[i];
    		   map.put(num[i], map.get(num[i]) + 1);
    	   }
       }
       return 0;
    }
    
    public int majorityElement1(int[] num) {
    	Arrays.sort(num);
    	return num[num.length/2];
    } 
    
    public int majorityElement2(int[] num){
    	if(num == null || num.length == 0) return -1;
    	int count = 1;
    	int candidate = num[0];
    	for(int i = 1; i < num.length; i++){
    		if(num[i] != candidate) count--;
    		else count++;
    		if (count == 0){
    			count = 1;
    			candidate = num[i];
    		}
    	}
    	return candidate;
    }
    
    
    public static void main(String[] args) {
    	MajorityElement m = new MajorityElement();
    	int[] num = {2, 2, 3, 3, 1, 4, 5, 2, 2, 2, 2};
    	System.out.println(m.majorityElement2(num));
	}
}
