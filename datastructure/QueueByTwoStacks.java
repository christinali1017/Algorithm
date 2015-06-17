package datastructure;

import java.util.Stack;

/**
 * Use two stack to implement a queue
 * @author wish
 *
 */
public class QueueByTwoStacks {
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	
	public void enqueue(int x){
		stack1.push(x);
	}

	public int dequeue(){
		if(!stack2.isEmpty())
			return stack2.pop();
		else{
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
			if(!stack2.isEmpty())
				return stack2.pop();
		}
		System.out.println("queue is tempty");
		return -1;
	}
	
	public static void main(String[] args) {
		QueueByTwoStacks queue = new QueueByTwoStacks();
		queue.enqueue(2);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(8);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
	}
}
