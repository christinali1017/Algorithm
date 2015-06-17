class Solution:
    # @param s, a string
    # @return an integer
    def titleToNumber(self, s):
        l = len(s)
        x = 1
        val = 0
        
        for ii in xrange(l-1, -1, -1):
            val += x * (ord(s[ii]) - 64)
            x *= 26
        
        return val