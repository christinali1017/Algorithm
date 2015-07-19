package leetcode;

/*
	Given a string s, partition s such that every substring of the partition is a palindrome.
	
	Return the minimum cuts needed for a palindrome partitioning of s.
	
	For example, given s = "aab",
	Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioning_2 {

	//solution 1:
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[][] isP = getParlindromeArray(s);
        int[] cut = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cut[i] = i;
            for (int j = 0; j <= i; j++) {
                if (isP[j][i]) {
                    if (j > 0) {
                        cut[i] = Math.min(cut[i], cut[j - 1] + 1);
                    } else {
                        cut[i] = 0;
                    }
                }
            }
        }
        return cut[s.length() - 1];
    }
    private boolean[][] getParlindromeArray(String s) {
        boolean[][] res = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || res[j + 1][i - 1])) {
                    res[j][i] = true;
                }
            }
        }
        return res;
    }

    //solution 2: combine the two dp together.

     public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[][] isP = new boolean[s.length()][s.length()];
        int[] cut = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cut[i] = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isP[j + 1][i - 1])) {
                    isP[j][i] = true;
                    if (j > 0) {
                        cut[i] = Math.min(cut[i], cut[j - 1] + 1);
                    } else {
                        cut[i] = 0;
                    }
                }
            }
        }
        return cut[s.length() - 1];
    }


	public static void main(String[] args) {
		String s1 = "abb";
		int min = minCut(s1);
		System.out.println(min);
	}

}
