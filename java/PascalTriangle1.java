package leetcode;
import java.util.ArrayList;
import java.util.List;
public class PascalTriangle1 {
	 public List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> list = new ArrayList<List<Integer>>();
	        if(numRows <= 0) return list;
	        List<Integer> firstR = new ArrayList<Integer>();
	        firstR.add(1);
	        list.add(firstR);
	        for(int i = 1; i < numRows; i++){
	            List<Integer> current = new ArrayList<Integer>();
	            List<Integer> pre = list.get(list.size()-1);
	            for(int j = 0; j <= pre.size(); j++){
	                int val = j == 0 || j == pre.size() ? 1 : pre.get(j-1)+pre.get(j);
	                current.add(val);
	            }
	            list.add(current);
	        }
	        return list;
	    }
    
    public static void main(String[] args) {
    	PascalTriangle1 pt = new PascalTriangle1();
    	List<List<Integer>> list = pt.generate(5);
    	
    	for(List<Integer> l : list)
    		System.out.println(l);
	}
    
}
