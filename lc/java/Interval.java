package leetcode;

public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
    
    public String toString(){
    	return "[" +start + "," + end+"]";
    }
}
