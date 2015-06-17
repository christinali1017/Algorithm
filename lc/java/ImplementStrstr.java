package leetcode;

/**
 * Implement strStr().

	Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

	Update (2014-11-02):
	The signature of the function had been updated to return the index instead of the pointer. If you still see your function signature returns a char * or String, please click the reload button  to reset your code definition.
 * @author wish
 *
 */

public class ImplementStrstr {
	/*kmp*/
	public int strStr(String haystack, String needle) {
		if(haystack == null) return -1;
    	if(needle == null || needle.length() == 0) return 0;
     	if(needle.length() > haystack.length()) return -1;
     	
     	int[] helper = helper(needle);
     	int hlen = haystack.length();
     	int nlen = needle.length();
     	int i = 0;
     	int j = 0;
     	
     	while(i < hlen && j < nlen){
     		
     		if(haystack.charAt(i) == needle.charAt(j)){
     			i++;
     			j++;
     			if(j == nlen) return i-nlen;
     		}else if(haystack.charAt(i) != needle.charAt(j)){
     			if(j != 0) j = helper[j-1];
     			else i++;
     		}
     		
     	}
     	
     	return -1;
     	
	}
	
	public int[] helper(String pattern){
		int[] helper= new int[pattern.length()];
		helper[0] = 0;
		int len = pattern.length();
		
		int i = 1;
		int j = 0;
		while(i < len){
			if(pattern.charAt(i) == pattern.charAt(j))
				helper[i++] = ++j;
			else{
				if(j != 0) j = helper[j-1];
				else helper[i++] = 0;
			}
		}
		return helper;
	}
    public int strStr1(String haystack, String needle) {
    	if(haystack == null) return -1;
    	if(needle == null || needle.length() == 0) return 0;
     	if(needle.length() > haystack.length()) return -1;
     	
    	for(int i = 0, len = haystack.length() - needle.length(); i <= len; i++){
    		boolean contain = true;
    		for(int j = 0, nlen = needle.length(); j < nlen; j++){
    			if(haystack.charAt(i+j) != needle.charAt(j)) {
    				contain = false;
    				break;
    			}
    		}
    		if(contain) return i;
    	}
    	return -1;
        
    }
    
    public static void main(String[] args) {
    	ImplementStrstr i = new ImplementStrstr();
    	System.out.println(i.strStr("mississipissippii", "issippi"));
	}

}
