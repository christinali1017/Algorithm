package leetcode;
import java.util.ArrayList;
import java.util.List;
public class NQueens {
    public List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<String[]>();
        if(n <= 0) return res;
        solve(0,n,new int[n],res);
        return res;
    }
    
    public void solve(int r, int n, int[] cols, List<String[]> list){
        if(r == n){
            String[] strs = new String[n];
            for(int i = 0; i < n; i++){
                StringBuilder s = new StringBuilder();
                for(int j = 0; j < n; j++){
                    if(j == cols[i]) s.append("Q");
                    else s.append(".");
                }
                strs[i] = s.toString();
            }
            list.add(strs);
            return;
        }
        for(int i = 0; i < n; i++){
            if(isValid(r, i, cols)){
                cols[r] = i;
                solve(r+1, n, cols, list);
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
	   NQueens n = new NQueens();
	   List<String[]> list = n.solveNQueens(8);
	   System.out.println(list.size());
	   for(String[] s : list){
		   for(String str : s)
			   System.out.println(str);
		   System.out.println("-----------------");
	   }
	   
		   
   }
}
