package leetcode;

import java.util.ArrayList;
import java.util.List;
public class LetterCombinationOfPhonenumber {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return res;
        for(int i = 0; i < digits.length(); i++){
            String cur = getLetters(digits.charAt(i));
            List<String> temp = new ArrayList<String>();
            if(res.size() == 0){
                for(int j = 0; j < cur.length(); j++){
                    res.add(cur.charAt(j) + "");
                }
            }else{
                for(String s : res){
                    for(int k = 0; k < cur.length(); k++){
                        temp.add(s + cur.charAt(k));
                    }
                }
                res = temp;      
            }
        }
        return res;
        
    }
    
    public String getLetters(char digit){
        switch(digit){
            case '9' : return "wxyz";
            case '8' : return "tuv";
            case '7' : return "pqrs";
            case '6' : return "mno";
            case '5' : return "jkl";
            case '4' : return "ghi";
            case '3' : return "def";
            case '2' : return "abc";
            default: return "";
        }
    }
	    
	    public static void main(String[] args) {
	    	LetterCombinationOfPhonenumber l = new LetterCombinationOfPhonenumber();
	    	System.out.println(l.letterCombinations("22"));
		}

}
