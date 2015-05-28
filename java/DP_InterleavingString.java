package leetcode;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

	For example,
	Given:
	s1 = "aabcc",
	s2 = "dbbca",
	
	When s3 = "aadbbcbcac", return true.
	When s3 = "aadbbbaccc", return false.
 * @author wish
 *
 */
public class DP_InterleavingString {
	/*dynamic programming*/
	public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        
        /* if the first i chars of s1 and first j chars of s2 can construct first i + j chars of s3, then arr[i][j] = true; */
        boolean[][] arr = new boolean[s1.length() + 1][s2.length() + 1];
        
        /* initialize arr */
        arr[0][0] = true;
        for(int i = 1, len = s1.length(); i <= len; i++){
        	if(s1.charAt(i-1) == s3.charAt(i-1) && arr[i-1][0]) arr[i][0] = true;
        }
        for(int i = 1, len = s2.length(); i <= len; i++){
        	if(s2.charAt(i-1) == s3.charAt(i-1) && arr[0][i-1]) arr[0][i] = true;
        }
        
        /* dp calculate*/
        for(int i = 1, lens1 = s1.length(); i <= lens1; i++){
        	for(int j = 1, lens2 = s2.length(); j <= lens2; j++){
        		if(s1.charAt(i-1) == s3.charAt(i+j-1) && arr[i-1][j]) arr[i][j] = true;
        		if(s2.charAt(j-1) == s3.charAt(i+j-1) && arr[i][j-1]) arr[i][j] = true;
        	}
        }
        
        return arr[s1.length()][s2.length()];
	}
	
	/* recursion ----Time limit exceeded */
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        
        return isInterleaveHelper(s1, s2, s3, 0, 0, 0);
    }
    
    public boolean isInterleaveHelper(String s1, String s2, String s3, int index1, int index2, int index3){
    	if(index3 == s3.length()) return true;
    	if(index1 == s1.length()) return s2.substring(index2).equals(s3.substring(index3));
    	if(index2 == s2.length()) return s1.substring(index1).equals(s3.substring(index3));
    	
    	/* if current char of s3 equals to both s1's current char and s2's current char, we have two choices. return || of those two results */
    	if(s1.charAt(index1) == s3.charAt(index3) && s2.charAt(index2) == s3.charAt(index3))
    		return  isInterleaveHelper(s1, s2, s3, index1+1, index2, index3+1) || isInterleaveHelper(s1, s2, s3, index1, index2+1, index3+1);
    	else if(s1.charAt(index1) == s3.charAt(index3)){
    		return  isInterleaveHelper(s1, s2, s3, index1+1, index2, index3+1);
    	}else if(s2.charAt(index2) == s3.charAt(index3)){
    		return isInterleaveHelper(s1, s2, s3, index1, index2+1, index3+1);
    	}else return false;
    	
    }
    
    public static void main(String[] args) {
    	String s1 = "aabcc",
    		   s2 = "dbbca",
    		   s3 = "aadbbcbcac",
    		   s4 = "aadbbbaccc";
    	DP_InterleavingString i = new DP_InterleavingString();
    	System.out.println(i.isInterleave(s1, s2, s3));
    	System.out.println(i.isInterleave(s1, s2, s4));
	}
}
