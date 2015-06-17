package leetcode;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        if(n <= 0) return false;
        Set<Integer> set = new HashSet<Integer>();
        set.add(n);
        while(n != 1){
            int temp = 0;
            while(n != 0){
                temp += (n%10) * (n%10);
                n = n/10;
            }
            if(set.contains(temp)) return false;
            set.add(temp);
            n = temp;
        }
        return true;
    }
}
