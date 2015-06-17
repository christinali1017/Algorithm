class Solution:
    # @return an integer
    def lengthOfLongestSubstring(self, s):
        if (len(s) <= 1): return len(s)
        
        d = {}
        maxl, back = 1, 0
        for i, c in enumerate(s):
            if c not in d:
                d[c] = i
            elif d[c] >= back:
                back = d[c] + 1
            d[c] = i
            if maxl < i - back + 1:
                maxl = i - back + 1
        
        return maxl