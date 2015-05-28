package leetcode;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 * @author wish
 *
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word1.length() == 0) return word2.length();
        if(word2 == null || word2.length() == 0) return word1.length();
        
       String longer = word1.length() > word2.length() ? word1 : word2;
       String shorter = longer == word1 ? word2 : word1;
       int[] dp = new int[shorter.length() + 1];
       
       for(int i = 0; i <= shorter.length(); i++) dp[i] = i;
       for(int i = 0; i < longer.length(); i++){
       		int[] temp = new int[shorter.length()+1];
       		temp[0] = i+1;
       		for(int j = 0; j < shorter.length(); j++){
       			if(shorter.charAt(j) == longer.charAt(i))  temp[j+1] = dp[j];
       			else temp[j+1] = Math.min(dp[j], Math.min(dp[j+1], temp[j]))+1;
       		}
       		dp = temp;
       }
       return dp[shorter.length()];
    }
    
    public static void main(String[] args) {
    	EditDistance e = new EditDistance();
    	System.out.println(e.minDistance("abc", "abc"));
	}
}
