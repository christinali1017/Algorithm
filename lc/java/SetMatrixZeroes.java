package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        Set<Integer> rSet = new HashSet<Integer>();
        Set<Integer> cSet = new HashSet<Integer>();
        for(int i = 0; i < matrix.length; i++){
        	for(int j = 0; j < matrix[0].length; j++){
        		if(matrix[i][j] == 0){
        			rSet.add(i);
        			cSet.add(j);
        		}
        	}
        }
        
        for(Integer i : rSet){
        	for(int j = 0; j < matrix[0].length; j++){
        		matrix[i][j] = 0;
        	}
        }
        
        for(Integer i : cSet){
        	for(int j = 0; j < matrix.length; j++){
        		matrix[j][i] = 0;
        	}
        }
    }
    
    public void setZeroes1(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int[] rzeros = new int[matrix.length];
        int[] czeros = new int[matrix[0].length];
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0) {
                    rzeros[i] = 1;
                    czeros[j] = 1;
                }
            }
        }
        for(int i = 0; i < rzeros.length; i++){
            if(rzeros[i] == 1){
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            } 
        }
        for(int i = 0; i < czeros.length; i++){
            if(czeros[i] == 1){
                for(int j = 0; j < matrix.length; j++){
                    matrix[j][i] = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	SetMatrixZeroes s = new SetMatrixZeroes();
    	int[][] matrix = {{1, 0}};
    	s.setZeroes(matrix);
    	System.out.println(Arrays.deepToString(matrix));
	}
	
}
