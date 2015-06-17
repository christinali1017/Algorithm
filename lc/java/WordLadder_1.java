package leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
public class WordLadder_1 {
	
	 public static int ladderLength(String start, String end, Set<String> dict) {
	        if(dict == null || dict.size() == 0 || start == null || end == null) return 0;
	        Deque<String> queue = new LinkedList<String>();
	        Deque<Integer> qlen = new LinkedList<Integer>();
	        queue.offer(start);
	        qlen.offer(1);
	        while(!queue.isEmpty()){
	            start = queue.pollFirst();
	            int len = qlen.pollFirst();
	            if(start.equals(end)) return len;
	            for(int i = 0; i < start.length(); i++){
	                char[] arr = start.toCharArray();
	                for(arr[i] = 'a'; arr[i] <='z'; arr[i]++){
	                    String s = new String(arr);
	                    if(dict.contains(s)){
	                        queue.addLast(s);
	                        qlen.addLast(len+1);
	                        dict.remove(s);
	                    }
	                }
	            }
	        }
	        return 0;
	    }
    
    public static void main(String[] args) {
    	String start = "qa";
    	String end = "sq";
    	Set<String> dict = new HashSet<String>();
    	String[] arr = {"si","go","se","cm","so","ph","mt","db","mb",
    			"sb","kr","ln","tm","le","av","sm","ar","ci",
    			"ca","br","ti","ba","to","ra","fa","yo","ow",
    			"sn","ya","cr","po","fe","ho","ma","re","or",
    			"rn","au","ur","rh","sr","tc","lt","lo","as",
    			"fr","nb","yb","if","pb","ge","th","pm","rb",
    			"sh","co","ga","li","ha","hz","no","bi","di",
    			"hi","qa","pi","os","uh","wm","an","me","mo",
    			"na","la","st","er","sc","ne","mn","mi","am",
    			"ex","pt","io","be","fm","ta","tb","ni","mr",
    			"pa","he","lr","sq","ye"};
    	for(String s : arr)
    	dict.add(s);
    	System.out.println(ladderLength(start, end, dict));
    	
	}

}
