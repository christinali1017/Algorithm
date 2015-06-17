package leetcode;
public class TrappingRainWater {
	  public int trap(int[] A) {
		if(A == null || A.length == 0) return 0;
		int[] left = new int[A.length];
		int max = 0;
		for(int i = 0; i < A.length; i++){
		    left[i] = max;
		    max = Math.max(max, A[i]);
		}
		
		int res = 0;
		max = 0;
		for(int i = A.length-1; i>=0; i--){
		    res += Math.min(max, left[i]) - A[i] > 0 ? (Math.min(max, left[i]) - A[i]): 0 ;
		    max = Math.max(max, A[i]);
		}
		return res;
	}
    
    public int trap1(int[] A) {
    	if(A == null || A.length == 0) return 0;
    	
    	int result = 0;
    	int l = 0;
    	int r = A.length -1;
    	while(l < r){
    		int min = Math.min(A[l], A[r]);
    		if(A[l] < A[r]){
    			l++;
    			while(l < r && A[l] < min)
    				result += min - A[l++];
    		}else{
    			r--;
    			while(l < r && A[r] < min){
    				result += min - A[r--];
    			}
    		}
    	}
    	return result;  	
    }
    
    public static void main(String[] args) {
    	TrappingRainWater t = new TrappingRainWater();
    	int[] A = {0,1,0,2,1,0,1,3,2,1,2,1};
    	System.out.println(t.trap(A));
    	System.out.println(t.trap1(A));
    	
	}

}
