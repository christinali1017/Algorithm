package leetcode;

public class ExcelSheetColumnNumber {
	public static void main(String[] args) {
		ExcelSheetColumnNumber e = new ExcelSheetColumnNumber();
		System.out.println(e.titleToNumber("ZZ"));
		System.out.println(e.titleToNumber1("ZZ"));
	}
    public int titleToNumber(String s) {
        if(s == null || s.length() == 0) return 0;
        int result = 0;
        for(int len = s.length(), i =len - 1; i >= 0; i--){
        	if(i == len - 1) result += s.charAt(i) - 'A' + 1;
        	else result += Math.pow(26, (len - i -1))  * (s.charAt(i) - 'A' + 1);
        }
        return result;
    }
    
    /* we can imagine this as 26 进制数 */
    public int titleToNumber1(String s) {
        if(s == null || s.length() == 0) return 0;
    	int result = 0;
    	for(int i = 0; i < s.length(); i++)
    		result = result * 26 + (s.charAt(i) - 'A' +1);
    	return result;
    }
}
