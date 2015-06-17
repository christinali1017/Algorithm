package leetcode;
public class ZigZagConversion {
    public String convert(String s, int nRows) {
        if(s == null || nRows == 1) return s;
        StringBuilder res = new StringBuilder();
        int size = nRows * 2 - 2;
        for(int i = 0; i < nRows; i++){
            for(int j = i; j < s.length(); j += size){
                res.append(s.charAt(j));
                if(i != 0 && i != nRows-1 && ((size - i + j - i) < s.length()))
                    res.append(s.charAt(size - i + j - i));
            }
        }
        return res.toString();
    }
    
    public static void main(String[] args) {
    	ZigZagConversion z = new ZigZagConversion();
    	String s = "PAYPALISHIRING";
    	System.out.println(z.convert(s, 3));
	}
}
