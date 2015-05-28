package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */
public class MissingRangs {
	 public List<String> findMissingRanges(int[] vals, int start, int end) {
		 List<String> res = new ArrayList<String>();
		 int pre = start -1;
		 for(int i = 0; i <= vals.length; i++){
			 int current = (i == vals.length) ? end+1 : vals[i];
			 if(current - pre >= 2){
				  String range = (pre + 1 == current -1) ? ("" + (pre+1)) : ((pre+1) + "->" + (current-1));
				 res.add(range);
			 }
			 pre = current;
		 }
		 return res;
	 }
}
