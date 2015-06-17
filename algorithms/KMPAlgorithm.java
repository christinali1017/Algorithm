

import java.util.Arrays;

public class KMPAlgorithm {
	public static void main(String[] args) {
		KMPAlgorithm kmp = new KMPAlgorithm();
		String text = "ABDABCCCABDABDABCB";
		String pattern = "ABDABC";
		kmp.matchString(text, pattern);
	}

	public int[] getHelperArray(String pattern){
		int i = 1, //suffix
			j = 0, //prefix
			len = pattern.length();
		int[] helper = new int[len];
		helper[0] = 0;
		
		while(i < len){
			if(pattern.charAt(i) == pattern.charAt(j)){
				j++;
				helper[i] = j;
				i++;
			}else{
				if(j != 0) j = helper[j-1];
				else{
					helper[i] = 0;
					i++;
				}
			}
		}
		
		System.out.println(Arrays.toString(helper));
		
		return helper; 
	}

	public void matchString(String text, String pattern){
		if(text == null || pattern == null || (text.length() < pattern.length())) return;
        if(pattern.equals("")) return;
		int[] helper = getHelperArray(pattern);
		int i = 0,
			j = 0,
			textLen = text.length(),
			patternLen = pattern.length();
		while(i < textLen && j < patternLen){
			if(text.charAt(i) == pattern.charAt(j)){
				j++;
				i++;
			}
			
			if(j == patternLen){
				System.out.println("Found pattern at index: " + (i-patternLen));
				j = helper[j-1];
			}else if((i < textLen && j < patternLen) && text.charAt(i) != pattern.charAt(j)){
				if(j != 0) j = helper[j-1];
				else i++;
			}
		}
	}
}
