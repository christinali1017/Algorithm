package leetcode;
public class TwoSum2 {
	public int[] twoSum(int[] numbers, int target){
		int[] res = new int[2];
		if(numbers == null || numbers.length <= 1) return res;
		int l = 0;
		int r = numbers.length -1;
		while(l < r){
			if(numbers[l] + numbers[r] == target){
				res[0] = l+1;
				res[1] = r+1;
				return res;
			}else if(numbers[l] + numbers[r] > target) r--;
			else l++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		TwoSum2 t = new TwoSum2();
		int[] numbers = {1, 2, 4, 7, 9, 10};
		int[] result = t.twoSum(numbers, 14);
		System.out.println(result[0]+"  " + result[1]);
	}
}
