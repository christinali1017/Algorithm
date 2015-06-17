package leetcode;

import java.util.ArrayList;
import java.util.List;
public class PermutationSequence {
      public String getPermutation(int n, int k) {
            if(n <= 0) return "";
            
            /* create factorial,nums for permutation and ressult stringbuilder*/
            List<Integer> nums = new ArrayList<Integer>();
            int factorial = 1;
            for(int i = 1; i <= n; i++){
                nums.add(i);
                factorial *= i;
            }
            factorial /= n;
            StringBuilder res = new StringBuilder();
            
            /* to simplify the boundary, we can k--. Consider n = 4, k = 18, 18 / 6 = 3, then we need to add nums[i-1]*/ 
            k--;
            
            /*create permutation one digit per time*/
            while(n > 0){
                int index = k / factorial;
                res.append(nums.get(index));
                nums.remove(index);
                k %= factorial;
                if(n > 1) {
                    factorial = factorial / (n-1);
                }
                n--;
            }
            return res.toString();
        }
	

    public static void main(String[] args) {
    	PermutationSequence p = new PermutationSequence();
    	System.out.println(p.getPermutation(4, 4));
	}
    
}
