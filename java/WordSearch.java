package leetcode;
/**
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 * @author wish
 *
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
    	if(word == null || word.length() == 0) return true;
    	if(board == null || board.length == 0) return false;
    	boolean[][] used = new boolean[board.length][board[0].length];
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			if(helper(i, j, board, word, 0, used)) return true;
    		}
    	}
    	return false;
    }
    
    public boolean helper(int i, int j, char[][] board, String word, int start, boolean[][] used){
    	if(start == word.length()) return true;
    	if(i < 0 || j < 0 || j > board[0].length-1 || i > board.length-1 || used[i][j] || board[i][j] != word.charAt(start)) return false;
    	used[i][j] = true;
    	
    	boolean result = helper(i, j-1, board, word, start+1, used) || helper(i, j+1, board, word, start+1, used) 
    			|| helper(i-1, j, board, word, start+1, used) || helper(i+1, j, board, word, start+1,used);
    	used[i][j] = false;
    	return result;
    }
    
    public static void main(String[] args) {
    	WordSearch w = new WordSearch();
    	char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
    	String word = "ASFCSEED";
    	System.out.println(w.exist(board, word));
	}

}
