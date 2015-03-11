package leetcode;
public class SearchInRotatedSortedArray {
	
    public int search(int[] A, int target) {
        if(A == null || A.length == 0) return -1;
        int l = 0;
        int r = A.length-1;
        while(l <= r){
            int mid = (l+r)/2;
            if(A[mid] == target) return mid;
            if(A[mid] > target && A[mid] < A[r]) r = mid-1;
            else if(A[mid] < target && A[mid] > A[l]) l = mid + 1;
            else if(A[l] != target) l++;
            else return l;
        }
        return -1;
    }
    
	 public int search1(int[] A, int target) {
		 if(A == null || A.length == 0) return -1;
			int mid = A.length/2;
			int startPos = 0;
			int endPos =A.length -1;
			if(A[mid] == target) return mid;
			if(A[mid] > A[0] ){
				if(A[0] > target) startPos = mid+1;
				else startPos = 0;		
			}else if(A[mid] < A[0]){
				if(A[mid] > target) endPos = mid -1;
			}
			for(int i = startPos; i <= endPos; i++){
				if(A[i] == target) return i;
			}
			return -1;
	 }
	 
	 public int search2(int[] A, int target){
			if(A == null || A.length == 0) return -1;
			int l = 0;
			int r = A.length -1;
			while( l <= r){
				int mid = (l + r)/2;
				if(A[mid] == target) return mid;
				/* >= ! or it might miss when left is equals to mid */
				if(A[mid] >= A[l]){
					if(A[l] <= target && A[mid] > target) r = mid -1;
					else l = mid + 1;
				}else {
					if(A[mid] < target && A[r] >= target) l = mid + 1;
					else r = mid -1;
				}
			}
			return -1;
		
		}
	
	
    
    public static void main(String[] args) {
    	SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
    	int[] A = {3, 1};
    	System.out.println(s.search2(A, 1));
	}
    

}
