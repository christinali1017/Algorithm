package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0) return intervals;
        List<Interval> res = new ArrayList<Interval>();
        Comparator<Interval> comp = new Comparator<Interval>(){
          public int compare(Interval arg1, Interval arg2){
              if(arg1.start == arg2.start) return arg1.end - arg2.end;
              else return arg1.start - arg2.start;
          }
        };
        Collections.sort(intervals, comp);
        res.add(intervals.get(0));
        for(int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.start > res.get(res.size() -1).end) 
                res.add(cur);
            else {
                if(cur.end > res.get(res.size() -1).end)
                    res.get(res.size() -1).end = cur.end;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	MergeIntervals m = new MergeIntervals();
    	List<Interval> intervals = new ArrayList<Interval>();
    	intervals.add(new Interval(1, 3));
    	intervals.add(new Interval(3, 6));
    	System.out.println(m.merge(intervals));
	}
}
