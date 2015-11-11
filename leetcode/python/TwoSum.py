class Solution:
    # @return a tuple, (index1, index2)
    def twoSum(self, num, target):
        d = {}
        for i, n in enumerate(num):
            if target - n in d:
                return (d[target-n] + 1, i + 1)
            d[n] = i

