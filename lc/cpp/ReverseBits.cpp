class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        if(n == 0) return 0;
        int res = 0;
        for(int i = 0; i < 32; i++){
            int mod = n % 2;
            n = n >> 1;
            res = (res << 1) + mod;
        }
        return res;
    }
    
};