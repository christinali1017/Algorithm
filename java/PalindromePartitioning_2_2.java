package leetcode;

/*
	Given a string s, partition s such that every substring of the partition is a palindrome.
	
	Return the minimum cuts needed for a palindrome partitioning of s.
	
	For example, given s = "aab",
	Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioning_2_2 {
	
	public static int minCut(String s){
		if(s == null || s.length() == 0 || s.length() == 1) return 0;
		int len = s.length();
		boolean[][] isPalin = new boolean[len][len];
		int minCut[] = new int[len+1];
		for(int i = 0; i < len; i++) 
			isPalin[i][i] = true;
		
		/* calculate isPalin */
		for(int i = len -1; i >= 0; i--){
			for(int j = i+1; j < len; j++){
				if(s.charAt(i) == s.charAt(j)){
					if(j-i == 1 || isPalin[i +1][j-1]) 
						isPalin[i][j] = true;
				}
			}
		}
		
		/* initial cut, cut at every char*/
		for(int i = len; i >=0; i--)
			minCut[i] = len-i-1;
		
		/* D[i] = min(D[i], 1 + D[j+1])*/
		for(int i = len-1; i >= 0; i--){
			for(int j = i; j < len; j++){
				if(isPalin[i][j])
					minCut[i] = Math.min(minCut[i], 1+minCut[j+1]);
			}
		}
		
		return minCut[0];
	}
	
	public static void main(String[] args) {
		String s1 = "abb";
		int min = minCut(s1);
		System.out.println(min);
	}

}
