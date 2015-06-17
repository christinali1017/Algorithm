package leetcode;

import java.util.HashMap;
import java.util.Map;
public class RomanToInteger {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'I') {
                res += map.get(c);
                continue;
            }
            if(i > 0 && map.get(s.charAt(i-1)) < map.get(c)) res = res + map.get(c) - 2 * map.get(s.charAt(i-1));
            else res += map.get(c);
        }
        return res;
    }
    
    public int romanToInt1(String s) {
        if(s == null || s.length() == 0) return 0;
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'I') res += 1;
            else if( c == 'V'){
                if(i > 0 && s.charAt(i-1) == 'I') res += 3;
                else res += 5;
            }else if(c == 'X'){
                if(i > 0 && s.charAt(i-1) == 'I') res += 8;
                else res += 10;
            }else if(c == 'L'){
                if(i > 0 && s.charAt(i-1) == 'X') res += 30;
                else res += 50;
            }else if(c == 'C'){
                if(i > 0 && s.charAt(i-1) == 'X') res += 80;
                else res += 100;
            }else if(c == 'D'){
                if(i > 0 && s.charAt(i-1) == 'C') res += 300;
                else res += 500;
            }else if (c == 'M'){
                if(i > 0 && s.charAt(i-1) == 'C') res += 800;
                else res += 1000;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	RomanToInteger r = new RomanToInteger();
    	System.out.println(r.romanToInt("XXI"));
	}
}
