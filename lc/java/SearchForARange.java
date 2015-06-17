package leetcode;

import java.util.Arrays;
public class SearchForARange {
	
    public int[] searchRange(int[] A, int target) {
        int[] res = {-1, -1};
        if(A == null || A.length == 0) return res;
        int l = 0;
        int r = A.length -1;
        while(l <= r){
            int mid = (l + r)/2;
            if(A[mid] < target) l = mid + 1;
            else if (A[mid] > target) r = mid - 1;
            else{
                int l1 = l;
                int r1 = mid;
                while(l1 <= r1){
                    int mid1 = (l1+r1)/2;
                    if(A[mid1] < target) l1 = mid1 + 1;
                    else r1 = mid1 -1;
                }
                res[0] = l1;
                
                int l2 = mid;
                int r2 = r;
                while(l2 <= r2){
                    int mid2 = (l2+r2)/2;
                    if(A[mid2] <= target) l2 = mid2 + 1;
                    else r2 = mid2 -1;
                }
                res[1] = r2;
                
                return res;
            }
        }
        return res;
    }
    
    
    public int[] searchRange1(int[] A, int target) {
        int[] res = {-1, -1};
        if(A == null || A.length == 0) return res;
        int l1 = 0;
        int r1 = A.length -1;
        while(l1 <= r1){
            int mid1 = (l1+r1)/2;
            if(A[mid1] < target) l1 = mid1 + 1;
            else r1 = mid1 -1;
        }
        int l2 = 0;
        int r2 = A.length -1;
        while(l2 <= r2){
            int mid2 = (l2 + r2)/2;
            if(A[mid2] <= target) l2 = mid2 + 1;
            else r2 = mid2 -1;
        }
        if(l1 <= r2){
            res[0] = l1;
            res[1] = r2;
        }
        
        return res;
    }
   
	
	/* O(n) time*/
    public int[] searchRange2(int[] A, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if(A == null || A.length == 0) return res;
        for(int i = 0; i < A.length; i++){
            if(A[i] == target){
                if(res[0] == -1) res[0] = i;
                else res[1] = i;
            }
        }
        if(res[0] != -1 && res[1] == -1) res[1] = res[0];
        return res;
    }
    
    public static void main(String[] args) {
    	SearchForARange s = new SearchForARange();
    	int[] A = {1, 2, 2, 3};
    	int[] result = s.searchRange(A, 2);
    	System.out.println(Arrays.toString(result));
	}

}
