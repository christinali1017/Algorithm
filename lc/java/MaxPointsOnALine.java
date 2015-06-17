package leetcode;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
	
	public static int maxPoints(Point[] points){
		if(points == null) return 0;
		if(points.length <=2) return points.length;
		
		int max = 2;
		for(int i = 0; i < points.length-1; i++){
			Map<Double, Integer> map = new HashMap<Double, Integer>();
			int same = 0;
			int local = 1;
			double slope = 0;
			for(int j = i+1; j < points.length; j++){
				if(points[i].x == points[j].x && points[i].y == points[j].y){
					same++;
					continue;
				}else if(points[i].x == points[j].x) slope = Double.MAX_VALUE;
				else if(points[i].y == points[j].y) slope = 0;
				else slope = (double)(points[j].y - points[i].y)/(double)(points[j].x - points[i].x);
				if(map.containsKey(slope)) map.put(slope, map.get(slope)+1);
				else map.put(slope, 2);
			}
			for(Integer val : map.values()){
				local = Math.max(local, val);
			}
			local += same;
			max = Math.max(local, max);
		}
		return max;
	}
	/* brute force, o(n^3) */
	public static int maxPoints1(Point[] points){
		if(points == null) return 0;
		if(points.length <=2) return points.length;
		
		/* check if all points are the same */
		boolean allSame = true;
		for(int i = 1; i < points.length; i++){
			if(points[0].y != points[i].y || points[0].x != points[i].x) {
				allSame = false;
				break;
			}
		}
		
		if(allSame){
			return points.length;
		}
		
		int max = 2;
		for(int i = 0; i < points.length-1; i++){
			for(int j = i+1; j < points.length; j++){
				if(points[j].y == points[i].y && points[j].x == points[i].x) continue;
				int temp = 2;
				for(int k = 0; k <points.length; k++){
					if(k != i && k != j && (points[i].y - points[j].y) * (points[i].x - points[k].x) == 
							(points[i].y - points[k].y) * (points[i].x - points[j].x)) temp++;
				}
				max = Math.max(max, temp);
			}
		}

		return max;
	}
	
	
	
	public static void main(String[] args) {
		Point[] array = {new Point(40,-23),new Point(9,138),new Point(429,115),new Point(50,-17),
				new Point(-3,80),new Point(-10,33),new Point(5,-21),new Point(-3,80),new Point(-6,-65),
				new Point(-18,26),new Point(-6,-65),new Point(5,72),new Point(0,77),new Point(-9,86),
				new Point(10,-2),new Point(-8,85),new Point(21,130),new Point(18,-6),new Point(-18,26),
				new Point(-1,-15),new Point(10,-2),new Point(8,69),new Point(-4,63),new Point(0,3),
				new Point(-4,40),new Point(-7,84),new Point(-8,7),new Point(30,154),new Point(16,-5),
				new Point(6,90),new Point(18,-6),new Point(5,77),new Point(-4,77),new Point(7,-13),
				new Point(-1,-45),new Point(16,-5),new Point(-9,86),new Point(-16,11),new Point(-7,84),
				new Point(1,76),new Point(3,77),new Point(10,67),new Point(1,-37),new Point(-10,-81),
				new Point(4,-11),new Point(-20,13),new Point(-10,77),new Point(6,-17),new Point(-27,2),
				new Point(-10,-81),new Point(10,-1),new Point(-9,1),new Point(-8,43),new Point(2,2),
				new Point(2,-21),new Point(3,82),new Point(8,-1),new Point(10,-1),new Point(-9,1),
				new Point(-12,42),new Point(16,-5),new Point(-5,-61),new Point(20,-7),new Point(9,-35),
				new Point(10,6),new Point(12,106),new Point(5,-21),new Point(-5,82),new Point(6,71),
				new Point(-15,34),new Point(-10,87),new Point(-14,-12),new Point(12,106),new Point(-5,82),
				new Point(-46,-45),new Point(-4,63),new Point(16,-5),new Point(4,1),new Point(-3,-53),
				new Point(0,-17),new Point(9,98),new Point(-18,26),new Point(-9,86),new Point(2,77),
				new Point(-2,-49),new Point(1,76),new Point(-3,-38),new Point(-8,7),new Point(-17,-37),
				new Point(5,72),new Point(10,-37),new Point(-4,-57),new Point(-3,-53),new Point(3,74),
				new Point(-3,-11),new Point(-8,7),new Point(1,88),new Point(-12,42),new Point(1,-37),
				new Point(2,77),new Point(-6,77),new Point(5,72),new Point(-4,-57),new Point(-18,-33),
				new Point(-12,42),new Point(-9,86),new Point(2,77),new Point(-8,77),new Point(-3,77),
				new Point(9,-42),new Point(16,41),new Point(-29,-37),new Point(0,-41),new Point(-21,18),
				new Point(-27,-34),new Point(0,77),new Point(3,74),new Point(-7,-69),new Point(-21,18),
				new Point(27,146),new Point(-20,13),new Point(21,130),new Point(-6,-65),new Point(14,-4),
				new Point(0,3),new Point(9,-5),new Point(6,-29),new Point(-2,73),new Point(-1,-15),
				new Point(1,76),new Point(-4,77),new Point(6,-29)};  
		
		//Point[] array1 = {new Point(40,-23),new Point(40,-23),new Point(40,-23),new Point(40,-23)};
		System.out.println(MaxPointsOnALine.maxPoints(array));
		System.out.println(MaxPointsOnALine.maxPoints1(array));
	}
	

}
