package sort;

public class HeapSort {
	public void heapSort(int[] arr){
		buildHeap(arr);
		int size = arr.length;
		for(int i = 0, len = size; i < len-1; i++ ){
			swap(arr, 0, size-1);
			size--;
			heapifyDown(arr, size, 0);
		}
	}

	public void buildHeap(int[] arr){
		for(int i = arr.length/2-1; i >= 0; i--)
		heapifyDown(arr, arr.length, i);
	}

	public void heapifyDown(int[] arr, int size, int i){
		int left = 2 * i +1;
		int right = 2 * i + 2;
		int largest = 0;

		if(left < size && arr[left] > arr[i])
			largest = left;
		else largest = i;
		if(right <  size && arr[right] > arr[largest])
			largest = right;

		if( i != largest){
			swap(arr, i, largest);
			heapifyDown(arr, size, largest);
		}
	}

	public void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}


	
	public static void main(String[] args) {
		HeapSort h = new HeapSort();
		int[] arr = {1, 3,2 ,5, 4, 7, 6, 9, 8};
		h.heapSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}
}
