package leetcode;

public class NumberOf1Bits {

    public int hammingWeight(int n) {
        if(n == 0) return 0;
        int res = 0;
        while(n != 0){
            if((n & 1) != 0) res++;
            n = n >>> 1;
        }
        return res;
    }
}
