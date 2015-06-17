package leetcode;
import java.awt.Point;
import java.util.HashMap;
public class MaxPointsOnALine1 {
	public int maxPoints(Point[] points){
		if(points.length==2){
			return points.length;
		}
		int tmpMaxP = 0;
		int maxP = 0;   
		int sameP = 1; //坐标完全相同
		int noK = 0;  //斜率不存在的点
		HashMap<Double,Integer> hs = new HashMap<Double,Integer>();
		for(int i=0; i<points.length; i++){
			tmpMaxP = 0;
			sameP = 1;
			noK = 0;
			hs.clear();
			for(int j=i+1; j<points.length; j++){
				
				if(points[i].x == points[j].x && points[i].y == points[j].y){
					sameP++;
					continue;
				}
				if(points[i].x == points[j].x){
					noK++;
				}else{
					double k=0.0;				
					 if(points[i].y == points[j].y) {    //注意-0.0和正0.0不等，应该分开讨论
		                    k = 0;  
		                } else {  
		                    k = ((double)(points[i].y - points[j].y))/(points[i].x - points[j].x);  
		                }					
					 if(hs.get(k)==null){
						hs.put(k, new Integer(1));
						 if(1>tmpMaxP) {  
							 tmpMaxP = 1;  
		                    }  
					}else{
						 int number = hs.get(k);  
		                 number++;  
		                 hs.put(k, new Integer(number));
		                 if(number>tmpMaxP) {  
		                	 tmpMaxP = number;  
		                    }  
					}
				}
			}
			if(noK>1) {
				if(noK>tmpMaxP) {  
		            	tmpMaxP = noK;  
		            }  
		        }  
			tmpMaxP += sameP;  
		    if(tmpMaxP>maxP) {  
		    	maxP = tmpMaxP;  
		        }  
		}
		return maxP;
	}
	public static void main(String[] args) {
		MaxPointsOnALine1 max = new MaxPointsOnALine1();
		Point[] array ={new Point(2,3),new Point(3,3),new Point(-5,3)};
		System.out.println((double)(3-3)/(2-3));
		System.out.println((double)(3-3)/(3+5));
		System.out.println(max.maxPoints(array));
	}

}
