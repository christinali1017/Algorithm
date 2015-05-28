package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
    	List<String> list = new ArrayList<String>();
    	if(s == null || s.length() > 12 || s.length() < 4) return list;
    	
    	helper(s, list, "", 0, 1);
    	return list;
        
    }
    
    public void helper(String s, List<String> list, String current, int index, int segNumber){
    	if(index >= s.length()) return;
    	if(segNumber == 4){
    		String seg4 = s.substring(index);
    		if(isValid(seg4)){
    			list.add(current + seg4); 
    		}
    		return;
    	}
    	
    	for(int i = 1; i <= 3 && (index +i <= s.length()); i++){
    		String currentSeg = s.substring(index, index +i);
    		if(isValid(currentSeg)){
    			helper(s, list, current + currentSeg + ".", index+i, segNumber+1);
    		}
    	}
    	
    }
    
    public boolean isValid(String s){
    	if(s == null || s.length() > 3) return false;
    	int num = Integer.parseInt(s);
    	if(num > 255 || num <0) return false;
    	if(s.length() >1 && s.charAt(0) == '0') return false;
    	return true;
    }
    
    public static void main(String[] args) {
		String s = "1111";
		RestoreIPAddress r = new RestoreIPAddress();
		List<String>  list = r.restoreIpAddresses(s);
		System.out.println(list);
		
	}


}
