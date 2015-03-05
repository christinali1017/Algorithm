package leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * @author wish
 *
 */
public class MaximumSubarray {
	/* use local and global best */
	 public int maxSubArray(int[] A) {
		 if(A == null || A.length == 0) return 0;
		 int local = A[0];
		 int global = A[0];
		 for(int i = 1; i < A.length; i++){
			 local = Math.max(A[i], local+A[i]);
			 global = Math.max(local, global);
		 }
		 return global;
	 }
	 
    public int maxSubArray1(int[] A) {
        if(A == null || A.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int temp = A[0];
        for(int i = 1; i < A.length; i++){
        	if(temp < 0){
        		max = Math.max(max,temp);
        		temp = A[i];
        	}else{
        		if(A[i] > 0) temp += A[i];
        		else if(temp + A[i] < 0){
        			max = Math.max(max,temp);
            		temp = A[i];
        		}else{
        			max = Math.max(max,temp);
            		temp += A[i];
        		}
        	}
        }
        max = Math.max(max,temp);
        return max;
    }
    
    public static void main(String[] args) {
    	MaximumSubarray m = new MaximumSubarray();
    	int[] A = {-2,1,-3,4,-1,2,1,-5,4};
    	System.out.println(m.maxSubArray(A));
    	System.out.println(m.maxSubArray1(A));
	}

}
