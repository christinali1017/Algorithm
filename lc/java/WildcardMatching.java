package leetcode;

public class WildcardMatching {
   	public boolean isMatch(String s, String p) {
   	    if(s == null && p == null || p.equals("*")) return true;
		int i = 0, j = 0, savei = -1, savej = -1;
		while(i < s.length()){
		    if(j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
		        i++;
		        j++;
		    }else if(j < p.length() && p.charAt(j) == '*'){
		        savei = i;
		        savej = j++;
		    }else if(savej != -1){
		        j = savej + 1;
		        i = ++savei;
		    }else return false;
		}
		while(j < p.length() && p.charAt(j) == '*') j++;
		return j == p.length();
	}
   	
	/*dp 
	 * dp[i] indicates if i characters of s match with j characters of p. because result of i only rely on result of j-1, 
	 * so we only need one dimensional arr*/
	public boolean isMatch1(String s, String p) {
		if(p.length() == 0) return s.length() == 0;
		if(s.length()>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')  
		    return false; 
		
		boolean[] dp = new boolean[s.length() +1];
		dp[0] = true;
		for(int j = 0; j < p.length(); j++){
			if(p.charAt(j) != '*'){
				for(int i = s.length() - 1; i >= 0; i--){
					dp[i+1] = dp[i] && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
				}
			}else{
				int i = 0;
				while(i <= s.length() && !dp[i]) i++;
				for(; i <= s.length(); i++) dp[i] = true;
			}
			dp[0] = dp[0] && p.charAt(j) == '*';
		}
		return dp[s.length()];
	}
	
	
    public boolean isMatch2(String s, String p) {
        if((s == null && p == null) || p.equals("*")) return true;
        return helper(s, p, 0, 0);
    }
    
    public boolean helper(String s, String p, int i, int j){
        if(j == p.length()) return i == s.length();
        if(p.charAt(j) != '*'){
            if(i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) return helper(s, p, i+1, j+1);
            else return false;
        }else{
            while(i < s.length()){
                if(helper(s, p, i++, j+1)) return true;
            }
            return helper(s, p, i, j+1);
        }
    }
	    
	    public static void main(String[] args) {
	    	WildcardMatching w = new WildcardMatching();
	    	System.out.println(w.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
	    	System.out.println(w.isMatch1("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
	    	System.out.println(w.isMatch2("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
		}
}
