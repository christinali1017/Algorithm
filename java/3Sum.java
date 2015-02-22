package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Sum_3sum {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(num == null || num.length < 3) return list;
        Arrays.sort(num);
        for(int i = 0; i <= num.length - 3; i++){
            if(i != 0 && num[i] == num[i-1]) continue;
            List<List<Integer>> current = twoSum(num, i+1, -num[i]);
            if(current.size() > 0){
                for(List<Integer> l : current ){
                    l.add(0, num[i]);
                }
                list.addAll(current);
            }
        }
        return list;
    }
    
    public List<List<Integer>> twoSum(int[] num, int start, int target){
        int r = num.length -1;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int l = start;
        while(l < r){
            if(target == num[l] + num[r]){
                List<Integer> cur = new ArrayList<Integer>();
                cur.add(num[l]);
                cur.add(num[r]);
                list.add(cur);
                l++;
                r--;
                while(l < r && num[l] == num[l-1]) l++;
                while(l < r && num[r] == num[r+1]) r--;
            }else if(target < num[l] + num[r])
                r--;
            else l++;
        }
        return list;
    }
    
    public static void main(String[] args) {
    	Sum_3sum s = new Sum_3sum();
    	int[] num = {2, -1, -1, 0, 0, 0, 0, 1, 3, -2 };
    	List<List<Integer>> list = s.threeSum(num);
    	for(List<Integer> l : list) System.out.println(l);
	}
}
