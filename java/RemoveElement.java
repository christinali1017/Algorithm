package leetcode;

import java.util.Arrays;
public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        if(A == null || A.length == 0) return 0;
        int count = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == elem) continue;
            A[count++] = A[i];
        }
        return count;
    }
		public static void main(String[] args) {
			RemoveElement r = new RemoveElement();
			int[] A = { 1, 2, 1, 2, 3, 2};
			int result = r.removeElement(A, 2);
			System.out.println(result);
			System.out.println(Arrays.toString(A));
		}

}
