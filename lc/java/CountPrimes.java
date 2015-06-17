package leetcode;

public class CountPrimes {
    public int countPrimes(int n) {
        if(n <= 2) {
            return 0;
        }
        boolean[] prime = new boolean[n];
        for(int i = 2, sqr = (int)Math.sqrt(n); i <= sqr; i++ ) {
            // if i is prime
            if(!prime[i]){
                for(int j = i*i; j < n; j += i) {
                    prime[j] = true;
                }
            }
        }
        int res = 1;
        for(int i = 3; i < n; i += 2){
            if(!prime[i]){
                res++;
            }
        }
        return res;
    }
    
    public int countPrimes1(int n) {
        if(n <= 2) {
            return 0;
        }
        boolean[] prime = new boolean[n];
        for(int i = 2; i <= n/2; i++ ) {
            // if i is prime
            if(!prime[i]){
                for(int j = i+i; j < n; j += i) {
                    prime[j] = true;
                }
            }
        }
        int res = 1;
        for(int i = 3; i < n; i++){
            if(!prime[i]){
                res++;
            }
        }
        return res;
    }
    
    

}
