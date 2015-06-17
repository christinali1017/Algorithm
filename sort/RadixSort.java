package sort;

import java.util.LinkedList;

public class RadixSort {
	/* base 10, radix sort  */
	public void radixSort(int[] arr, int maxDigit){
		/* initialize bucket */
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] bucket = new LinkedList[10];
		for(int i = 0; i < 10; i++)
			bucket[i] = new LinkedList<Integer>();
		
		int mod = 10;
		int divide = 1;
		for(int i = 0; i < maxDigit; i++, divide *= 10, mod *= 10){
			/* put element to bucket */
			for(int j = 0, len = arr.length; j < len; j++){
				int bucketIndex = (arr[j] % mod)/divide;
				bucket[bucketIndex].offer(arr[j]);
			}
			
			int pos = 0;
			for(int j = 0, len = bucket.length; j < len; j++){
				Integer val = null;
				while((val = bucket[j].poll()) != null)
					arr[pos++] = val;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		int[] arr = {1, 22, 12, 44, 111, 1430};
		rs.radixSort(arr, 4);
		for(int i : arr)
			System.out.print(i + "  ");
		
	}


}
