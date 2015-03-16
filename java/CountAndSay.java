package leetcode;
public class CountAndSay {
	  public String countAndSay(int n) {
	        if(n <= 0) return "";
	        if(n == 1) return "1";
	        StringBuilder res = new StringBuilder();
	        res.append(1);
	        for(int i = 2; i <= n; i++){
	            String temp = res.toString();
	            StringBuilder cur = new StringBuilder();
	            char pre = temp.charAt(0);
	            int count = 1;
	            for(int j = 1; j < temp.length(); j++){
	                if(pre != temp.charAt(j)){
	                    cur.append(count);
	                    cur.append(pre);
	                    pre = temp.charAt(j);
	                    count = 1;
	                }else count++;
	            }
	            cur.append(count);
	            cur.append(pre);
	            res = cur;
	        }
	        return res.toString();
	    }
    
    public static void main(String[] args) {
    	CountAndSay c = new CountAndSay();
    	System.out.println(c.countAndSay(7));
	}

}
