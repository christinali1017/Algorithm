package leetcode;

public class HouseRobber {
    public int rob(int[] num) {
        if(num == null || num.length == 0) return 0;
        int robYes = 0;
        int robNo = 0;
        for(int money : num){
            int temp = robNo;
            robNo = Math.max(robYes, robNo);
            robYes = temp + money;
        }
        return Math.max(robYes, robNo);
    }
    
    public int rob1(int[] num) {
        if(num == null || num.length == 0) return 0;
        int even = 0;
        int odd = 0;
        for(int i = 0; i < num.length; i++){
            if(i % 2 == 0){
                even += num[i];
                even = Math.max(even, odd);
            }else{
                odd += num[i];
                odd = Math.max(even, odd);
            }
        }
        return Math.max(odd, even);
    }
}
