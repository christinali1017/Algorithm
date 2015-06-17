package leetcode;

public class FactorialTrailingZeros {
	/* if n = 0 return 0 */
    public int trailingZeroes(int n) {
    	int result = 0;
        while(n > 0){
        	n /= 5;
        	result += n;
        }
        return result;
    }
    
    
    public int calFactorial(int n){
    	int result = 1;
    	for(int i = n; i >= 1; i--)
    		result *= i;
    	return result;
    }
    public static void main(String[] args) {
    	FactorialTrailingZeros f = new FactorialTrailingZeros();
    	System.out.println(f.trailingZeroes(20));
    	System.out.println(f.calFactorial(20));
	}
}
