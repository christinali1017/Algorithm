package sort;

public class BubbleSort {
	public void bubbleSort(int[] arr){
		boolean swap = true;
		while(swap){
			swap = false;
			for(int i = 0, len = arr.length; i < len-1; i++){
				if(arr[i] > arr[i+1]) {
					swap(arr, i, i+1);
							swap = true;
			 	}
			}
		}
	}
	
	public void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static void main(String[] args) {
		BubbleSort b = new BubbleSort();
		int[] arr = {1, 3,2 ,5, 4, 7, 6, 9, 8};
		b.bubbleSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}

}
