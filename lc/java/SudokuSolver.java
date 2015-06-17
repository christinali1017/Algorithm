package leetcode;

import java.util.Arrays;


public class SudokuSolver {
    public void solveSudoku(char[][] board) {
    	if(board == null || board.length != 9 || board[0].length != 9) return;
    	solveHelper(board, 0, 0);
    }
    
    public boolean solveHelper(char[][] board, int i, int j){
    	if(j == 9) return solveHelper(board, i+1, 0);
    	if(i == 9) return true;
    	if(board[i][j] == '.'){
    		for(int k = 1; k <= 9; k++){
    			board[i][j] = (char)(k +'0');
    			if(isValidSudoku(board, i, j)){
    				if(solveHelper(board, i, j+1)) return true;
    			}
    			board[i][j] = '.';
    		}
    	}else return solveHelper(board, i, j+1);
    	return false;
    }
	
	 public boolean isValidSudoku(char[][] board, int i, int j) {
		 for(int k = 0; k < 9; k++){
			 if(k != j && board[i][k] == board[i][j]) return false;
			 if(k != i && board[i][j] == board[k][j]) return false;
		 }
		 
		 for(int r = i/3 *3; r < i/3 *3 +3; r++){
			 for(int c = j/3 * 3; c < j/3 * 3+3; c++){
				 if((r != i || c != j) && board[r][c] == board[i][j]) return false;
			 }
		 }
		 return true;
	 }
	 
	 public static void main(String[] args) {
		 SudokuSolver s = new SudokuSolver();
		 char[][] board = {{'5', '.', '.', '.', '.', '.', '.', '.', '.'},{'6', '.', '.', '.', '.', '.', '.', '.', '.'},{'1', '.', '.', '.', '.', '.', '.', '.', '.'},
				 {'8', '.', '.', '.', '.', '.', '.', '.', '.'},{'4', '.', '.', '.', '.', '.', '.', '.', '.'},{'7', '.', '.', '.', '.', '.', '.', '.', '.'},
				 {'9', '.', '.', '.', '.', '.', '.', '.', '.'},{'2', '.', '.', '.', '.', '.', '.', '.', '.'},{'3', '.', '.', '.', '.', '.', '.', '.', '.'}};
		 s.solveSudoku(board);
		 System.out.println(Arrays.deepToString(board));
	}

}
