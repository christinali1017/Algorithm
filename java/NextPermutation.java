package leetcode;

import java.util.Arrays;
public class NextPermutation {
    public void nextPermutation(int[] num) {
    	if(num == null || num.length == 0) return;
    	
    	int i = num.length - 2;
    	while(i >= 0 && num[i] >= num[i+1]) i--;
    	if(i == -1) {
    		reverse(num, 0);
    		return;
    	}
    	
    	int j = i+1;
    	while(j < num.length && num[j] > num[i]) j++;
    	swap(num, i, j-1);
    	
    	reverse(num, i+1);
    }
    
    public void reverse(int[] num, int start){
    	int end = num.length-1;
    	while(start < end){
    		int temp = num[start];
    		num[start] = num[end];
    		num[end] = temp;
    		start++;
    		end--;
    	}
    }
    
    public void swap(int[] num, int i, int j){
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
