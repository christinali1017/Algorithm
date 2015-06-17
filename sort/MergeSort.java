package sort;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSort {
	public void mergeSort(int[] arr, int start, int end){
		if(arr == null || arr.length == 0) return;
		
		if(start >= end) return;
		int mid = (start + end)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		merge(arr, start, mid, end);
	}

	public void merge(int[] arr, int start, int mid,  int end){
		Queue<Integer> queue = new LinkedList<Integer>();
		int i = start, 
		    j = mid +1;
		while(i <= mid && j <= end){
			if(arr[i] > arr[j])
				queue.offer(arr[j++]);
			else
				queue.offer(arr[i++]);
		}
		
		while(i <= mid)
			queue.offer(arr[i++]);

		while(j <= end)
			queue.offer(arr[j++]);
		
		while(!queue.isEmpty())
			arr[start++] = queue.poll();
		
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 1, 3, 5, 4, 6, 7, 9, 8, 8};
		MergeSort ms = new MergeSort();
		ms.mergeSort(arr, 0, arr.length-1);
		for(int i : arr)
			System.out.print(i + "  ");
	}

}
