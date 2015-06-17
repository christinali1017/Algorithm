package leetcode;

/**
 * Given two binary strings, return their sum (also a binary string).

	For example,
	a = "11"
	b = "1"
	Return "100".
 * @author wish
 *
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        if(a == null) return b;
        if(b == null) return a;
        
        int len = a.length() > b.length() ? b.length() : a.length();
        if(len == b.length()){
        	String temp = a;
        	a = b;
        	b = temp;
        }
        
        int j = b.length()-1;
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for(int i = a.length()-1; i >= 0; i--){
        	int sum = carry + (int)a.charAt(i) + (int)b.charAt(j--) -96;
        	carry = sum >= 2 ? 1 : 0;
        	result.insert(0, "" +sum%2);
        }
        for(; j >= 0; j--){
        	int sum = carry + (int)b.charAt(j) - 48;
        	carry = sum >= 2 ? 1 : 0;
        	result.insert(0, "" +sum%2);
        }
        if(carry != 0) result.insert(0, "" +carry);
        
        return result.toString();
    }
    
    public static void main(String[] args) {
    	String a = "111";
    	String b = "1";
    	AddBinary aa = new AddBinary();
    	System.out.println(aa.addBinary(a, b));
	}

}
