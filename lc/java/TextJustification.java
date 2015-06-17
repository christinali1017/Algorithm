package leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
 * @author wish
 *
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int L) {
    	List<String> list = new ArrayList<String>();
    	if(words == null || words.length == 0) return list;
    	
    	int len = 0; // words length of each line
    	int startWord = 0; //start word index
    	for(int i = 0; i < words.length; i++){
    		if(len+words[i].length() + i - startWord > L){
    			
    			int spaces  = 0;
    			int extra = 0;
    			if(i - startWord -1 != 0){
    				spaces = (L - len)/(i-startWord-1);
    				extra = (L - len)%(i-startWord-1);
    			}
	    		StringBuilder str = new StringBuilder();
	    		for(int j = startWord; j < i; j++){
	    			str.append(words[j]);
	    			if(j != i-1)
	    				for(int k = 0; k < spaces; k++) str.append(" ");
	    			if(extra > 0) str.append(" ");
	    			extra--;
	    		}
	    		for(int j = str.length(); j < L; j++) str.append(" ");
	    		list.add(str.toString());
	    		len = 0;
	    		startWord = i;
    		}
    		len += words[i].length();
    	}
    	StringBuilder str = new StringBuilder();
    	for(int i = startWord; i < words.length; i++){
    		str.append(words[i]);
    		if(str.length() < L) str.append(" ");
    	}
    	
    	for(int i = str.length(); i < L; i++)
    		str.append(" ");
    	
    	list.add(str.toString());
    	return list;
    }
    
    public static void main(String[] args) {
    	TextJustification t = new TextJustification();
    	String[] words = {"This", "is", "an", "abz", "example", "of", "text", "justification."};
    	System.out.println(t.fullJustify(words, 16));
    	
	}
    
}
