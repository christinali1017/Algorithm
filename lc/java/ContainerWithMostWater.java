package leetcode;
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
    	if(height == null || height.length == 0) return 0;
    	int left = 0;
    	int right = height.length-1;
    	int max = 0;
    	while(left < right){
    		max = Math.max(max, Math.min(height[left], height[right]) * (right-left));
    		if(height[right] > height[left]) left++;
    		else right--;
    	}
    	return max;
    }
    
    public static void main(String[] args) {
    	ContainerWithMostWater c = new ContainerWithMostWater();
    	int[] height = {1, 1};
    	System.out.println(c.maxArea(height));
	}

}
