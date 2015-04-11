package leetcode;

import java.util.ArrayList;
import java.util.List;
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return res;
        int row = matrix.length;
        int col = matrix[0].length;
        int loops = (Math.min(row, col)) /2;
        for(int i = 0; i < loops; i++){
            for(int j = i; j < col - i; j++){
                res.add(matrix[i][j]);
            }
            for(int j = i+1; j < row -i; j++){
                res.add(matrix[j][col-i-1]);
            }
            for(int j = col-i-2; j >= i; j--){
                res.add(matrix[row-i-1][j]);
            }
            for(int j = row - (i+2); j >= i+1; j--){
                res.add(matrix[j][i]);
            }
        }
        if(row >= col && col % 2 == 1){
            for(int i = col/2; i < row - col/2; i++){
                res.add(matrix[i][col/2]);
            }
        }
        if(row < col && row % 2 == 1){
            for(int i = row/2; i < col - row/2; i++){
                res.add(matrix[row/2][i]);
            }
        }
        
         return res;
    }
	
	public static void main(String[] args) {
		SpiralMatrix s = new SpiralMatrix();
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8},{9, 10, 11, 12},{13, 14, 15, 16},{16, 17, 18, 19},{22, 23, 24, 25},{26, 27, 28, 29}, {-1, -2, -3, -4}};
		System.out.println(s.spiralOrder(matrix));
	}
}
