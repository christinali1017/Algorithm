package datastructure;

public class LinkedList_ImplementByTwoArray {
	/* store value */
	int[] value;
	
	/* store next point*/
	int[] next;
	
	/* the head of free list*/
	int empty = 0; 
	
	/*length of linked list */
	int length = 0;
	
	/* head of linked list */
	int head = -1;
	
	/**
	 * @param capacity
	 */
	LinkedList_ImplementByTwoArray(int capacity){
		this.value = new int[capacity];
		this.next = new int[capacity];
		
		/*initialize next array */
		for(int i = 0; i < capacity-1; i++){
			next[i] = i+1;
		}
		next[capacity-1] = -1;
	}
	
	/**
	 * @return an index from the free list
	 * if free list has no spot, then return -1
	 */
	private int newNode(){
		if(empty == -1)
			return -1;
		else{
			int temp = empty;
			empty = next[empty];
			return temp;
		}
	}
	
	/**
	 * Add a node to linked list, value is x
	 * @param x
	 */
	public void add(int x){
		int index = newNode();
		if(index == -1){
			System.out.println("Array is full");
			return;
		}else{
			value[index] = x;
			next[index] = head;
			head = index;
			length++;
		}
	}
	
	/**
	 * Remove node with value x from linked list
	 * @param x
	 */
	public void remove(int x){
		if(head == -1){
			System.out.println("Linked List is empty, can not remove node");
			return;
		}else if(value[head] == x){
			int temp = head;
			head = next[head];
			next[temp] = empty;
			empty = temp;
			length--;
		}else{
			int prev = head;
			int current = next[head];
			while(current != -1 && value[current] != x){
				prev = next[prev];
				current = next[current];
			}
			if(value[current] == x){
				next[prev] = next[current];
				next[current] = empty;
				empty = current;
				length--;
			}else
				System.out.println("Cannot find value x");
		}
	}
	
	public void print(){
		int temp = head;
		while(temp != -1){
			System.out.print(value[temp] + "-->");
			temp = next[temp];
		}
	}
	
	public static void main(String[] args) {
		LinkedList_ImplementByTwoArray list = new LinkedList_ImplementByTwoArray(5);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		System.out.println("---------------");
		list.print();
		
		System.out.println("\n---------------");
		list.remove(3);
		list.print();

		System.out.println("\n-------------");
		list.add(7);
		list.print();
		
		System.out.println("\n-------------");
		list.add(8);
	}
	
	
}
