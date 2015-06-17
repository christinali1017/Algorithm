package leetcode;
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int A[], int B[]) {
        if(A == null && B == null) return 0;
        if(A == null) return B.length % 2 == 0 ? (B[B.length/2 -1] + B[B.length/2])/2 : B[B.length/2];
        if(B == null) return A.length % 2 == 0 ? (A[A.length/2 -1] + A[A.length/2])/2 : A[A.length/2];
        return ((A.length + B.length) % 2 == 1) ? helper(A, B, 0, A.length-1, 0, B.length-1, (A.length + B.length)/2 + 1):((helper(A, B, 0, A.length-1, 0, B.length-1, (A.length + B.length)/2 )) + helper(A, B, 0, A.length-1, 0, B.length-1, (A.length + B.length)/2 + 1))/2;
    }
    
    public double helper(int[] A, int[] B, int startA, int endA, int startB, int endB, int k){
        int lenA = endA - startA + 1;
        int lenB = endB - startB + 1;
        if(lenA > lenB) return helper(B, A, startB, endB, startA, endA, k);
        if(lenA == 0) return B[startB + k - 1];
        if(lenB == 0) return A[startA + k - 1];
        if(k == 1) return Math.min(A[startA], B[startB]);
        int posA = Math.min(k/2, lenA);
        int posB = k - posA;
        if(A[startA + posA -1] == B[startB + posB -1]) return A[startA + posA -1];
        if(A[startA + posA -1] < B[startB + posB -1]) return helper(A, B, posA+startA, endA, startB, posB+startB-1, k - posA);
        else return helper(A, B, startA, posA + startA -1, startB + posB, endB, k - posB);
        
    }
    
    public static void main(String[] args) {
		int[] A = {1, 3, 5};
		int[] B = {2, 4, 6};
		MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
		System.out.println(m.findMedianSortedArrays(A, B));
	}
}
