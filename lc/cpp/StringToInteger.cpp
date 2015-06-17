class Solution {
public:
    int atoi(const char *str) {
        bool neg = false;
        int r = 0, prev = 0;
        int ii = 0;
        
        for (; str[ii] == ' '; ii ++); // trim
        
        if (str[ii] == '-') { neg = true; ii ++; }
        else if (str[ii] == '+') { ii ++; }
        
        for (; str[ii] != 0; ii ++) {
            if (str[ii] >= '0' && str[ii] <= '9') {
                prev = r;
                r = str[ii] - '0' + r * 10;
                if (r / 10 != prev) {
                    if (neg) {
                        r = - INT_MIN;
                    } else {
                        r = INT_MAX;
                    }
                }
            } else { break; }
        }
        
        return neg ? -r : r;
    }
};