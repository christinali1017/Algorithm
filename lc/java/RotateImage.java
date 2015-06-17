package leetcode;

import java.util.Arrays;
public class RotateImage {
	public void rotate(int[][] matrix) {
		 if(matrix == null || matrix.length <= 1) return;
		 for(int i = 0, len = matrix.length; i < len/2; i++){
			 for(int j = i; j < len - i -1; j++){
				 int temp = matrix[i][j];
				 matrix[i][j] = matrix[len-j-1][i];
				 matrix[len-j-1][i] = matrix[len-i-1][len-j-1];
				 matrix[len-i-1][len-j-1] = matrix[j][len-i-1];
				 matrix[j][len-i-1] = temp;
			 }
		 }
		
	}
    
    public static void main(String[] args) {
    	RotateImage r = new RotateImage();
    	int[][] matrix = {{1, 2, 3, 4, 44},{5, 6, 7, 8, 88},{9, 10, 11, 12, 122},{13, 14, 15, 16, 166} ,{17, 18, 19, 20, 21}};
    	r.rotate(matrix);
    	System.out.println(Arrays.deepToString(matrix));
	}

}
