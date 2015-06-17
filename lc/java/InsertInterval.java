package leetcode;

import java.util.ArrayList;
import java.util.List;
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        int i = 0;
        for(; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.end >= newInterval.start){
                break;
            }
            res.add(cur);
        }
        
        res.add(newInterval);
        
        for(; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            Interval last = res.get(res.size()-1);
            if(cur.start > last.end){
                res.add(cur);
            }else{
                last.start = Math.min(last.start, cur.start);
                last.end = Math.max(last.end, cur.end);
            }
        }
        return res;
    }
    
    
    /*method 2*/
    public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        for(Interval interval : intervals){
            if(interval.end < newInterval.start){
                res.add(interval);
            }else if(interval.start > newInterval.end){
                res.add(newInterval);
                newInterval = interval;
            }else if(newInterval.end >= interval.start || newInterval.start <= interval.end ){
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        res.add(newInterval);
        return res;
    }
    
    public static void main(String[] args) {
    	InsertInterval m = new InsertInterval();
    	List<Interval> intervals = new ArrayList<Interval>();
    	intervals.add(new Interval(0, 1));
    	intervals.add(new Interval(3, 5));
    	Interval newInterval = new Interval(2, 2);
    	System.out.println(m.insert(intervals, newInterval));
	}
    
}
