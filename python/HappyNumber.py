class Solution:
    # @param {integer} n
    # @return {boolean}
    def isHappy(self, n):
        m = n
        mem = set()
        while m not in mem:
            mem.add(m)
            m = sum([int(x) ** 2 for x in str(m)])
            if m == 1:
                return True
        return False

