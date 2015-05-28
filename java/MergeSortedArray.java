package leetcode;

import java.util.Arrays;

public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        if(A == null || B == null) return;
        int index = m+n-1;
        n--;
        m--;
        while(m >=0 || n >=0){
            if(m >= 0 && n >= 0){
                if(A[m] > B[n]) A[index--] = A[m--];
                else A[index--] = B[n--];
            }else if(m >= 0) A[index--] = A[m--];
             else A[index--] = B[n--];
        }
    }
    
    public static void main(String[] args) {
		int A[] = {0,0};
		int B[] = {1};
		MergeSortedArray m = new MergeSortedArray();
		m.merge(A, 0, B, B.length);
		System.out.println(Arrays.toString(A));
	}
}
