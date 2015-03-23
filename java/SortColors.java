package leetcode;

import java.util.Arrays;

public class SortColors {
    public void sortColors(int[] A) {
        if(A == null || A.length == 0) return;
        int count0 = 0, count1 = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0){
                A[i] = 2;
                A[count1++] = 1;
                A[count0++] = 0;
            }else if(A[i] == 1){
                A[i] = 2;
                A[count1++] = 1;
            }
        }

    }
    public void sortColors1(int[] A) {
    	if(A == null || A.length == 0) return;
    	int count0 = 0;
    	int count1 = 0;
    	for(int i = 0; i < A.length; i++){
    		if(A[i] == 0) count0++;
    		else if(A[i] == 1) count1++;
    	}
    	for(int i = 0; i < A.length; i++){
    		if(i < count0) A[i] = 0;
    		else if(i >= count0 && i < count0+count1) A[i] = 1;
    		else A[i] = 2;
    	}
    }
    
    public void sortColors2(int[] A) {
        if(A == null || A.length == 0) return;
        @SuppressWarnings("unused")
		int count0 = 0, count1 = 0, count2 = 0;
        
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0) count0++;
            else if(A[i] == 1) count1++;
            else count2++;
        }
        
        for(int i = 0; i < A.length; i++){
            if(i < count0) A[i] = 0;
            else if(i < count0 + count1) A[i] = 1;
            else A[i] = 2;
        }
    }
    
    public static void main(String[] args) {
		int[] A = {1, 0, 1, 1,1, 0 };
		SortColors s = new SortColors();
		s.sortColors(A);
		System.out.println(Arrays.toString(A));
	}
}
