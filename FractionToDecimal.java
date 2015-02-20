package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0 || numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        boolean positive = ((numerator ^ denominator) >>> 31) == 0;
        if(!positive) res.append("-");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        res.append(num/den);
        if(num % den == 0) return res.toString();
        res.append(".");
        long mod = num % den;
        while(mod != 0){
            if(map.containsKey(mod)){
                res.insert(map.get(mod), "(");
                res.append(")");
                return res.toString();
            }
            map.put(mod, res.length());
            mod = mod * 10;
            long divide = mod/den;
            mod = mod % den;
            res.append(divide);
        }
        return res.toString();
    }
    public static void main(String[] args) {
    	FractionToDecimal f = new FractionToDecimal();
    	System.out.println(f.fractionToDecimal(3, 7));
	}
}
