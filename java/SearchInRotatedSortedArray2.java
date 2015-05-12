package leetcode;
public class SearchInRotatedSortedArray2 {
	  public boolean search(int[] A, int target) {
	    	if(A == null) return false;
	    	int l = 0;
	    	int r = A.length-1;
	    	while(l <= r){
	    		int mid = (l+r)/2;
	    		if(A[mid] == target) return true;
	    		if(A[mid] > A[l]){
	    			if(target >= A[l] && target < A[mid]) r = mid-1;
	    			else l = mid + 1;
	    		}else if (A[mid] < A[l]){
	    			if(A[mid] < target && A[r] >= target ) l = mid + 1;
	    			else r = mid -1;
	    		}else l++;
	    	}
	        return false;
	    }

	  
	  public boolean search1(int[] A, int target) {
	        if(A == null || A.length == 0) {
	            return false;
	        }
	        int l = 0;
	        int r = A.length-1;
	        while(l <= r){
	            int mid = (l+r)/2;
	            if(A[mid] == target) {
	                return true;
	            } else if(A[mid] > target && A[mid] < A[r]) {
	                r = mid-1;
	            } else if(A[mid] < target && A[mid] > A[l]) {
	                l = mid + 1;
	            } else if (A[l] != target) {
	                l++;
	            } else {
	                return true;
	            }
	        }
	        return false;
	    }
  
    public static void main(String[] args) {
    	SearchInRotatedSortedArray2 s = new SearchInRotatedSortedArray2();
       	int[] A = {3,1,2,3,3,3,3};
    	System.out.println(s.search(A, 2));
	}
}
