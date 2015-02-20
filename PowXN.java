package leetcode;
public class PowXN {
	public double pow(double x, int n){
		   if(n == 0) return 1;
		   if(n < 0) return 1/helper(x, -n);
		   else return helper(x, n);
	}
		
	public double helper(double x, int n){
	    if(n == 1) return x;
	    else if(n % 2 == 0){
	        double result = pow(x, n/2);
	        return result * result;
	    } 
	    else {
	        double result = pow(x, n/2);
	        return result * result * x;
	    } 
	}
	
	public double pow1(double x, int n){
	    if (n == 0) return 1;
	    double result = pow(x, n/2);
	    if(n % 2 == 0) return result * result;
	    else if(n % 2 == 1) return result * result * x;
	    else return result * result / x;
	}
	   
	public static void main(String[] args) {
	   PowXN p = new PowXN();
	   System.out.println(p.pow(-1.00000, -2147483648));
	}

}
