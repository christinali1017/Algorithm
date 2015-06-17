package leetcode;

import java.util.Arrays;
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        if(A == null) return 0;
        if(A.length <= 1) return A.length;
        int count = 1;
        int pre = A[0];
        for(int i = 1; i < A.length; i++){
            if(A[i] == pre) continue;
            A[count++] = A[i];
            pre = A[i];
        }
        return count;
    }
	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray r = new RemoveDuplicatesFromSortedArray();
		int[] A = { 1, 1, 1, 2, 2};
		int result = r.removeDuplicates(A);
		System.out.println(result);
		System.out.println(Arrays.toString(A));
	}

}
