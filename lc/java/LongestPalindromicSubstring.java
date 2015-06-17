package leetcode;
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() <= 1) return s;
        int max = 0;
        String res = "";
        for(int i = 0; i < s.length(); i++){
            String mid = helper(s, i-1, i+1);
            String left =  helper(s, i-1, i);
            String right =  helper(s, i, i+1);
            String temp = mid.length() > left.length() ? mid : left;
            temp = temp.equals(mid) ? (mid.length() > right.length() ? mid : right) : (left.length() > right.length() ? left : right);
            if(temp.length() > max){
                max = temp.length();
                res = temp;
            }
        }
        return res;
  	}
  	
  	public String helper(String s, int l, int r){
  	    while(l >= 0 && r < s.length()){
  	        if(s.charAt(l) == s.charAt(r)) {
  	            l--;
  	            r++;
  	        }else{
  	            return s.substring(l+1, r);
  	        }
  	    }
  	    return s.substring(l+1, r);
  	}
     
     /** dp */
     public String longestPalindrome1(String s) {
         if(s == null || s.length() == 0) return s;
         boolean[][] dp = new boolean[s.length()+1][s.length() + 1];
         dp[0][0] = true;
         int max = 0;
         String res = "";
         for(int i = 0; i < s.length(); i++){
             for(int j = 0; j <= i; j++){
                 if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])){
                     dp[j][i] = true;
                     if(i - j + 1 > max){
                         max = i-j+1;
                         res = s.substring(j, i+1);
                     }
                 }
                 
             }
         }
         return res;
   	}
     
     public static void main(String[] args) {
		String s = "aaabaaaa";
		LongestPalindromicSubstring l = new LongestPalindromicSubstring();
		System.out.println(l.longestPalindrome(s));
		System.out.println(l.longestPalindrome1(s));
	}
     
}
