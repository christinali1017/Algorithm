package leetcode;

import java.util.Arrays;

public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        for(int i = 0; i < n/2+1; i++){
            for(int j = i; j < n - i; j++){
               matrix[i][j] = num++ ;
            }
            for(int j = i+1; j < n -i; j++){
               matrix[j][n-i-1] = num++;
            }
            for(int j = n-i-2; j >= i; j--){
               matrix[n-i-1][j] = num++;
            }
            for(int j = n - (i+2); j >= i+1; j--){
               matrix[j][i] = num++;
            }
       }
       return matrix;
    }
    public static void main(String[] args) {
    	SpiralMatrix2 s = new SpiralMatrix2();
    	System.out.println(Arrays.deepToString(s.generateMatrix(-1)));
	}
}
