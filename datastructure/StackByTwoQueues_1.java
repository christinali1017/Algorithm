package datastructure;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Use two queues to implement a stack
 * Push O(1)
 * Pop O(n)
 * @author wish
 *
 */
public class StackByTwoQueues_1 {
	Queue<Integer> queue1 = new LinkedList<Integer>();
	Queue<Integer> queue2 = new LinkedList<Integer>();
	
	public void push(int x){
		queue1.offer(x);
	}
	
	public int pop(){
		if(!queue1.isEmpty()){
			while(queue1.size() > 1){
				queue2.offer(queue1.poll());
			}
			int x = queue1.poll();
			Queue<Integer> temp = queue1;
			queue1 =queue2;
			queue2 = temp;
			return x;
		}else{
			System.out.println("Stack is empty");
			return -1;
		}
	}
	
	public static void main(String[] args) {
		StackByTwoQueues_1 stack = new StackByTwoQueues_1();
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
