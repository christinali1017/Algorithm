package leetcode;
/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

	For example,
	"A man, a plan, a canal: Panama" is a palindrome.
	"race a car" is not a palindrome.
	
	Note:
	Have you consider that the string might be empty? This is a good question to ask during an interview.
	
	For the purpose of this problem, we define empty string as valid palindrome.
 * @author wish
 *
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        s = s.trim();
        int i = 0,
        	len = s.length(),
        	j = len-1;
        
        while(i < j){
        	char ci = s.charAt(i);
        	while((i < len -1) && !isNum(ci) && !isAlpha(ci)){
        		i++;
        		ci = s.charAt(i);
        	}
        	
        	char cj = s.charAt(j);
        	while((j > 0) && !isNum(cj) && !isAlpha(cj)){
        		j--;
        		cj = s.charAt(j);
        	}
        	
        	if(i >= j) break;
        	
        	if(!isEqual(ci, cj)) return false;
        	i++;
        	j--;
        }
        return true;
    }
    
    public boolean isAlpha(char c){
    	if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
    		return true;
    	return false;
    }
    
    public boolean isNum(char c){
    	if(c >= '0' && c <= '9')
    		return true;
    	return false;
    }
    
    public boolean isEqual(char x, char y){
    	if(isNum(x) && isNum(y))
    		return x == y;
    	else if(Character.toLowerCase(x) == Character.toLowerCase(y))
    		return true;
    	else return false;
    }
    
    public static void main(String[] args) {
		String s = ".,aa,.,.,.";
		ValidPalindrome vp = new ValidPalindrome();
		System.out.println(vp.isPalindrome(s));
	}
	
}
