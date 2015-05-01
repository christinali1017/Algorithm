package leetcode;
public class UniquePath2 {
	  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	        int m = obstacleGrid.length;
	        int n = obstacleGrid[0].length;
	        if (obstacleGrid == null || m == 0 || n == 0 || obstacleGrid[0][0] == 1) {
	            return 0;
	        }
	        int[] dp = new int[n];
	        dp[0] = 1;
	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                if (obstacleGrid[i][j] == 0 && j > 0) {
	                    dp[j] = dp[j-1] + dp[j];
	                }else if (obstacleGrid[i][j] == 1) {
	                    dp[j] = 0;
	                }
	            }
	        }
	        return dp[n - 1];
	    }
    
    
    /*two dimensional array */
    
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        boolean blocked = false;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] == 1) {
                blocked = true;
            }
            if (!blocked) {
                dp[i][0] = 1;
            }
        }
        blocked = false;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i] == 1) {
                blocked = true;
            }
            if (!blocked) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1, rlen = obstacleGrid.length; i < rlen; i++) {
            for (int j = 1, clen = obstacleGrid[0].length; j < clen; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
    public static void main(String[] args) {
    	UniquePath2 u = new UniquePath2();
		int[][] obstacleGrid = {{0,0}};
		System.out.println(u.uniquePathsWithObstacles(obstacleGrid));
	}

}
