package leetcode;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle2 {
	public List<Integer> getRow(int rowIndex) {
	    List<Integer> list = new ArrayList<Integer>();
    	if(rowIndex < 0) return list;
    	list.add(1);
    	for(int i = 0; i < rowIndex; i++){
    	    int pre = 1;
    	    int current = 0;
    	    for(int j = 0; j < list.size(); j++){
    	        if(j == 0) continue;
    	        current = list.get(j);
    	        list.set(j, pre + list.get(j));
    	        pre = current;
    	    } 
    	    list.add(1);
    	}
        return list;
	}
    public static void main(String[] args) {
    	PascalTriangle2 pt = new PascalTriangle2();
    	List<Integer> list = pt.getRow(-1);
    	System.out.println(list);
	}
    
}
