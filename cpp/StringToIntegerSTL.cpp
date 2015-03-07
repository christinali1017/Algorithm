class Solution {
public:
    int atoi(string str) {
        bool neg = false;
        int r = 0, prev = 0;
        string::iterator ch = str.begin();
        
        for (; *ch == ' '; ch ++); // trim
        
        if (*ch == '-') {
            neg = true;
            ch ++;
        } else if (*ch == '+') {
            ch ++;
        }
        
        for (; ch != str.end(); ch ++) {
            if (*ch >= '0' && *ch <= '9') {
                prev = r;
                r = *ch - '0' + r * 10;
                if (r / 10 != prev) {
                    if (neg) {
                        r = - INT_MIN;
                    } else {
                        r = INT_MAX;
                    }
                }
            } else {
                break;
            }
        }
        
        return neg ? -r : r;
    }
};