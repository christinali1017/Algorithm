package leetcode;
/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
	Given an encoded message containing digits, determine the total number of ways to decode it.
	
	For example,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	
	The number of ways decoding "12" is 2.
 * @author wish
 *
 */
public class DeoceWays {
	/** dp
	 *  if 11 - 26(except 20) dp[i] = dp[i-1] + dp[i-2]
	 *  if 10, 20, dp = dp[i-2]
	 *  if 00 dp[i] = 0
	 *  if 01-09, dp[i] = dp[i-1]
	 *  if 27-99 dp[i] = dp[i-1]
	 *  
	 */
	public int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
		
		/* n1 dp[i-2], n2 dp[i-1], n3 dp[i] */
		int n1 = 1,
			n2 = 1,
			n3 = 1;
		for(int i = 1, len = s.length(); i < len; i++){
			if(i != 0 && s.charAt(i-1) == '0' && s.charAt(i) == '0') return 0; 
			if(s.charAt(i) == '0') {
				if(s.charAt(i-1) == '1' || s.charAt(i-1) == '2') n3 = n1;
				else return 0;
			}else{
				if(s.charAt(i-1) == '0' || s.charAt(i-1) >= '3' || (s.charAt(i-1) == '2' && s.charAt(i) >= '7' &&  s.charAt(i) <= '9' ))
					n3 = n2;
				else n3 = n1 + n2;
			}
			n1 = n2;
			n2 = n3;
			
		}
		return n2;
	}
	
	
	
   /* Time Limit Exceeded, also the conditions have problem*/
   public int numDecodings1(String s) {
    	if(s == null || s.length() == 0) return 1;
    	
    	int[] sum = new int[1];
    	helper(s, 0, sum);
    	return sum[0];
        
    }
    
    public void helper(String s, int index, int[] sum){
    	if(index == s.length()){
    		sum[0]++;
    		return;
    	}
    	for(int i = 1; i < 3 && index + i <= s.length() ; i++){
    		String temp = s.substring(index, index+i);
    		int val = Integer.valueOf(temp);
    		if(val >=1 && val <= 26) helper(s, index + i, sum);
    	}
    }
    

    public static void main(String[] args) {
		String s = "10276755442121234";
		DeoceWays d = new DeoceWays();
		System.out.println(d.numDecodings1(s));
		System.out.println(d.numDecodings(s));
		
	}
}
