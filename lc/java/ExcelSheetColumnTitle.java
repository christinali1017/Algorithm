package leetcode;

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        if(n <= 0) return "";
        StringBuilder sBuilder = new StringBuilder();
        while(n > 0){
        	sBuilder.insert(0,  (char)(((n-1) % 26) + 'A'));
        	n = (n-1)/ 26;
        }
        	
        return sBuilder.toString();
    }
    
    public static void main(String[] args) {
    	ExcelSheetColumnTitle e = new ExcelSheetColumnTitle();
    	System.out.println(e.convertToTitle(702));
	}
}
