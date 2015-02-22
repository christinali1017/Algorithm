package leetcode;

public class Sqrt {
	public int sqrt(int x){
		if(x < 1) return 0;
		double result = 1;
		while(Math.abs(x/result-result) > 0.000000001){
			result = (x/result + result)/2;
		}
		return (int)result;
	}
	
    public int sqrt1(int x){
        if(x < 1) return 0;
        if(x == 1) return 1;
        int l = 1; 
        int r = x/2 + 1;
    	while(l <= r ){
    	    int mid = (l+r)/2;
    	    if(mid <= x/mid && (mid + 1) > x/(mid+1)) return mid;
    	    else if(mid > x/mid) r = mid - 1;
    	    else l = mid + 1;
    	}
    	return 0;
    }
    
	public static void main(String[] args) {
		Sqrt s = new Sqrt();
		System.out.println(s.sqrt(2147483647));
		System.out.println(s.sqrt1(2147483647));
		System.out.println(Math.sqrt(2147483647));
	}


}
