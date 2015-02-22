package leetcode;
public class DivideTwoIntegers {
	/** log(n), each integer can be represent as binary format, so n = (0 or 1) * 2 ^ n +(0 or 1) * 2 ^ (n-1) + .....*/
	public int divide( int dividend, int divisor){
	       if(divisor == 0) return Integer.MAX_VALUE;
	       int result = 0;
	       boolean positive = ((dividend ^ divisor) >>> 31) == 0;
	       if(divisor == Integer.MIN_VALUE){
	           if(dividend == Integer.MIN_VALUE) return 1;
	           else return 0;
	       }
	       if(dividend == Integer.MIN_VALUE){
	           if(divisor == 1) return Integer.MIN_VALUE;
	           else if(divisor == -1) return Integer.MAX_VALUE;
	           dividend += Math.abs(divisor);
	           result += 1;
	       }
	       
	       dividend = Math.abs(dividend);
	       divisor = Math.abs(divisor);
	       
	       int count = 0;
	       while(divisor <= (dividend >> 1)){
	           count++;
	           divisor <<= 1;
	       }
	       
	       while(count >= 0){
	           if(dividend >= divisor){
	               result += 1 << count;
	               dividend -= divisor;
	           }
	           divisor >>= 1;
	           count --;
	       }
	       return positive ? result : -result;
	    }
    
	
	/* Time Limit Exceeded */
    public int divide1(int dividend, int divisor) {
    	if(divisor == 0) return Integer.MAX_VALUE;
    	
    	boolean isDif = (dividend^divisor)>>>31 == 1;
    	
    	if(divisor == Integer.MIN_VALUE){
            if(dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }
    	int dividendAbs = Math.abs(dividend);
    	int divisorAbs = Math.abs(divisor);
    	int result = 0;

    	if(dividendAbs == Integer.MIN_VALUE) dividendAbs = Integer.MAX_VALUE;
    	if(dividendAbs == Integer.MAX_VALUE && divisorAbs ==1){
    		result = Integer.MAX_VALUE;
    		return isDif ? -result : result;
    	}
    	int i = 0;
    	while(dividendAbs >= divisorAbs){
    		dividendAbs -= divisorAbs;
    		result++;
    		if(i == 0 && dividend == Integer.MIN_VALUE)
    			dividendAbs += 1;
    		i++;
    	}
    	
    	return isDif ? -result : result;
        
    }
    
    public static void main(String[] args) {
    	DivideTwoIntegers d = new DivideTwoIntegers();
    	System.out.println(d.divide(-2147483648, -33));
    	System.out.println(d.divide1(-2147483648, -33));
	}

}
