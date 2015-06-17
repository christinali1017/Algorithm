package leetcode;

public class StrStr {
	/*method 1*/
	public int strStr(String haystack, String needle){
		if (haystack == null || needle == null || (haystack.length() < needle.length())) { 
		    return -1;
		}
		if (needle.equals("")) {
		    return 0;
		}
		int[] helper = getHelperArray(needle);
		int i = 0;
		int j = 0;
		int textLen = haystack.length();
		int patternLen = needle.length();
		while (i < textLen && j < patternLen){
			if(haystack.charAt(i) == needle.charAt(j)){
				j++;
				i++;
			}
			if(j == patternLen){
				return i - patternLen;
				//j = helper[j-1]; //if we want find all matches. 
			} else if ((i < textLen && j < patternLen) &&haystack.charAt(i) != needle.charAt(j)){
				if(j != 0) {
				    j = helper[j-1];
				} else {
				    i++;
				}
			}
		}
		return -1;
	}
	
	public int[] getHelperArray(String pattern){
		int i = 1; //suffix
		int	j = 0; //prefix
		int len = pattern.length();
		int[] helper = new int[len];
		helper[0] = 0;
		
		while (i < len){
			if (pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				helper[i] = j;
				i++;
			} else {
				if (j != 0) {
				    j = helper[j-1];
				} else{
					helper[i] = 0;
					i++;
				}
			}
		}
		return helper; 
	}

	
	
	/*method 2*/
    public int strStr1(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        if(needle.equals("")) return 0;
        boolean contain = true;
        for(int i = 0; i <= haystack.length() - needle.length();i++){
            for(int j = i; j < i + needle.length(); j++){
                if(haystack.charAt(j) != needle.charAt(j-i)){
                    contain = false;
                    break;
                } 
            }
            if(contain) return i;
            contain = true;
        }
        return -1;
    }

    /*method 3*/
    public int strStr2(String haystack, String needle) {
       if (haystack == null || needle == null) {
           return -1;
       }
       for (int i = 0; i <= haystack.length() - needle.length(); i++) {
           String temp = haystack.substring(i, needle.length() + i);
           if (temp.equals(needle)) {
               return i;
           }
       }
       return -1;
    }
    
    /*method 4*/
    public int strStr3(String haystack, String needle) {
        if (haystack == null || needle == null) {
        	return -1;
        }
        return haystack.indexOf(needle);
    }
    

}
