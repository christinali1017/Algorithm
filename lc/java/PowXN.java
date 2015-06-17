package leetcode;
public class PowXN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double result = myPow(x, n/2);
        if (n % 2 == 0) {
            return result * result;
        } else if (n % 2 == 1) {
             return result * result * x;
        } else {
            return result * result / x;
        }
    }
	   
	public static void main(String[] args) {
	   PowXN p = new PowXN();
	   System.out.println(p.pow(-1.00000, -2147483648));
	}

}
