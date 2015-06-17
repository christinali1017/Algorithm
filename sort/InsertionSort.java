package sort;

public class InsertionSort {
	public void insertionSort(int[] arr){
		for(int i = 1, len = arr.length; i < len; i++){
			for (int j = i; j > 0; j--){
				if(arr[j] < arr[j-1])
					swap(arr, j, j-1);
			}
		}
	}

	public void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public static void main(String[] args) {
		InsertionSort h = new InsertionSort();
		int[] arr = {1, 3,2 ,5, 4, 7, 6, 9, 8, 8};
		h.insertionSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}

}
