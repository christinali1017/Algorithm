package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
	    if(triangle == null || triangle.size() == 0) return 0;
	    int[] res = new int[triangle.get(triangle.size()-1).size()];
	    for(int i = 0; i < res.length; i++){
	        res[i] = triangle.get(triangle.size()-1).get(i);
	    }
	    for(int i = triangle.size()-2; i >= 0; i--){
	        List<Integer> current = triangle.get(i);
	        for(int j = 0; j < current.size(); j++){
	            res[j] = Math.min(res[j], res[j+1]) + current.get(j);
	        }
	    }
	    return res[0];
	 }
    
    public static void main(String[] args) {
    	List<List<Integer>> triangle = new ArrayList<List<Integer>>();
    	List<Integer> list1 = new ArrayList<Integer>();
    	list1.add(2);
    	
    	List<Integer> list2 = new ArrayList<Integer>();
    	list2.add(3);
    	list2.add(4);
    	
    	List<Integer> list3 = new ArrayList<Integer>();
    	list3.add(6);
    	list3.add(5);
    	list3.add(7);
    	
    	List<Integer> list4 = new ArrayList<Integer>();
    	list4.add(4);
    	list4.add(1);
    	list4.add(8);
    	list4.add(3);
    	triangle.add(list1);
    	triangle.add(list2);
    	triangle.add(list3);
    	triangle.add(list4);
    	
    	Triangle t = new Triangle();
    	System.out.println(t.minimumTotal(triangle));
    	
	}
}
