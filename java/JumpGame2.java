package leetcode;
public class JumpGame2 {
    public int jump(int[] A) {
        if(A == null || A.length <= 1) return 0;
        int max = 0;
        int canReach = 0;
        int res = 0;
        for(int i = 0; i < A.length; i++){
            if(i > max) return -1;
            if(i > canReach){
                res++;
                canReach = max;
            }
            max = Math.max(max, i + A[i]);
        }
        return res;
    }
    
    public static void main(String[] args) {
    	JumpGame2 j = new JumpGame2();
    	int[] A = {2, 3, 1, 1, 4};
    	System.out.println(j.jump(A));
	}
}
