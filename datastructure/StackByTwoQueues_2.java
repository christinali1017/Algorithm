package datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Use two queues to implement a stack
 * Push O(n)
 * Pop  O(1)
 * @author wish
 *
 */
public class StackByTwoQueues_2 {
	Queue<Integer> queue1 = new LinkedList<Integer>();
	Queue<Integer> queue2 = new LinkedList<Integer>();
	
	public void push(int x){
		queue2.offer(x);
		while(!queue1.isEmpty()){
			queue2.offer(queue1.poll());
		}
		Queue<Integer> temp = null;
		temp = queue1;
		queue1 = queue2;
		queue2 = temp;
	}
	
	public int pop(){
		if(!queue1.isEmpty())
			return queue1.poll();
		else{
			System.out.println("Stack is empty");
			return -1;
		}
	}
	
	public static void main(String[] args) {
		StackByTwoQueues_2 stack = new StackByTwoQueues_2();
		stack.push(2);
		stack.push(4);
		stack.push(5);
		stack.push(8);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}
}
