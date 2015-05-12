package leetcode;

public class FindMinimumInRotatedSortedArray2 {
	 public int findMin(int[] nums) {
	        if (nums == null || nums.length == 0) {
	            return Integer.MIN_VALUE;
	        }
	        int l = 0;
	        int r = nums.length - 1;
	        int result = Integer.MAX_VALUE;
	        while (l <= r) {
	            int mid = l + (r - l) / 2;
	            //the left half is in order
	            if (nums[mid] > nums[l]) {
	                result = Math.min(result, nums[l]);
	                l = mid + 1;
	            } else if (nums[mid] < nums[l]) {
	                result = Math.min(result, nums[mid]);
	                r = mid - 1;
	            } else {
	                result = Math.min(result, nums[l]);
	                l++;
	            }
	        }
	        return result;
	    }
}
