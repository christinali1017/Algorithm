class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        n  = len(A)
        if n <= 1:
            return n

        c, m = A[0], 1
        for i in xrange(n):
            if A[i] == c:
                continue
            A[m], c = A[i], A[i]
            m += 1

        return m

