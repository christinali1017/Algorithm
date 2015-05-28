package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    public String largestNumber(int[] num) {
        if(num == null || num.length == 0) return "0";
       	 StringBuilder sBuilder = new StringBuilder();
       	 List<String> list = new ArrayList<String>(num.length);
       	 
       	 for(int n : num) list.add(String.valueOf(n));
       	 Comparator<String> comparator = new Comparator<String>(){
       	 	public int compare(String a, String b){
       	 		if(a.length() == b.length()) return b.compareTo(a);
       	 		else{
       	 			String ab = a + b;
       	 			String ba = b + a;
       	 			return ba.compareTo(ab);
       	 		}
       	 	}

       	 };
       	 
       	 Collections.sort(list, comparator);
       	 for(String s : list) sBuilder.append(s);
       	 if(sBuilder.toString().length() > 0 && sBuilder.toString().charAt(0) == '0')
       	 	return "0";
       	 
       	 return sBuilder.toString();
    }
    
    public static void main(String[] args) {
    	LargestNumber l = new LargestNumber();
    	int[] num = {0, 0};
    	System.out.println(l.largestNumber(num));
	}
}
