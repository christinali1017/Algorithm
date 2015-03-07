class Solution {
public:
    int titleToNumber(string s) {
        int val = 0, x = 1;
        for (string::reverse_iterator c = s.rbegin(); c != s.rend(); ++c ) {
            val += x * (*c - 'A' + 1);
            x *= 26;
        }
        
        return val;
    }
};