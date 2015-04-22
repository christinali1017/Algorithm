package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs == null || strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s : strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            if(map.containsKey(sorted)){
                map.get(sorted).add(s);
            }else{
                List<String> list = new ArrayList<String>();
                list.add(s);
                map.put(sorted, list);
            }
        }
        for(Map.Entry<String, List<String>> e : map.entrySet()){
            if(e.getValue().size() > 1){
                res.addAll(e.getValue());
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	Anagrams a = new Anagrams();
    	String[] strs={"abc", "cba", "bca", "ab", "ba", "a"};
    	List<String> list = a.anagrams(strs);
    	System.out.println(list);
	}

}
