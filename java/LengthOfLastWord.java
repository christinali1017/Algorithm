package leetcode;
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        s = s.trim();
        String[] words = s.split("\\s+");
        return words[words.length -1].length();
    }
    
	public int lengthOfLastWord1(String s) {
		if(s == null || s.length() == 0) return 0;
    	s = s.trim();
    	int len = 0;
    	for(int i = s.length()-1; i>=0; i--){
    		if(s.charAt(i) != ' '){
    			len++;
    		}else break;
    	}
    	return len;
	}
    
    public static void main(String[] args) {
    	LengthOfLastWord l = new LengthOfLastWord();
    	System.out.println(l.lengthOfLastWord("Today is a nice day  "));
	}

}
