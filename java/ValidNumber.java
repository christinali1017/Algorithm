package leetcode;
public class ValidNumber {
	/**
	 * 符号位‘+’，‘-’，小数点‘.’，还有‘e’和‘E’，剩下的就只有数字0-9
	 * 其他字符如果出现就是非法字符，返回false。
	 * 数字字符在哪里出现都是ok的，我们主要考虑几个特殊字符的情况。
	 * 小数点:（1）前面不能有小数点或者‘e’和‘E’；
	 * 		 （2）前一位是数字（不能是第一位）或者后一位要是数字
	 * 
     * 正负号：（1）必须是第一位或者在‘e’和‘E’后一位；
     * 		 （2）后一位要是数字。
     * 
	 * ‘e’和‘E’（1）前面不能有‘e’和‘E’出现过；
	 * 			（2）不能是第一位（前面没数字科学计数没有意义）或者最后一位（后面没数字就不用写指数了）。
	 * 			（3）e 后必须是整数(可以有＋ or －)
	 */
	
    public boolean isNumber1(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        s = s.trim();
        boolean eE = false;
        boolean dot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //check if character valid, if e|E at the beginning , if -|+ at the end
            if (!isValidCharacter(c) || (i == 0 && (c == 'e' || c == 'E')) || (i == s.length() -1  && (c == '-' || c == '+'))) {
               return false;
            }
            //if e|E, e|E must hasn't existed before, must be a number of + - after it
            if ((c == 'e' || c == 'E')) {
                if (eE || (i == s.length() -1 || (!isNumber(s.charAt(i+1)) && s.charAt(i+1) != '-' && s.charAt(i+1) != '+'))) {
                    return false;
                }
                eE = true;
            }
            //if -|+, must at the beginning or after e|E, must be a . or number after it
            if ((c == '-' || c == '+')) {
                if((!isNumber(s.charAt(i+1)) && s.charAt(i+1) != '.') || (i != 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E')) {
                    return false;
                }
            }
            // if ., .|e|E must hasn't existed before, must has a number before it or after it. 
           if (c == '.'){
               if (dot || eE ||  s.length() == 1 || (i == 0 && !isNumber(s.charAt(i+1))) || ( i == s.length() - 1 && !isNumber(s.charAt(i-1))) || (i != 0 && i != s.length() -1 && !isNumber(s.charAt(i-1)) && !isNumber(s.charAt(i+1)))) {
                   return false;
               } 
               dot = true;
           }
        }
        return true;
    }
    
	 public boolean isNumber2(String s) {
	        if (s == null || s.trim().length() == 0) {
	            return false;
	        }
	        s = s.trim();
	        boolean eE = false;
	        boolean dot = false;
	        for (int i = 0; i < s.length(); i++) {
	           switch(s.charAt(i)) {
	               case 'e':
	               case 'E':
	                    if (eE || i == s.length() -1 || i == 0) {
	                    return false;
	                }
	                    eE = true;
	                    break;
	                case '-':
	                case '+':
	                     if ((i == s.length() -1 || !((s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') || s.charAt(i+1) == '.') || (i != 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E'))) {
	                    return false;
	                    }
	                    break;
	                case '.':
	                    if (dot || eE || s.length() == 1 || (i == 0 && !isNumber(s.charAt(i+1))) || ( i == s.length() - 1 && !isNumber(s.charAt(i-1))) || (i != 0 && i != s.length() -1 && !isNumber(s.charAt(i-1)) && !isNumber(s.charAt(i+1)))) {
	                   return false;
	                    } 
	                    dot = true;
	                    break;
	                case '0':
	        		case '1':
	        		case '2':
	        		case '3':
	        		case '4':
	        		case '5':
	        		case '6':
	        		case '7':
	        		case '8':
	        		case '9':
	        			break;
	            	default:
	            			return false;
	           }
	        }
	        return true;
	    }
	    
    public boolean isNumber(String s) {
        if(s == null || s.trim().length() == 0) return false;
        boolean dot = false;
        boolean e = false;
        s = s.trim();
        for(int i = 0; i < s.length(); i++){
        	switch(s.charAt(i))
        	{
        	case '.':  
                if(dot || e   
                || ((i==0||!(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'))   
                    && (i==s.length()-1||!(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'))))  
                    return false;  
                dot = true;  
                break;  
            case '+':  
            case '-':  
                if((i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))  
                  || (i==s.length()-1 || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'||s.charAt(i+1)=='.')))  
                    return false;  
                break;  
            case 'e':  
            case 'E':  
                if(e || i==s.length()-1 || i==0)  
                    return false;  
                e = true;  
                break;  
    		case '0':
    		case '1':
    		case '2':
    		case '3':
    		case '4':
    		case '5':
    		case '6':
    		case '7':
    		case '8':
    		case '9':
    			break;
        	default:
        			return false;
        			
        	}
        }
        return true;
    }
    
    

    
    public boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
    
    public boolean isValidCharacter(char c) {
        if (isNumber(c) || c == '-' || c == '+' || c == '.' || c == 'e' || c == 'E') {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
    	ValidNumber v = new ValidNumber();
    	System.out.println(v.isNumber(" 005047e+6"));
	}

}
