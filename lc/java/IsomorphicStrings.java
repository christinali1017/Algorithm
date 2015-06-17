package leetcode;

import java.util.HashMap;
import java.util.Map;


public class IsomorphicStrings {
	
    public boolean isIsomorphic1(String s, String t) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i++) {
           char cs = s.charAt(i);
           char ct = t.charAt(i);
           if(!map.containsKey(cs)) {
               if(map.containsValue(ct)) {
                   return false;
               }
               map.put(cs, ct);
           } else {
               if(map.get(cs) != ct) {
                   return false;
               }
           }
        }
        return true;
    }
    
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> maps = new HashMap<Character, Character>();
        Map<Character, Character> mapt =  new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i++) {
           char cs = s.charAt(i);
           char ct = t.charAt(i);
           maps.putIfAbsent(cs, ct);
           mapt.putIfAbsent(ct, cs);
           if(maps.get(cs) != ct || mapt.get(ct) != cs) {
               return false;
           }
        }
        return true;
    }
    
    public boolean isIsomorphic2(String s, String t) {
        int[] sArr = new int[256];
        int[] tArr = new int[256];
        for(int i = 0; i < s.length(); i++) {
            if(sArr[s.charAt(i)] != tArr[t.charAt(i)]) {
                return false;
            }
            sArr[s.charAt(i)] = i+1;
            tArr[t.charAt(i)] = i+1;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	IsomorphicStrings i = new IsomorphicStrings();
    	System.out.println(i.isIsomorphic2("aba^^", "cgc**"));
	}
}
