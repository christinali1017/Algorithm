package leetcode;

public class ReverseBits {
    public int reverseBits(int n) {
        if(n == 0) return 0;
        for(int i = 0; i < 16; i++){
        	n = swap(n, i, 32-i-1);
        }
        return n;
    }
    
    public int swap(int n,int i, int j){
    	int bitI = (n >> i) & 1;
    	int bitJ = (n >> j) & 1;
    	if((bitI ^ bitJ) != 0){
    		n = n ^ ((1 << i) | (1 << j));
    	}
    	return n;
    }
    
    public static void main(String[] args) {
    	ReverseBits r = new ReverseBits();
    	System.out.println(r.reverseBits(1));
	}
}
