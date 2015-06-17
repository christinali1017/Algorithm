package leetcode;

public class MaxProductSubarr {
	public static int max(int[] arr, int start, int end){
		int maxProduct = 1;
		int negativeCount = 0;
		int tempProductLeft = 1;
		int tempProductRight = 1;
		if(end < start) return 0;
		if(end == start) return arr[start];
		
		for(int i = start; i<= end; i++){
			if(arr[i] < 0){
				negativeCount ++;
			}
		}
		if((negativeCount & 1) != 0){
			int i, j;
			for(i = start; i<=end && arr[i]>0; i++);
			for(j = end; j>=start && arr[j]>0; j--);
			for(int begin = start; begin < j; begin++) tempProductLeft *= arr[begin];
			for(int begin = i+1; begin <= end; begin++) tempProductRight *= arr[begin];
			maxProduct = Math.max(tempProductLeft, tempProductRight);
		}else{
			for(int i = start; i <= end; i++)
				maxProduct *= arr[i];
			
		}
		return maxProduct;
	}
	
	public static int maxProduct(int[] arr){
		int maxP = Integer.MIN_VALUE;
		int tempMax = Integer.MIN_VALUE;
		int lastZero = -1;
		if(arr.length == 1) return arr[0];
		for(int i = 0, len=arr.length; i<len; i++){
			if(arr[i] == 0){
				tempMax = max(arr, lastZero+1, i-1);
				tempMax = Math.max(tempMax, max(arr, i+1, arr.length-1));
				maxP = Math.max(tempMax, maxP);
				lastZero = i;
			}
		}
		maxP = Math.max(max(arr, 0, arr.length-1), maxP);
		return maxP;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,0,0,0};
		int result = maxProduct(arr);
		System.out.println(result);
	}

}
