class Solution:
    def median(self, array):
        n = len(array)
        if n == 1:
            return array[0]
        elif n % 2 == 0:
            return float((array[n/2] + array[n/2 - 1])) / 2
        else:
            return array[n/2]

    def shrink(self, A, B, A_isleft):
        a, b = len(A), len(B)
        n = (b-1) / 2 if a > b else (a-1) / 2
        if A_isleft:
            return A[:a-n], B[n:]
        else:
            return A[n:], B[:b-n]

    # @return a float
    def findMedianSortedArrays(self, A, B):
        if not A and not B: raise ValueError(message="Two empty arrays")

        if not A:
            return self.median(B)

        if not B:
            return self.median(A)

        if len(A) <= 2 or len(B) <= 2:
            return self.median(sorted(A+B))

        A_isleft = self.median(A) > self.median(B)
        a, b = self.shrink(A, B, A_isleft)
        return self.findMedianSortedArrays(a, b)
