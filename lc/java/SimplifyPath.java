package leetcode;

import java.util.Stack;


public class SimplifyPath {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) return "";
        Stack<String> stack = new Stack<String>();
        
        int i = 0;
        while( i < path.length()){
        	StringBuilder str = new StringBuilder();
        	int start = i;
        	while(i < path.length() && path.charAt(i) != '/'){
        		str.append(path.charAt(i));
        		i++;
        	}
        	if(start != i){
        		if(str.toString().equals("..")){
        			if(!stack.isEmpty()) stack.pop();
        		} else if(!str.toString().equals(".")) stack.push(str.toString());
        	}
        	i++;
        }
        
        StringBuilder result = new StringBuilder();
        if(!stack.isEmpty()){
        	String[] strs = stack.toArray(new String[stack.size()]);
        	for(int j = 0; j < strs.length; j++)
        		result.append("/" +strs[j]);
        }
        if(result.length() == 0) return "/";
        return result.toString();
    }
    
    public static void main(String[] args) {
    	SimplifyPath s = new SimplifyPath();
    	String path = "/a/d/./b/../../c/";
    	System.out.println(s.simplifyPath(path));
	}
}
