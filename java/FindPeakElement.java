package leetcode;

public class FindPeakElement {
	//O(lgn)
	public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length -1 || nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (mid > 0 && nums[mid] < nums[mid - 1]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
	
	//O(n)
    public int findPeakElement1(int[] num) {
        if(num == null || num.length == 0) return -1;
        if(num.length == 1) return 0;
        for(int i = 0; i < num.length-1; i++){
        	if(i == 0){
        		if(num[i] > num[i+1]) return i;
        	}else{
        		if(num[i] > num[i+1] && num[i] > num[i-1]) return i;
        	}
        }
        if(num[num.length-1] > num[num.length-2]) return num.length -1;
        return -1;
    }
    
    public static void main(String[] args) {
    	FindPeakElement f = new FindPeakElement();
    	int[] num = {1};
    	System.out.println(f.findPeakElement(num));
	}
}
