package leetcode;
/**
 * You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * @author wish
 *
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n < 0) return 0;
        if(n == 1 || n == 0) return 1;
        return climbStairs(n-1)+climbStairs(n-2);
    }
    
    public int climbStairs1(int n){
        if(n < 0) return 0;
        if(n == 1 || n == 0) return 1;
        int pre = 1;
        int prepre = 1;
        for(int i = 2; i <= n; i++){
        	int temp = pre;
        	pre = pre + prepre;
        	prepre = temp;
        }
        return pre;
    }
    
    public int climbStairs2(int n){
        if(n < 0) return 0;
        if(n <= 1) return 1;
        int[][] res = {{1, 0}, {0, 1}};
        int[][] m = {{1, 1}, {1, 0}};
        while(n > 0){
            if(n % 2 == 1) res = multiplyMatrix(res, m);
            n = n/2;
            m = multiplyMatrix(m, m);
        }
        return res[0][0];
    }
    
    public int[][] multiplyMatrix(int[][] m, int[][]n){
        int a = m[0][0] * n[0][0] + m[0][1] * n[1][0];
        int b = m[0][0] * n[0][1] + m[0][1] * n[1][1];
        int c = m[1][0] * n[0][0] + m[1][1] * n[1][0];
        int d = m[1][0] * n[0][1] + m[1][1] * n[1][1];
        int[][] res = {{a, b}, {c, d}};
        return res;
    }
    
    public static void main(String[] args) {
    	ClimbingStairs c = new ClimbingStairs();
    	System.out.println(c.climbStairs(10));
    	System.out.println(c.climbStairs1(10));
	}
    
    
}
