package leetcode;

public class RotateArray {
	
   public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return;
        k = k % nums.length;
       reverse(nums, 0, nums.length-1);
       reverse(nums, 0, k-1);
       reverse(nums, k, nums.length-1);
    }
    
    public void reverse(int[] nums, int left, int right){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    public void rotate1(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return;
        int[] result = new int[nums.length];
        k = k % nums.length;
        for(int i = 0; i < k; i++){
            result[i] = nums[nums.length -k + i];
        }
        for(int i = k; i < nums.length; i++){
            result[i] = nums[i-k];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = result[i];
        }
    }
    public static void main(String[] args) {
    	RotateArray r = new RotateArray();
    	int[] nums = {1};
    	r.rotate(nums, 1);
	}
}
