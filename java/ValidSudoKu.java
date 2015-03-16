package leetcode;

import java.util.HashSet;
import java.util.Set;
public class ValidSudoKu {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) return false;
        for(int i = 0; i < board.length; i++){
            Set<Character> rset = new HashSet<Character>();
            Set<Character> cset = new HashSet<Character>();
            for(int j = 0; j < board[0].length; j++){
                char rc = board[i][j];
                if((rc != '.' && (rc > '9' || rc < '1')) || rset.contains(rc)) return false;
                if(rc != '.') rset.add(rc);
                char cc = board[j][i];
                if((cc != '.' && (cc > '9' || cc < '1')) || cset.contains(cc)) return false;
                if(cc != '.') cset.add(cc);
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!helper(i, j, board)) return false;
            }
        }
        return true;
    }
    public boolean helper(int i, int j, char[][] board){
        Set<Character> set = new HashSet<Character>();
        for(int k = 3*i; k < 3*i+3; k++){
            for(int l = 3*j; l <3*j+3; l++){
                char c = board[k][l];
                if((c != '.' &&(c > '9' || c < '1')) || set.contains(c)) return false;
                if(c != '.') set.add(c);
            }
        }
        return true;
    }
		 
		 public static void main(String[] args) {
			 ValidSudoKu v = new ValidSudoKu();
			 char[][] board = {{'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
					 {'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
					 {'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'},{'1', '2', '3', '4', '5', '6', '7', '8', '9'}};
			 System.out.println(v.isValidSudoku(board));
		}
}
