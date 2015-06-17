package leetcode;

import java.util.Stack;
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        int max = 0;
        int start = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty() && s.charAt(i) == ')') start = i+1;
            else if(s.charAt(i) == ')'){
                stack.pop();
                max = stack.isEmpty()?Math.max(max, i -start+1) : Math.max(max, i-stack.peek());
            } 
            else if(s.charAt(i) == '(') stack.push(i);
        }
        return max;
    }
    public static void main(String[] args) {
    	LongestValidParentheses l = new LongestValidParentheses();
    	System.out.println(l.longestValidParentheses("()(()((()))"));
	}

}
