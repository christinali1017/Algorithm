package leetcode;

import java.util.Arrays;
public class PlusOne {
	 public int[] plusOne(int[] digits) {
	        if (digits == null || digits.length == 0) {
	            return digits;
	        }
	        int carry = 0;
	        for (int i = digits.length - 1; i >= 0; i--) {
	            int digit = digits[i];
	            if (i == digits.length -1) {
	                digit += 1;
	            }
	            digit += carry;
	            digits[i] = digit % 10;
	            carry = digit / 10;
	        }
	        if (carry == 0) {
	            return digits;
	        } else {
	            int[] res = new int[digits.length + 1];
	            res[0] = carry;
	            return res;
	        }
	    }
    public static void main(String[] args) {
    	PlusOne p = new PlusOne();
    	int[] digits = {9, 9, 9};
    	System.out.println(Arrays.toString(p.plusOne(digits)));
	}

}
