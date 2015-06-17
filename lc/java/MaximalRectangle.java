package leetcode;

import java.util.Stack;

public class MaximalRectangle {
	/* brute force, for each point, caculate largest value */
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int max = 0;
        for(int i = 0;i < matrix.length; i++){
        	for(int j = 0; j < matrix[0].length; j++){
        		if(matrix[i][j] == '1')
        			max = Math.max(max, helper(matrix, i, j));
        		
        	}
        }
        return max;
    }
    
    public int helper(char[][] matrix, int r, int c){
    	int max = 0;
    	int width = Integer.MAX_VALUE;
    	for(int i = r; i < matrix.length && matrix[i][c] == '1'; i++){
    		int temp = 0; 
    		while(c + temp < matrix[r].length && matrix[i][c+temp] == '1') temp++;
    		if(temp < width) width = temp;
    		max = Math.max(max, width * (i - r + 1));
    	}
    	return max;
    }
    
    /* use the method in largest rectangle in histogram */
    public int maximalRectangle1(char[][] matrix) {
    	  if(matrix == null || matrix.length == 0) return 0;
          int max = 0;
          int[] height = new int[matrix[0].length];
          for(int i = 0;i < matrix.length; i++){
          	for(int j = 0; j < matrix[0].length; j++){
          		height[j] = matrix[i][j] == '0' ? 0: height[j]+1;
          	}
          	max = Math.max(max, largestRectangleArea(height));
          }
          return max;
    	
    }
    

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
    
    
    public static void main(String[] args) {
    	MaximalRectangle m = new MaximalRectangle();
    	char[][] matrix = {{'0','0','1', '0'},{'0','1','1', '1'},{'1','1','1', '1'},{'0','1','0', '1'}};
    	System.out.println(m.maximalRectangle(matrix));
    	System.out.println(m.maximalRectangle1(matrix));
    }
}
