package sort;

public class SelectionSort {
	public void selectionSort(int[] arr){
		for(int i = 0, len = arr.length; i < len; i++){
			int minIndex = i;
			for(int j = i+1; j < len; j++){
				if(arr[j] < arr[minIndex]) minIndex = j;
			}
			swap(arr, i, minIndex);
		}
	}
	public void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	
	
	public static void main(String[] args) {
		SelectionSort s = new SelectionSort();
		int[] arr = {1, 3,2 ,5, 4, 7, 6, 9, 8};
		s.selectionSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}
}
