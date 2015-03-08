package leetcode;
public class RegularExpressionMatching {
    public boolean isMatch1(String s, String p) {
        if((s == null && p == null) || p.equals(".*")) return true;
        return helper(s, p, 0, 0);
    }
    public boolean helper(String s, String p, int i, int j){
        if(j == p.length()) return i == s.length();
         if(j+1 == p.length() || p.charAt(j+1) != '*'){
            if(i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) return helper(s, p, i+1, j+1);
            else return false;
         }
         while(i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))){
             if(helper(s, p, i++, j+2)) return true;
         }
         return helper(s, p, i, j+2);
    }
    
    /** dp*/
    public boolean isMatch(String s, String p) {
        if((s == null && p == null) || p.equals(".*")) return true;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 2; i <= p.length(); i++){
            if(p.charAt(i-1) == '*') dp[0][i] = dp[0][i-2];
        }
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                if(p.charAt(j-1) != '*'){
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') dp[i][j] = dp[i-1][j-1];
                }else{
                   if(j > 1 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'))
                         dp[i][j] = dp[i][j-2] || dp[i-1][j];
                   else dp[i][j] = dp[i][j-2];
                }
            }
        }
        return dp[s.length()][p.length()];
     }
    
    public static void main(String[] args) {
    	RegularExpressionMatching r = new RegularExpressionMatching();
    	System.out.println(r.isMatch("aab","aac*xa*b"));
    	System.out.println(r.isMatch1("aab","aac*xa*b"));
	}
}
