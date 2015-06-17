package leetcode;
public class SearchInsertPosition {
    public int searchInsert(int[] A, int target) {
    	if(A == null || A.length == 0) return 0;
    	
    	int l = 0;
    	int r = A.length -1;
    	while(l <= r){
    		int mid = (l +r)/2;
    		if(A[mid] == target) return mid;
    		else if(A[mid] < target) l = mid+1;
    		else r = mid - 1;
    	}
    	
    	return l;
        
    }
    
    public static void main(String[] args) {
    	SearchInsertPosition s = new SearchInsertPosition();
    	int[] A = {0, 1, 3, 5, 6};
    	System.out.println(s.searchInsert(A, 6));
	}
}
