package leetcode;

/**
 * The function returns the converted integral number as an int value,
 *  if successful. If no valid conversion could be performed then an zero value is returned. 
 *  If the value is out of range then INT_MAX or INT_MIN is returned.
 *  Function discards any whitespace characters until first non-whitespace character is found.
 *   Then it takes as many characters as possible to form a valid integer number representation and converts them to integer value. 
 *   The valid integer value consists of the following parts:
 *   (optional) plus or minus sign
 *	numeric digits
 */
public class StringToInteger {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        int res = 0;
        boolean isPositive = true; 
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                if (c == '-') {
                    isPositive = false;
                }
                continue;
            }
            if (!isNum(c)) {
                break;
            } else if (res > (Integer.MAX_VALUE - (c - '0')) / 10) {
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                res = res * 10 + (c - '0');
            }
        }
        return isPositive ? res : -res;
    }
    
    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
   
   public static void main(String[] args) {
	   StringToInteger s = new StringToInteger();
	   System.out.println(s.atoi("  abc"));
   }
   
		    
}
