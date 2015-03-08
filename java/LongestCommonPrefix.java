package leetcode;
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        String s = strs[0];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            for(int j= 1; j < strs.length; j++){
                if((i < strs[j].length() && c != strs[j].charAt(i)) || i >= strs[j].length()) return res.toString();
            }
            res.append(c);
        }
        return res.toString();
    }
    
    public static void main(String[] args) {
    	LongestCommonPrefix l = new LongestCommonPrefix();
    	String[] strs = {"abcdef", "abcdefgh", "a"};
    	System.out.println(l.longestCommonPrefix(strs));
	}
}
