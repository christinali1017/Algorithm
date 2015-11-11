class Solution {
    public:
    int lengthOfLongestSubstring(string s) {
        if (s.size() <= 1) { return s.size(); }
        
        int back = 0, maxl = 1, i = -1;
        map<char, int> d;
        
        for(auto it = s.begin(); it != s.end(); it ++) {
            i ++;
            if (d.find(*it) != d.end()) {
                int indx = d.find(*it)->second;
                if (indx >= back) {
                    back = indx + 1;
                }
            }
            d[*it] = i;
            if (maxl < i - back + 1) {
                maxl = i - back + 1;
            }
        }
        
        return maxl;
    }
};