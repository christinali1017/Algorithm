package sort;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
	public void bucketSort(int[] arr){
		int n = arr.length;
		if(n <= 0) return;
		int min = arr[0];
		int max = min;
		for(int i = 1; i < n; i++){
			if(arr[i] > max) max = arr[i];
			if(arr[i] < min) min = arr[i];
		}
		
		/* put element into bucket*/
		int bucket[] = new int[max - min + 1];
		for(int i = 0; i < n; i++){
			bucket[arr[i] -min]++;
		}
		
		int i = 0;
		for(int b = 0, len = bucket.length; b < len; b++){
			for(int j = 0; j < bucket[b]; j++)
				arr[i++] = b + min;
		}
		
	}
	
	
	/* float number */
	public void bucketSort(float[] arr, int n){
		if(n <= 0) return;
		@SuppressWarnings("unchecked")
		ArrayList<Float>[] bucket = new ArrayList[n];
		for(int i = 0; i < n; i++)
			bucket[i] = new ArrayList<Float>();
	
		/* add element to buckets */
		for(int i = 0; i < n; i++){
			int bucketIndex = (int) (arr[i] * n);
			bucket[bucketIndex].add(arr[i]);
		}
	
		/* sort element in bucket */
		for(int i = 0; i < n; i++){
			Collections.sort((bucket[i]));
		}
	
		/* concatenate all buckets */
		int index = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0, size = bucket[i].size(); j < size; j++){
				arr[index++] = bucket[i].get(j);
			}
	
		}
	}
	
	public static void main(String[] args) {
		BucketSort b = new BucketSort();
		float[] arr = {(float) 0.007,(float) 0.897, (float) 0.565, (float) 0.656, (float) 0.1234, (float) 0.665, (float) 0.3434};
		b.bucketSort(arr, 7);
		for(float i : arr)
			System.out.print(i + "  ");
		
		System.out.println();
		int[] arr1 = {2, 3, 5, 7, 1, 2, 6, 9, 10, 0, 11};
		b.bucketSort(arr1);
		for(int i : arr1)
			System.out.print(i + "  ");
	}
}
