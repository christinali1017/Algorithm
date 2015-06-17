package leetcode;
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        if(num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder res = new StringBuilder();
        int cur = 0;
        for(int i= num1.length() + num2.length() -1;i >=0; i--){
            for(int j = Math.min(i-1, num1.length()-1);j >= 0; j--){
                if(j < num1.length() && ((i-j-1) < num2.length())) {
                    cur += (int)(num1.charAt(j) - '0') * (int)(num2.charAt(i-j-1) - '0');
                }
            }
            if(i != 0 || cur > 0) res.insert(0, cur % 10);
            cur = cur / 10;
        }
        return res.toString();
    }

	public static void main(String[] args) {
		MultiplyStrings m = new MultiplyStrings();
		System.out.println(m.multiply("9133", "0"));
	}

}
