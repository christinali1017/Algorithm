package leetcode;

public class OneEditDistance {
	public boolean isOneEditDistance(String word1, String word2){
		String temp = word1.length() < word2.length() ? word1 : word2;
		word2 = temp.equals(word1) ? word2 : word1;
		word1 = temp.equals(word1) ? word1 : temp;
		
		int len1 = word1.length();
		int len2 = word2.length();
		
		if(len2 > len1+1) return false;
		int i = 0;
		while(i < len1 && word1.charAt(i) == word2.charAt(i)) i++;
		if(i == len1) return (len2-len1) == 1;
		if(len1 == len2) i++;
		
		/* if two len equals, two of them start from i+1, if not equals, word2 start from i+1*/
		while(i < len1 && word1.charAt(i) == word2.charAt(i+len2-len1)) i++;
		return i == len1;
	}
	
    public boolean isOneEditDistance1(String s, String t) {
        if(Math.abs(s.length() - t.length()) > 1) return false;
        if(s.length() == t.length()) return isOneSameLength(s, t);
        if(s.length() > t.length()) return isOneDifLength(t, s);
        else return isOneDifLength(s, t);
    }
    private boolean isOneDifLength(String s, String l) {
         int i = 0;
        while(i < s.length() && s.charAt(i) == l.charAt(i)) {++i;}
        if(i == s.length()) return true;
        return s.substring(i).equals(l.substring(i + 1));
    }
    private boolean isOneSameLength(String s, String t) {
        int dif = 0;
        for(int i = 0; i < s.length(); ++i) {
             if(s.charAt(i) != t.charAt(i)) ++dif;
        }
        return dif == 1;
    }
    
	public static void main(String[] args) {
		OneEditDistance one = new OneEditDistance();
		System.out.println(one.isOneEditDistance("abcd", "abcefj"));
	}
}
