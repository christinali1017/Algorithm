package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Sum_4Sum {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(num == null || num.length <= 3) return list;
        Arrays.sort(num);
        for(int i = 0; i <= num.length - 4; i++){
            if(i != 0 && num[i] == num[i-1]) continue;
            List<List<Integer>> current = threeSum(num, i+1, target - num[i]);
            for(List<Integer> l : current){
                l.add(0, num[i]);
            }
            list.addAll(current);
        }
        return list;
    }
    
    public List<List<Integer>> threeSum(int[] num, int start, int target){
         List<List<Integer>> list = new ArrayList<List<Integer>>();
         for(int i = start; i <= num.length - 3; i++){
             if(i != start && num[i] == num[i-1]) continue;
             List<List<Integer>> current = twoSum(num, i+1, target - num[i]);
              for(List<Integer> l : current){
                l.add(0, num[i]);
            }
            list.addAll(current);
         }
         return list;
    }
    
    public List<List<Integer>> twoSum(int[] num, int start, int target){
         List<List<Integer>> list = new ArrayList<List<Integer>>();
         int r = num.length -1;
         int l = start;
         while(l < r){
             if(num[l] + num[r] == target){
                 List<Integer> cur = new ArrayList<Integer>();
                 cur.add(num[l]);
                 cur.add(num[r]);
                 list.add(cur);
                 l++;
                 r--;
                 while(l < r && num[l] == num[l-1]) l++;
                 while(l < r && num[r] == num[r+1]) r--;
             }else if(num[l] + num[r] < target) l++;
             else r--;
         }
         return list;
    }
    
    public static void main(String[] args) {
    	Sum_4Sum s = new Sum_4Sum();
    	int[] num = {2, -1, -1, 0, 0, 0, 0, 1, 3, -2 };
    	List<List<Integer>> list = s.fourSum(num, 5);
    	for(List<Integer> l : list)
    		System.out.println(l);
    	
	}
    

}
