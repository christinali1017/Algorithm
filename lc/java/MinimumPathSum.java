package leetcode;
public class MinimumPathSum {
	/*two dimensional dp*/
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length; 
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + grid[i][j], dp[i][j-1] + grid[i][j]);
            }
        }
        return dp[m-1][n-1];
    }
	
    /*one dimensional dp*/
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length; 
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 && i == 0) {
                    continue;
                } else if (i == 0) {
                    dp[j] = dp[j-1] + grid[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                     dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
                }
            }
        }
        return dp[n-1];
    }
    
	/* recursion, time limit exceeded*/
    public int minPathSum(int[][] grid) {
    	 int m = grid.length;
    	 int n = grid[0].length;
    	 if(n <= 0 || m <= 0) return 0;
		 
    	 int[] min = new int[1];
    	 min[0] = Integer.MAX_VALUE;
    	 helper(grid, m-1, n-1, 0, min);
		 return min[0];
    }
    
    public void helper (int[][] grid, int i, int j, int sum, int[] min){
    	if(i == 0 && j == 0){
    		sum += grid[0][0];
    		min[0] = Math.min(sum, min[0]);
    		return;
    	}else if(i < 0 || j < 0) return;
    	
    	helper(grid, i, j-1, sum+grid[i][j], min);
    	helper(grid, i-1, j, sum+grid[i][j], min);
    }
    
    public static void main(String[] args) {
    	MinimumPathSum m = new MinimumPathSum();
    	int[][] grid = {{1,2, 3, 4, 5},{4, 5, 2, 3, 4},{7, 8, 1, 2, 3}};
    	System.out.println(m.minPathSum(grid));
    	System.out.println(m.minPathSum1(grid));
    	System.out.println(m.minPathSum2(grid));
	}
}
