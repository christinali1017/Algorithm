package questions;

class Node {
    int val;
    int r;
    int c;
    public Node(int val, int r, int c) {
      this.val = val;
      this.r = r;
      this.c = c;
    }
  }

public class MinHeap_findkth {
	  
    public int kthSmallest(int[][] matrix, int k) {
	    // Write your solution here
	    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	      return Integer.MIN_VALUE;
	    }
	    if (k <= 0 || k > matrix.length * matrix[0].length) {
	      return Integer.MIN_VALUE;
	    }
	    int heapsize = matrix[0].length;
	    Node[] arr = new Node[heapsize];
	    for (int i = 0; i < heapsize; i++) {
	      arr[i] = new Node(matrix[0][i], 0, i);
	    }
	    buildHeap(arr);
	    Node root = null;
	    for (int i = 0; i < k; i++) {
	      root = arr[0];
	      if (root.r < matrix.length - 1) {
	        arr[0] = new Node(matrix[root.r + 1][root.c], root.r + 1, root.c);
	      } else {
	        arr[0] = new Node(Integer.MAX_VALUE, root.r + 1, root.c);
	      }
	      heapify(arr, heapsize, 0);
	      
	    }
	    return root.val;
	  }
	  
	  private void heapify(Node[] arr, int size, int i) {
	    int l = i * 2 + 1;
	    int r = i * 2 + 2;
	    int smallest = i;
	    if (l < size && arr[l].val < arr[i].val) {
	      smallest = l;
	    }
	    if (r < size && arr[r].val < arr[smallest].val) {
	      smallest = r;
	    }
	    if (smallest != i) {
	      swap(arr, smallest, i);
	      heapify(arr, size, smallest);
	    }
	  }
	    
	  private void swap(Node[] arr, int i, int j) {
	    Node temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	  }
	  
	  private void buildHeap(Node[] arr) {
	    int n = arr.length;
	    int i = (n - 1) / 2;
	    while (i >= 0) {
	      heapify(arr, n, i);
	      i--;
	    }
	  }
	  
	public static void main(String[] args) {
		MinHeap_findkth min = new MinHeap_findkth();
		//		int[][] matrix = {  {1,  3,   5,  7}, 
		//		{2,  4,   8,   9},
		//		{3,  5, 11, 15},
		//		{6,  8, 13, 18} };
		int[][] matrix = {{1, 2, 3}, {2, 4, 5}};
		System.out.println(min.kthSmallest(matrix, 5));
	}
	  
}
