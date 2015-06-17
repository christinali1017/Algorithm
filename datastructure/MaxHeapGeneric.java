package datastructure;

import java.util.Comparator;

/**
 * 
 * Property of heap:
 * 		1) complete binary tree
 * 		2) node is larger than its children if it's a max heap
 * 
 * @author wish
 * @version December 9, 2014
 */
public class MaxHeapGeneric<E extends Comparable<? super E>>{
	/**
	 * 
	 */
	private transient Object[] heap;
	
	/* The size of heap*/
	private int heapsize;
	
	/* Capacity of heap*/
	private int capacity;
	
	private final static int defualtInitialCapacity = 1000;
	
	private final Comparator<? super E> comparator;

	public MaxHeapGeneric(){
		this(defualtInitialCapacity, null);
	}
	
	public MaxHeapGeneric(int capacity){
		this(capacity, null);
	}
	
	public MaxHeapGeneric(int capacity,  Comparator<? super E> comparator){
		this.heap = new Object[capacity];
		this.comparator = comparator;
	}
	
	public MaxHeapGeneric( int capacity, int heapsize, E[] heap){
		this(capacity,heapsize, heap, null);
	}
	
	/**
	 * Constructor, create a max heap object
	 * @param heapsize
	 * @param capacity
	 * @param heap
	 */
	public MaxHeapGeneric(int capacity, int heapsize, E[] heap, Comparator<? super E> comparator ){
		this.heapsize = heapsize;
		this.capacity = capacity;
		this.heap = heap;
		this.buildMaxHeap();
		this.comparator = comparator;
	}
	
	
	/**
	 * Building a heap 
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
	@SuppressWarnings("unchecked")
	private void maxHeapifyDown(int i){
		if(isLeaf(i)) return;
		int left = leftChildIndex(i),
			right = rightChildIndex(i),
			largest = 0;
		
		if(left < heapsize && (comparator.compare((E)heap[left], (E)heap[i]) > 0))
			largest = left;
		else largest = i;
		
		if(right < heapsize && (comparator.compare((E)heap[left], (E)heap[largest]) > 0))
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
	@SuppressWarnings("unchecked")
	public void insert(E element){
		if(capacity <= heapsize)
			throw new IllegalArgumentException("Heap is full, can not insert new elemnet");
		
		int currentIndex = heapsize++;
		heap[currentIndex] = element;
		
		while(comparator.compare((E)heap[currentIndex], (E)heap[parentIndex(currentIndex)]) > 0){
			swap(currentIndex, parentIndex(currentIndex));
			currentIndex = parentIndex(currentIndex);
		}
	}
	
	/**
	 * @param index
	 * @return the element at index
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index){
		if(index < 0 || index > heapsize-1)
			throw new IllegalArgumentException("Invalid index");
		
		if(index == heapsize -1){
			heapsize --;
			return (E) heap[heapsize];
		}
		
		/* swap the target element with the last element in the heap*/
		swap(index, --heapsize);
		
		/* flow down element in the target index */
		maxHeapifyDown(index);
		
		return (E) heap[heapsize];
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
		@SuppressWarnings("unchecked")
		E temp = (E)heap[i];
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
	
	
}
