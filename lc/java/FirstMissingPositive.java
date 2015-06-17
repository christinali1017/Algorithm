package leetcode;
public class FirstMissingPositive {
    /** use the thought of counting sort. 
     * A[0] = 1. A[1] = 2. A[2] = 3
     * */
    public int firstMissingPositive(int[] A) {
        if(A == null || A.length == 0) return 1;
        
        for(int i = 0, len = A.length; i < len; i++){
        	if(A[i] <= len && (A[i] > 0 && A[A[i]-1] != A[i]) ){
        		int temp = A[A[i]-1];
        		A[A[i]-1] = A[i];
        		A[i] = temp;
        		i--;
        	}
        }
        
        for(int i = 0, len = A.length; i < len; i++){
        	if(A[i] != i+1) return i+1;
        }
        
        return A.length + 1;
     }
    
    public static void main(String[] args) {
    	FirstMissingPositive f = new FirstMissingPositive();
    	int[] A = {1, 2, 3};
    	
    	System.out.println(f.firstMissingPositive(A));
	}

}
