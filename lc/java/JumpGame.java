package leetcode;
public class JumpGame {
    public boolean canJump(int[] A) {
        if(A == null || A.length <= 1) return true;
        int max = A[0];
        for(int i = 0; i < A.length && i <= max; i++){
            max = Math.max(max, i+A[i]);
            if(max >= A.length-1) return true;
        }
        return max >= A.length-1;
    }

    
    public static void main(String[] args) {
    	JumpGame j = new JumpGame();
    	int[] A= {1,2,2,6,3,6,1,8,9,4,7,6,5,6,8,2,6,1,3,6,6,6,3,2,4,9,4,5,9,8,2,2,1,6,1,6,2,2,6,1,8,6,8,3,2,8,5,8,0,1,4,8,7,9,0,3,9,4,8,0,2,2,5,5,8,6,3,1,0,2,4,9,8,4,4,2,3,2,2,5,5,9,3,2,8,5,8,9,1,6,2,5,9,9,3,9,7,6,0,7,8,7,8,8,3,5,0};

     	System.out.println(j.canJump(A));
	}
}
