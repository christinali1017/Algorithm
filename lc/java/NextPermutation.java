package leetcode;

import java.util.Arrays;
public class NextPermutation {
    public void nextPermutation(int[] num) {
        if(num == null || num.length == 0) {
            return;
        }
        int i = num.length - 2;
        while (i >= 0 && num[i] >= num[i+1]) {
            i--;
        }
        int index1 = i;
        if (i == -1) {
            reverse(num, 0);
            return;
        }
        i = num.length - 1;
        while (i >= 0 && num[i] <= num[index1]) {
            i--;
        }
        swap(num, index1, i);
        reverse(num, index1 + 1);
    }
    
    public void reverse(int[] num, int i) {
        for (int j = i; j < (num.length - i) / 2 + i; j++) {
            swap(num, j, num.length + i - j - 1);
        }
    }
    public void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    
    public static void main(String[] args) {
    	NextPermutation n = new NextPermutation();
    	int[] num = {5, 5, 1};
    	n.nextPermutation(num);
    	System.out.println(Arrays.toString(num));
	}

}
