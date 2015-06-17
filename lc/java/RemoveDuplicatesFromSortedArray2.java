package leetcode;
import java.util.Arrays;
public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] A) {
        if(A == null) return 0;
        if(A.length <= 2) return A.length;
        int count = 2;
        int pre = A[1];
        int prepre = A[0];
        for(int i = 2; i < A.length; i++){
            if(A[i] == pre && A[i] == prepre) continue;
            A[count++] = A[i];
            prepre = pre;
            pre = A[i];
        }
        return count;
    }
	
	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray2 r = new RemoveDuplicatesFromSortedArray2();
		int[] A = { 1, 2, 2, 3};
		int result = r.removeDuplicates(A);
		System.out.println(result);
		System.out.println(Arrays.toString(A));
	}
}
