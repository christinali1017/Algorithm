package leetcode;

import java.util.HashMap;
import java.util.Map;
public class TwoSum3 {
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public void add(int number){
		if(map.containsKey(number)) map.put(number, map.get(number)+1);
		else map.put(number, 1);
	}
	
	public boolean find(int value){
		for(Integer i : map.keySet()){
			int remain = value -i;
			if(map.containsKey(remain)){
				if(remain == i && map.get(remain) < 2) continue;
				else return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		TwoSum3 t = new TwoSum3();
		t.add(1);
		t.add(4);
		t.add(6);
		t.add(2);
		System.out.println(t.find(8));
		System.out.println(t.find(5));
		System.out.println(t.find(10));
		System.out.println(t.find(6));
		System.out.println(t.find(9));
	}
}
