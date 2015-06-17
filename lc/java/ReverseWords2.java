package leetcode;
/**
 * 跟Reverse Words in a String很类似，但是这里要求in-place，也就是说不需要开辟额外空间。
	该题在LeetCode中假设开头和结尾没有空格，而且单词之间只有一个空格。
	思路就是两步走，第一步就是将整个字符串翻转。然后从头逐步扫描，将每个遇到单词再翻转过来。
	
	[注意事项]
1）如果是Java，应该跟面试官指出String是immutable，所以需要用char array来做。
2）follow-up问题：k-step reverse。也就是在第二部翻转的时候，把k个单词看作一个长单词，进行翻转。
 * @author wish
 *
 */
public class ReverseWords2 {
	public void reverseWords(char[] s) {
	    // 1, reverse the whole sentence
	    reverse(s, 0, s.length - 1);
	    // 2, reverse each word
	    int start = 0;
	    for (int i = 0; i < s.length; i++) {
	        if (s[i] == ' ') {
	            reverse(s, start, i - 1);
	            start = i + 1;
	        }
	    }
	    // 3, reverse the last word, if there is only one word this will solve the corner case
	    reverse(s, start, s.length - 1);
	}

	public void reverse(char[] s, int start, int end) {
	    while (start < end) {
	        char temp = s[start];
	        s[start] = s[end];
	        s[end] = temp;
	        start++;
	        end--;
	    }
	}
	
	public static void main(String[] args) {
		ReverseWords2 r = new ReverseWords2();
		char[] s = "word".toCharArray();
		r.reverseWords(s);
		System.out.println(new String(s));
	}
}
