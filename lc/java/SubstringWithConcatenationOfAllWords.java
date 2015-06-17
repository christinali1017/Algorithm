package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String S, String[] L) {
    	List<Integer> list = new ArrayList<Integer>();
    
    	if(S == null || S.length() == 0 || L == null || L.length == 0) return list;
    	if(S.length() < L.length * L[0].length()) return list;
    	
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(int i = 0, len = L.length; i < len; i++){
    		String current = L[i];
    		if(map.containsKey(current)) map.put(current, map.get(current)+1);
    		else map.put(current, 1);
    	}
    	
    	int unitLen = L[0].length();
    	int sLen = S.length();
    	int totalStr = L.length;
    	int i = 0;
    	
    	while(sLen - i >= unitLen * totalStr){
    		Map<String, Integer> tempMap = new HashMap<String, Integer>(map);
    		for(int j = 0; j < totalStr; j++){
    			String current = S.substring(i+j*unitLen, i + (j+1)*unitLen);
    			if(!tempMap.containsKey(current)) break;
    			if(tempMap.get(current) == 1) tempMap.remove(current);
    			else tempMap.put(current,tempMap.get(current)-1);
    		}
			if(tempMap.size() == 0) list.add(i);
    		i++;
    	}
    	
    	return list;   
    }

	public static void main(String[] args) {
		SubstringWithConcatenationOfAllWords s = new SubstringWithConcatenationOfAllWords();
		String[] L = {"foo", "bar", "the"};
		System.out.println(s.findSubstring("barfoothefoobarman", L));
	}

}
