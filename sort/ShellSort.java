package sort;

public class ShellSort {
	public void shellSort(int[] arr){
		int gap = arr.length/2;
		while(gap >= 1){
			sort(arr, gap);
			gap = gap/2;
		}
	}

	public void sort(int[] arr, int gap){
		for(int i = 1, len = arr.length; i < len; i = i + gap){
			for (int j = i; j > 0; j = j - gap){
				if(arr[j] < arr[j-1]){
					swap(arr, j, j-1);
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
		ShellSort h = new ShellSort();
		int[] arr = {1, 3,2 ,5, 4, 7, 6, 9, 8};
		h.shellSort(arr);
		for(int i : arr)
			System.out.print(i + "  ");
	}

}
