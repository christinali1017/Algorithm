package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<Integer>();
        if(n < 0) return list;
        list.add(0);
        if(n == 0) return list;
        list.add(1);
        for(int i = 2; i <= n; i++){
            for(int j = list.size()-1; j >= 0; j--){
                list.add(list.get(j) + (1 <<(i-1)));
            }
        }
        return list;
     }
    
    public static void main(String[] args) {
    	GrayCode g = new GrayCode();
    	List<Integer> list = g.grayCode(3);
    	System.out.println(list);
	}
    
}
