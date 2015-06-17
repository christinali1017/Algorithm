package leetcode;

public class MaximumProductSubArray {
	public int maxProduct(int[] A){
		if(A == null || A.length == 0) return 0;
		int global = A[0];
		int localMin = A[0];
		int localMax = A[0];
		for(int i = 1; i < A.length; i++){
			int temp = localMax;
			localMax = Math.max(Math.max(A[i], localMax*A[i]), localMin*A[i]);
			localMin = Math.min(Math.min(A[i], localMin * A[i]), temp * A[i]);
			global = Math.max(global, localMax);
		}
		return global;
	}
	
	public static void main(String[] args) {
		int[] A = {1, 2, 3, -1, 10, 2, -6, -2};
		MaximumProductSubArray m = new MaximumProductSubArray();
		System.out.println(m.maxProduct(A));
	}
}
