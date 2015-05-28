package leetcode;

import java.util.Arrays;

public class Sum_3SumClosest {
    public int threeSumClosest(int[] num, int target) {
	    if(num == null || num.length == 0) return 0;
	    int res = Integer.MAX_VALUE;
	    Arrays.sort(num);
	    for(int i = 0; i <= num.length -3; i++){
	        if(i != 0 && num[i] == num[i-1]) continue;
	        int dif = twoSumClosest(num, i + 1, target-num[i]);
	        if(Math.abs(dif) < Math.abs(res)) res = dif;
	    }
	    return res+target;
	}
	
	public int twoSumClosest(int[] num, int start, int target){
	    int r = num.length - 1;
	    int l = start;
	    int res = Integer.MAX_VALUE;
	    while(l < r){
	        if(Math.abs(num[l] + num[r] - target) < Math.abs(res))
	            res = num[l] + num[r] - target;
	        if(num[l] + num[r] == target)
	            return 0;
	        else if(num[l] + num[r] > target)
	            r--;
	        else 
	            l++;
	        
	    }
	    return res;
	}
	    
	    public static void main(String[] args) {
	    	Sum_3SumClosest s = new Sum_3SumClosest();
	    	int[] num = {2, -1, -1, 0, 0, 0, 0, 1, 3, -2 };
	    	System.out.println(s.threeSumClosest(num, 0));
		}

}
