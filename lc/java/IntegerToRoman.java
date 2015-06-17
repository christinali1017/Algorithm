package leetcode;
public class IntegerToRoman {
	
    public String intToRoman1(int num) {
        if(num <= 0 || num >= 4000) return "";
        int divide = 1000;
        int[] digits = new int[4];
        StringBuilder res = new StringBuilder();
        for(int i = 3; i >= 0 && num > 0; i--){
            digits[i] = num/divide;
            num = num % divide;
            divide /= 10;
        }
        res.append(helper(digits[3], 'M', ' ', ' '));
        res.append(helper(digits[2], 'C', 'D', 'M'));
        res.append(helper(digits[1], 'X', 'L', 'C'));
        res.append(helper(digits[0], 'I', 'V', 'X'));
        return res.toString();
    }
    
    public String helper(int i, char one, char five, char ten){
        StringBuilder res = new StringBuilder();
        switch(i){
            case 9: res.append(one + "" + ten); break;
            case 8: res.append(five + "" + one + "" + one + "" + one); break;
            case 7: res.append(five + "" + one + "" + one); break;
            case 6: res.append(five + "" + one); break;
            case 5: res.append(five); break;
            case 4: res.append(one + "" + five); break;
            case 3: res.append(one + "" + one + "" + one); break;
            case 2: res.append(one + "" + one); break;
            case 1: res.append(one);
        }
        return res.toString();
    }
    
    public String intToRoman(int num) {
    	if(num <= 0 || num >= 4000) return "";
    	int divide = 1000;
    	int[] digits = new int[4];
    	for(int i = 3; i >= 0 && num > 0; i--){
             digits[i] = num/divide;
             num = num % divide;
             divide /= 10;
         }
    	StringBuilder result = new StringBuilder();
    	result.append(convert(digits[3], 'M', ' ', ' '));
    	result.append(convert(digits[2], 'C', 'D', 'M'));
    	result.append(convert(digits[1], 'X', 'L', 'C'));
    	result.append(convert(digits[0], 'I', 'V', 'X'));
        
    	return result.toString();
    }
    
    public String convert(int digit, char one, char five, char ten){
    	StringBuilder result = new StringBuilder();
    	switch(digit)
    	{
    		case 9:
    			result.append(one);
    			result.append(ten);
    			break;
    		case 8:
    		case 7:
    		case 6:
    		case 5:
    			result.append(five);
    			for(int i = 5; i < digit; i++) result.append(one);
    			break;
    		case 4: 
    			result.append(one);
    			result.append(five);
    			break;
    		case 3:
    		case 2:
    		case 1:
    			for(int i = 0; i < digit; i++) result.append(one);
    			break;
    		default:
    			break;
    	}
    	return result.toString();
    }
    
    public static void main(String[] args) {
    	IntegerToRoman i = new IntegerToRoman();
    	System.out.println(i.intToRoman(501));
	}

}
