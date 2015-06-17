package datastructure;

import java.util.Stack;

/* 
 * Memory limits (The min stack should not have same number of elements with the stack. Just keep track of the smallest elements)
 * 
 * Duplicate elements. (less or equal than, push into min stack).
 * 
 */

public class MinStack {
    static Stack<Integer> stack;
	static Stack<Integer> minStack;
	public MinStack_Stack(){
	    stack = new Stack<Integer>();
	    minStack = new Stack<Integer>();
	}
	public void push(int x){
		stack.push(x);
		if(minStack.isEmpty() || minStack.peek() >= x)
			minStack.push(x);
	}
	
	public void pop(){
		if(minStack.peek().equals(stack.peek()))
			minStack.pop();
		stack.pop();
	}
	
	public int top(){
		return stack.peek();
	}
	
	public int getMin(){
		return minStack.peek();
		
	}
	
	public static void main(String[] args) {
		MinStack s = new MinStack();
		s.push(10);
		s.push(20);
		s.push(1);
		s.push(1);
		s.push(20);
		s.push(11);
		s.push(12);
		s.push(5);
		s.push(5);
		System.out.println(s.top());
		s.pop();
		System.out.println(s.getMin());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.getMin());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.getMin());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.getMin());
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		s.pop();
	}

}
