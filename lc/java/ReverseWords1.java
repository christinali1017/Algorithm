package leetcode;

public class ReverseWords1 {
    public String reverseWords(String s) {
        if(s.equals(""))return "";
    	String[] sset = s.split("\\s+");
        StringBuffer sb = new StringBuffer();
        for(int i=sset.length-1; i>=0; i--){
            sb.append(sset[i]+" ");
        }
        return sb.toString().trim();
    }
    public static void main(String[] args) {
    	ReverseWords1 rw = new ReverseWords1();
    	System.out.println("\""+rw.reverseWords("   the sky    is   blue   ")+"\"");
		
	}

}
