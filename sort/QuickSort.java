package sort;

public class QuickSort {
//	public void quickSort(int[] arr, int start, int end){
//		if(start > end) return;
//		int partition = partition(arr, start, end);
//		quickSort(arr, start, partition-1);
//		quickSort(arr, partition+1, end);
//	}
//
//	public int partition(int[] arr, int start, int end){
//		int mid = (start + end)/2;
//		swap(arr, mid, end);
//		int lower = start;
//		while(start < end){
//			if(arr[start] < arr[end])
//				swap(arr, lower++, start);
//			start++;
//			
//		}
//		swap(arr, lower, end);
//		return lower;
//	}
//	
//	public int partition2(int[] arr, int start, int end){
//		int mid = (start + end)/2;
//		swap(arr, mid, end);
//		int x = arr[end];
//		int i = start;
//		int j = end;
//		while(i < j){
//			/* we swap mid with end, then start from left 
//			 * if we swap mid with left, then we need to start from right*/
//			
//			while(i < j && arr[i] <= x) i++;
//			swap(arr, i, j);
//			while(i < j && arr[j]>=x) j--;
//			swap(arr, i, j);
//		}
//		return i;
//		
//	}
//
//	public void swap(int[] arr, int index1, int index2){
//		int temp = arr[index1];
//		arr[index1] = arr[index2];
//		arr[index2] = temp;
//	}

	  public int[] quickSort(int[] array) {
		    // Write your solution here
		    if (array == null || array.length == 0) {
		      return array;
		    }
		    quickSort(array, 0, array.length - 1);
		    return array;
		  }
		  
		  public void quickSort(int[] array, int start, int end) {
		    if (start >= end) {
		      return;
		    }
		    int index = partition(array, start, end);
		    quickSort(array, start, index - 1);
		    quickSort(array, index + 1, end);
		  }
		  
		  public int partition(int[] array, int start, int end) {
		    int i = start;
		    while(i < end) {
		      if (array[i] <= array[end]) {
		        swap(array, i, start++);
		      }
		      i++;
		    }
		    swap(array, start, end);
		    return start;
		  }
		  public void swap(int[] array, int i, int j) {
			    int temp = array[i];
			    array[i] = array[j];
			    array[j] = temp;
			  }
	public static void main(String[] args) {
		int[] arr = {4, 2, 1, 6, 3, 5};
		QuickSort qs = new QuickSort();
		qs.quickSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}
}
