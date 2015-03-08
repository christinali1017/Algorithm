package leetcode;
public class ReverseIntegers {
    public int reverse(int x) {
        if(x <= 9 && x >= -9) return x;
        int res = 0;
        boolean positive = x > 0 ? true : false;
        x = Math.abs(x);
        while(x > 0 && (Integer.MAX_VALUE - x % 10) / 10 >= res){
            int mod = x % 10;
            res = res * 10 + mod;
            x = x / 10;
        }
        if(x > 0) return 0;
        return positive ?  res : -res;
    }
    
    
    public static void main(String[] args) {
    	ReverseIntegers r = new ReverseIntegers();
    	System.out.println(r.reverse(1463847412));
		
	}

}
