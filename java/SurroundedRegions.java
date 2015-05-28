package leetcode;

import java.util.LinkedList;
/*
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	A region is captured by flipping all 'O's into 'X's in that surrounded region.
	For example,
	X X X X
	X O O X
	X X O X
	X O X X
	After running your function, the board should be:
	
	X X X X
	X X X X
	X X X X
	X O X X
 * 
 * 
 */

/*
 * solution: first find all O which don't need to change, change it to other character, eg *
 * change the remaining O to X
 * change * back to O
 */

public class SurroundedRegions {
    public static void solve(char[][] board) {
    	if(board == null || board.length <= 2 || board[0].length <= 2 ) return;
    	/* fill 0 that not need to change */
    	for(int i = 0; i < board[0].length; i++){
			floodfill(board, 0, i);
			floodfill(board, board.length - 1, i);
    	}
    	for(int i = 0; i < board.length; i++){
			floodfill(board, i, 0);
			floodfill(board,i,board[0].length - 1);
    	}
    	
    	/* change the remaining 0 to X*/
    	/* change * back to 0 */
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			if(board[i][j] == 'O') board[i][j] = 'X';
    			else if(board[i][j] == '*') board[i][j] = 'O';
    		}
    	}
    		
    }
    
    public static void floodfill(char[][] board, int i, int j){
    	if(board[i][j] != 'O') return;
    	int colN = board[0].length;
    	int rowN = board.length;
    	int index = i * colN + j;
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	queue.push(index);
    	while(!queue.isEmpty()){
    		int pos = queue.pop();
    		int row = pos/colN;
    		int col = pos%colN;
    		board[row][col] = '*';
    		if(row > 0 && board[row-1][col] == 'O')
    			queue.push(pos - colN);
    		if(row < rowN-1 && board[row+1][col] == 'O')
    			queue.push(pos + colN);
    		if(col > 0 && board[row][col -1] == 'O')
    			queue.push(pos - 1);
    		if(col < colN -1 && board[row][col + 1] == 'O')
    			queue.push(pos + 1);
    	}
    	
    }
    
    public static void main(String[] args) {
		//char[][] board1 = {{'X', 'O', 'X','O', 'X', 'O'}, {'O', 'X','O', 'X', 'O','X'}, {'X', 'O', 'X','O', 'X', 'O'}, {'O', 'X','O', 'X', 'O','X'}};
		char[][] board = {{'O', 'O', 'O'},{'O', 'O', 'O'},{'O', 'O', 'O'}};
		System.out.println("-----------before---------");
		for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
		
		solve(board);
		
		System.out.println("-----------after---------");
		for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
		
	}
}


