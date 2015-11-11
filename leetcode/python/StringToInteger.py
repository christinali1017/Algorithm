class Solution:
    INT_MIN = -2147483648
    INT_MAX = 2147483647
    
    # @return an integer
    def atoi(self, str):
        num = 0
        
        for ii in xrange(len(str)):
            if str[ii] != " ":
                break
        else:
            return 0
        
        if str[ii] == "-":
            neg = True
            ii += 1
        elif str[ii] == "+":
            neg = False
            ii += 1
        else:
            neg = False

        for ii in xrange(ii, len(str)):
            if "0" <= str[ii] <= "9":
                if neg:
                    num = num * 10 - int(str[ii])
                else:
                    num = num * 10 + int(str[ii])
            else:
                break

        if num < self.INT_MIN:
            return self.INT_MIN
elif num > self.INT_MAX:
    return self.INT_MAX
        
        return num
