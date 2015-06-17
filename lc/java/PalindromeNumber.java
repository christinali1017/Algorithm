package leetcode;
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
	    if(x <= 9) return true;
	    int divide = 1;
	    while((divide < Integer.MAX_VALUE/10) && (divide * 10) <= x){
	        divide *= 10;
	    }
	    while(x != 0){
	        int ms = x/divide;
	        int ls = x%10;
	        if(ms != ls) return false;
	        x = (x % divide) / 10;
	        divide  = divide / 100;
	    }
	    return true;
	}
	 
    public static void main(String[] args) {
    	PalindromeNumber p = new PalindromeNumber();
    	System.out.println(p.isPalindrome(100001));
	}
}
