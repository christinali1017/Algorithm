package leetcode;
public class UniquePath {
	/* dp, one dimensional array*/
	 public int uniquePaths(int m, int n) {
	        if (m < 0 || n < 0 || (m == 0 && n == 0)) {
	            return 0;
	        }
	        
	        int[] dp = new int[n];
	        dp[0] = 1;
	        for (int i = 0; i < m; i++) {
	            for (int j = 1; j < n; j++) {
	                dp[j] = dp[j] + dp[j-1];
	            }
	        }
	        
	        return dp[n-1];
	    }
	 
	 /*dp two dimensional array*/
	    public int uniquePaths2(int m, int n) {
	        if (m < 0 || n < 0 || (m == 0 && n == 0)) {
	            return 0;
	        }
	        
	        int[][] dp = new int[m][n];
	        for (int i = 0; i < m; i++) {
	            dp[i][0] = 1;
	        }
	        for (int i = 0; i < n; i++) {
	            dp[0][i] = 1;
	        }
	        
	        for (int i = 1; i < m; i++) {
	            for (int j = 1; j < n; j++) {
	                dp[i][j] = dp[i-1][j] + dp[i][j-1];
	            }
	        }
	        
	        return dp[m-1][n-1];
	    }
	 
	 /* combination
	  * m+n-2 step, m-1 steps and n-1 steps
	  * */
	 public int uniquePaths3(int m, int n) {
		 int small = m < n ? m-1 : n-1;
		 int large = small == m-1 ? n-1 : m-1;
		 /* if use int, when m, n > 10, will overflow and get wrong answer*/
		 double up = 1;
		 double down = 1;
		 for(int i = 1; i <= small; i++){
			 down *= i;
			 up *= small + large + 1 -i;
		 }
		 return (int) (up/down);
		 
	 }
	
	/* time limit exceeded */
    public int uniquePaths1(int m, int n) {
    	if(n <= 0 || m <= 0) return 0;
    	return helper(m-1, n-1);
    }
    
    public int helper(int m, int n){
    	if(m == 0 && n == 0) return 1;
    	else if( m < 0 || n < 0) return 0;
    	return helper(m-1, n)+helper(m, n-1);
    }
    public static void main(String[] args) {
    	UniquePath u = new UniquePath();
    	System.out.println(u.uniquePaths(10, 10));
    	System.out.println(u.uniquePaths1(10, 10));
    	System.out.println(u.uniquePaths2(10, 10));
	}
}
