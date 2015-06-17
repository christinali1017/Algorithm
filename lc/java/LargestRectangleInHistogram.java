package leetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {
	
	/* use stack, stack store the index of element whose value less than current element's value*/
	public int largestRectangleArea(int[] height) {
		if(height == null || height.length == 0) return 0;
		int largest = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < height.length; i++){
			while(!stack.isEmpty() && height[stack.peek()] > height[i]){
				int index = stack.pop();
				largest = Math.max(largest, height[index]*(stack.isEmpty() ? i : i - stack.peek()-1));
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()){
			int index = stack.pop();
			largest = Math.max(largest, height[index]*(stack.isEmpty() ? height.length : height.length - stack.peek()-1));
		}
		return largest;
	}
	/* go two ways for each bar */
	/* Time limit exceeded */
	public int largestRectangleArea2(int[] height) {
		if(height == null || height.length == 0) return 0;
		int largest = 0;
        for(int i = 0; i < height.length; i++){
        	int l = i;
        	int r = i;
        	while(l-1 >= 0 && height[l-1] >= height[i]) l--;
        	while(r+1 <= height.length-1 && height[r+1] >= height[i]) r++;
        	largest = Math.max(height[i] * (r-l+1), largest);
        }
        return largest;
	}
	
	/* brute force n^ 2*/
	/* Time Limit Exceeded */
    public int largestRectangleArea1(int[] height) {
        if(height == null || height.length == 0) return 0;
        int largest = 0;
        int minHeight = Integer.MAX_VALUE;
        for(int i = 0; i < height.length; i++){
        	for(int j = i; j < height.length; j++){
        		minHeight = Math.min(minHeight, height[j]);
        		largest = Math.max(minHeight * (j-i+1), largest);
        	}
        	minHeight = Integer.MAX_VALUE;
        }
        return largest;
    }
    
    public static void main(String[] args) {
    	LargestRectangleInHistogram l = new LargestRectangleInHistogram();
    	int[] height = {2, 1, 5, 6, 3, 3};
    	System.out.println(l.largestRectangleArea(height));
    	System.out.println(l.largestRectangleArea1(height));
    	System.out.println(l.largestRectangleArea2(height));
	}
}
