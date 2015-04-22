package leetcode;

public class NQueens_2 {
    public int totalNQueens(int n) {
        if(n <= 0) return 0;
        int[] res = new int[1];
        solve(0,n,new int[n],res);
        return res[0];
    }
    
    public void solve(int r, int n, int[] cols, int[] res){
        if(r == n){
          res[0]++;
        }
        for(int i = 0; i < n; i++){
            if(isValid(r, i, cols)){
                cols[r] = i;
                solve(r+1, n, cols, res);
            }
        }  
        
    }
    
	public boolean isValid(int r, int c, int[] cols){
	    for(int i = 0; i < r; i++){
	        if(c == cols[i] || r - i == Math.abs(cols[i] - c)) return false;
	    }
	    return true;
	}
   

   
   public static void main(String[] args) {
	   NQueens_2 n = new NQueens_2();
	   System.out.println(n.totalNQueens(8));
   }
}
