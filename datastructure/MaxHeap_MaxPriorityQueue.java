package datastructure;

/**
 * 
 * Property of heap:
 * 		1) complete binary tree
 * 		2) node is larger than its children if it's a max heap
 * 
 * @author wish
 * @version December 9, 2014
 */
public class MaxHeap_MaxPriorityQueue{
	private int[] heap;
	private int heapsize;
	private int capacity;
	
	public MaxHeap_MaxPriorityQueue(int capacity){
		this.heapsize = 0;
		this.capacity = capacity;
		heap = new int[capacity];
	}
	
	
	public MaxHeap_MaxPriorityQueue(int capacity, int[] heap ){
		this.heapsize = heap.length;
		this.capacity = capacity;
		this.heap = new int[capacity];
		for(int i = 0; i < heapsize; i++)
			this.heap[i] = heap[i];
		this.heap = heap;
		this.buildMaxHeap();
	}
	
	
	/**
	 * Constructor, create a max heap object
	 * @param heapsize
	 * @param capacity
	 * @param heap
	 */
	public MaxHeap_MaxPriorityQueue(int heapsize, int capacity, int[] heap ){
		if(heapsize > heap.length)
			throw new IllegalArgumentException("Invalid heap and heapsize, heapsize should less than or equal with the length of heap");
		this.heapsize = heapsize;
		this.capacity = capacity;
		this.heap = new int[capacity];
		for(int i = 0; i < heapsize; i++)
			this.heap[i] = heap[i];
		this.heap = heap;
		this.buildMaxHeap();
	}
	
	
	/**
	 * Building a max heap 
	 */
	private void buildMaxHeap(){
		for(int i = heapsize/2 - 1; i >= 0; i--){
			maxHeapifyDown(i);
		}
	}
	
	
	/**
	 * Let E at index i float down in the max heap so that the subtree rooted at
	 * index i obeys the max heap property.
	 * 
	 * @param heap
	 * @param i
	 */
	private void maxHeapifyDown(int i){
		if(isLeaf(i)) return;
		int left = leftChildIndex(i),
			right = rightChildIndex(i),
			largest = 0;
		
		if(left < heapsize && (heap[left] > heap[i]))
			largest = left;
		else largest = i;
		
		if(right < heapsize && (heap[right] > heap[largest]))
			largest = right;
		
		if(largest != i){
			swap(i, largest);
			maxHeapifyDown(largest);
		}
		
	}
	
	/**
	 * Insert a new element into max heap
	 * 1) First put element in the end of the heap
	 * 2) Flow up the element by comparing with its parent node.
	 * @param element
	 */
	public void insert(int element){
		if(capacity <= heapsize)
			throw new IllegalArgumentException("Heap is full, can not insert new elemnet");
		
		int currentIndex = heapsize++;
		heap[currentIndex] = element;
		
		while(currentIndex != 0 && heap[currentIndex] > heap[parentIndex(currentIndex)]){
			swap(currentIndex, parentIndex(currentIndex));
			currentIndex = parentIndex(currentIndex);
		}
	}
	
	/**
	 * @param index
	 * @return the element at index
	 */
	public int remove(int index){
		if(index < 0 || index > heapsize-1)
			throw new IllegalArgumentException("Invalid index");
		
		if(index == heapsize -1){
			heapsize --;
			return heap[heapsize];
		}
		
		/* swap the target element with the last element in the heap*/
		swap(index, --heapsize);
		
		/* flow down element in the target index */
		maxHeapifyDown(index);
		
		return heap[heapsize];
	}
	
	/**
	 * @return maximum element of heap
	 */
	public int maximum(){
		if(heap == null || heap.length <= 0){
			System.out.println("Heap underflow");
			return -1;
		}
		return heap[0];
	}
	
	/**
	 * Delete maximum element from heap
	 * @return maximum element of heap
	 */
	public int extractMax(){
		if(heap == null || heap.length <= 0){
			System.out.println("Heap underflow");
			return -1;
		}
		
		int max = heap[0];
		swap(0, heapsize-1);
		heapsize--;
		maxHeapifyDown(0);
		return max;
	}
	
	
	
	/**
	 * @param index
	 * @return if element at index is a leaf node
	 */
	public boolean isLeaf(int index){
		return index >= (heapsize/2) && (index < heapsize);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < heapsize; i++){
			str.append(heap[i] + " ");
		}
		return str.toString();
	}
	
	
	/**
	 * Swap two elements in heap array
	 * @param heap
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	
	/**
	 * @param index
	 * @return index of left child
	 */
	private int leftChildIndex(int index){
		if(index > (heapsize - 1)/2)
			throw new IllegalArgumentException("Index is not valid, element at index does not has a left child");
		return 2*index + 1;
	}
	
	
	/**
	 * @param index
	 * @return index of right child
	 */
	private int rightChildIndex(int index){
		if(index > (heapsize - 2)/2)
			throw new IllegalArgumentException("Index is not valid, element at index does not has a right child");
		return 2*index + 2;
	}
	
	
	/**
	 * @param index
	 * @return index of parent
	 */
	private int parentIndex(int index){
		if(index <= 0)
			throw new IllegalArgumentException("Index is not valid, element at index does not has parent");
		return (index -1)/2;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[100];
		int[] arr1 = {2, 3, 5, 1, 4, 0};
		for (int i : arr1)
			arr[i] = i;
		MaxHeap_MaxPriorityQueue heap = new MaxHeap_MaxPriorityQueue(6, 100, arr);
		System.out.println("--------------test build heap---------");
		System.out.println(heap);
		
		System.out.println("--------------test insert element------");
		heap.insert(333);
		heap.insert(22);
		heap.insert(34);
		System.out.println(heap);
		
		System.out.println("--------------test remove element------");
		heap.remove(0);
		System.out.println(heap);
		
		System.out.println("--------------test is leaf-------------");
		System.out.println(heap.isLeaf(4));
		
		System.out.println("--------------test maximium-------------");
		System.out.println(heap.maximum());
		
		System.out.println("--------------test extractMaximum-------------");
		System.out.println(heap.extractMax());
		System.out.println(heap);
		
		
	}
	
}
