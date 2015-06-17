package leetcode;

import java.util.Stack;

/* 
 * Memory limits (The min stack should not have same number of elements with the stack. Just keep track of the smallest elements)
 * 
 * Duplicate elements. (less or equal than, push into min stack).
 * 
 */

public class MinStack {
	 Stack<Integer> stack; 
	   Stack<Integer> minstack;
	   public MinStack(){
	       stack = new Stack<Integer>();
	       minstack = new Stack<Integer>();
	   }
	    public void push(int x){
	        stack.push(x);
	        if(minstack.isEmpty() || minstack.peek() >= x)
	            minstack.push(x);
	    }
	    
	    public void pop(){
	        if(stack.isEmpty()) return;
	        int val = stack.pop();
	        if(val == minstack.peek()) minstack.pop();
	    }
	    
	    public int top(){
	        if(stack.isEmpty()) return -1;
	        return stack.peek();
	    }
	    
	    public int getMin(){
	    	if(stack.isEmpty()) return -1;
	    	return minstack.peek();
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
