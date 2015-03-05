package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
	/* from bottom to top*/
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
	
	/* from top to bottom */
	 public int minimumTotal1(List<List<Integer>> triangle) {
		   if(triangle == null || triangle.size() == 0) return 0;
		   int[] sum = new int[triangle.get(triangle.size()-1).size()];
		   sum[0] = triangle.get(0).get(0);
		   int min = Integer.MAX_VALUE;
		   for(int i = 1; i < triangle.size(); i++){
		       List<Integer> cur = triangle.get(i);
		       for(int j = cur.size() -1 ; j >= 0; j--){
		          if(j == cur.size()-1) sum[j] = cur.get(j) + sum[j-1];
		          else if(j == 0) sum[j] = cur.get(j) + sum[0];
		          else sum[j] = cur.get(j) + Math.min(sum[j], sum[j-1]);
		       }
		   }
		   for(int i = 0; i < sum.length; i++){
		       if(min > sum[i]) min = sum[i];
		   }
		   return min;
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
