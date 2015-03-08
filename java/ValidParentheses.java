package leetcode;

import java.util.Stack;
public class ValidParentheses {
    public boolean isValid(String s) {
    	if(s == null || s.length() == 0) return true;
    	Stack<Character> stack = new Stack<Character>();
    	for(int i = 0, len = s.length(); i < len; i++){
    		char c = s.charAt(i);
    		if(stack.isEmpty() && (c == ')' || c == '}' || c == ']')) return false;
    		else if(c == ')' || c == '}' || c == ']') {
    			if((stack.peek() == '(' && c == ')') || stack.peek() == '[' && c == ']' || stack.peek() == '{' && c == '}') stack.pop();
    			else return false;
    		}
    		else stack.push(c);
    	}
    	return stack.isEmpty();
    }
    
    public static void main(String[] args) {
    	ValidParentheses v = new ValidParentheses();
    	System.out.println(v.isValid("([)]"));
	}

}
