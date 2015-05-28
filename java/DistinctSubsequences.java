package leetcode;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.

	A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	
	Here is an example:
	S = "rabbbit", T = "rabbit"
	
	Return 3.
 */
/**
 * Dp, w[i][j] stands for the number of subsequences of s(0,i) in t(0,j)
 * if s.charAt(i) = t.charAt(j), w[i][j] = w[i-1][j-1] + w[i-1,j]
 * otherwise w[i][j] = w[i-1,j]
 */
public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        if(S == null || T == null) return 0;
        
        int[] num[] = new int[S.length() + 1][T.length() + 1];
        /* initialize num */
        for(int i = 0; i < S.length(); i++){
        	num[i][0] = 1;
        }
        
        for(int i = 1; i <= S.length(); i++){
        	for(int j = 1; j <= T.length(); j++){
        		if(S.charAt(i-1) == T.charAt(j-1)){
        			num[i][j] = num[i-1][j-1] + num[i-1][j];
        		}else{
        			num[i][j] = num[i-1][j];
        		}
        	}
        }
        return num[S.length()][T.length()];
    }
    
    public static void main(String[] args) {
		String S = "rabbbit";
		String T = "rabbit";
		DistinctSubsequences ds = new DistinctSubsequences();
		System.out.println(ds.numDistinct(S, T));
	}
}
