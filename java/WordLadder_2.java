package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
public class WordLadder_2 {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(dict == null || dict.size() == 0 || start == null || end == null) return res;
        /*if start equals end, we can return [[start, end]]*/
        if(start.equals(end)){
            List<String> temp = new ArrayList<String>();
            temp.add(start);
            temp.add(end);
            return res;
        }
        
        /*if dict contains end, remove end from dict, otherwise we might have duplicates */
        dict.remove(end); 
        
        Queue<String> queue = new LinkedList<String> ();
        
        /*store the parent node of each node*/
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        queue.offer(start);
        for(String s : dict){
            map.put(s, new ArrayList<String>());
        }
        map.put(end, new ArrayList<String>());
        List<String> cur = new ArrayList<String>();
        
        while(!queue.isEmpty()){
            cur.clear();
            for(int i = 0, size = queue.size(); i < size; i++){
            	String temp = queue.poll();
                cur.add(temp);
                dict.remove(temp);
            }
            for(String s : cur){
                for(int i = 0; i < s.length(); i++){
                    char[] arr = s.toCharArray();
                    for(arr[i] = 'a'; arr[i] <= 'z'; arr[i]++){
                        String temp = new String(arr);
                        if(temp.equals(s)) continue;
                        if(temp.equals(end)){
                            map.get(end).add(s);
                        }
                        if(dict.contains(temp)){
                            if(!map.containsKey(temp)){
                                map.put(temp, new ArrayList<String>());
                            }
                            map.get(temp).add(s);
                            if(!queue.contains(temp)) queue.offer(temp);
                        }
                    }
                }
            }
            
            if(map.get(end).size()>0) break;
        }
        
        List<String> path = new ArrayList<String>();
        path.add(end);
        buildPaths(map, res, end, start, path);
        return res;
 	}
 	
 	public void buildPaths(Map<String, ArrayList<String>> map, List<List<String>> res, String end, String start, List<String> path){
 	    if(end.equals(start)){
 	        List<String> apath = new ArrayList<String>(path);
 	        Collections.reverse(apath);
 	        res.add(apath);
 	        return;
 	    }
 	    List<String> pre = map.get(end);
 	    for(String s : pre){
 	        path.add(s);
 	        buildPaths(map, res, s, start, path);
 	        path.remove(path.size()-1);
 	    }
 	}
	 
	 public static void main(String[] args) {
	    	String start = "red";
	    	String end = "tax";
	    	Set<String> dict = new HashSet<String>();
	    	String[] arr = {"ted","tex","red","tax","tad","den","rex","pee"};
	    	for(String s : arr)
	    		dict.add(s);
	    	WordLadder_2 wl = new WordLadder_2();
	    	List<List<String>> list = wl.findLadders(start, end, dict);
	    	for(List<String> l : list)
	    		System.out.println(l);
		}

}
