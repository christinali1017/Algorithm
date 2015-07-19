package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) {
            return res;
        }
        partition(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    private void partition(String s, int start, List<String> cur, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(cur));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (isPalindrome(sb.toString())) {
                cur.add(sb.toString());
                partition(s, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        for (int i = 0, halfLen = s.length() / 2; i < halfLen; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //dp solution

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

}
