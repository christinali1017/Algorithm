package leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class EvalRPN1 {
	public int evalRPN(String[] tokens){
		try{
			Stack<String> s = new Stack<String>();
			String operators = "+-*/";
			for(int i=0; i<tokens.length; i++){
				if(!operators.contains(tokens[i])){
					s.push(tokens[i]);
				}else{
					int a = Integer.valueOf(s.pop());
					int b = Integer.valueOf(s.pop());
					switch(operators.indexOf(tokens[i])){
					case 0: 
						s.push(String.valueOf(a+b));
						break;
					case 1: 
						s.push(String.valueOf(b-a));
						break;
					case 2: 
						s.push(String.valueOf(a*b));
						break;
					case 3: 
						s.push(String.valueOf(b/a));
						break;
					}
				}
			}
			return Integer.valueOf(s.pop());
		}catch(ArithmeticException e){
			return 0;
		}
		
	}
	
	
    public static ArrayList<Integer> transform(String prefix) {
        System.out.println("transform");
        int i, len = prefix.length();// 用字符数组保存前缀表达式
        prefix=prefix+ '#';// 让前缀表达式以'#'结尾
        Stack<Character> stack = new Stack<Character>();// 保存操作符的栈
        stack.push('#');// 首先让'#'入栈
        ArrayList<Integer> postfix = new ArrayList<Integer>();
        // 保存后缀表达式的列表,可能是数字，也可能是操作符，之前使用的是ArrayList
        for (i = 0; i < len + 1; i++) {
            System.out.println(i+" "+prefix.charAt(i));
            if (Character.isDigit(prefix.charAt(i))) {// 当前字符是一个数字
                if (Character.isDigit(prefix.charAt(i+1))) {// 当前字符的下一个字符也是数字(两位数)
                    postfix.add(10 * (prefix.charAt(i)-'0') + (prefix.charAt(i+1)-'0'));
                    i++;
                } else {// 当前字符的下一个字符不是数字(一位数)
                    postfix.add((prefix.charAt(i)-'0'));
                }
            } else {// 当前字符是一个操作符
                switch (prefix.charAt(i)) {
                case '(':// 如果是开括号
                    stack.push(prefix.charAt(i));// 开括号只是放入到栈中，不放入到后缀表达式中
                    break;
                case ')':// 如果是闭括号
                    while (stack.peek() != '(') {
                        postfix.add(stack.pop(), len);// 闭括号是不入栈的
                    }
                    stack.pop();// 弹出'('
                    break;
                default:// 默认情况下:+ - * /
                    while (stack.peek() != '#'
                            && compare(stack.peek(), prefix.charAt(i))) {
                        postfix.add(stack.pop(), len);// 不断弹栈，直到当前的操作符的优先级高于栈顶操作符
                    }
                    if (prefix.charAt(i) != '#') {// 如果当前的操作符不是'#'(结束符)，那么入操作符栈
                        stack.push(prefix.charAt(i));// 最后的标识符'#'是不入栈的
                    }
                    break;
                }
            }
        }
        return postfix;
    }
    
    //比较运算符之间的优先级
    public static boolean compare(char peek, char cur) {// 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        if (peek == '*'
                && (cur == '+' || cur == '-' || cur == '/' || cur == '*')) {// 如果cur是'('，那么cur的优先级高,如果是')'，是在上面处理
            return true;
        } else if (peek == '/'
                && (cur == '+' || cur == '-' || cur == '*' || cur == '/')) {
            return true;
        } else if (peek == '+' && (cur == '+' || cur == '-')) {
            return true;
        } else if (peek == '-' && (cur == '+' || cur == '-')) {
            return true;
        } else if (cur == '#') {// 这个很特别，这里说明到了中缀表达式的结尾，那么就要弹出操作符栈中的所有操作符到后缀表达式中
            return true;// 当cur为'#'时，cur的优先级算是最低的
        }
        return false;// 开括号是不用考虑的，它的优先级一定是最小的,cur一定是入栈
    }
    

	public static void main(String[] args) {
		String[] tokens = new String[]{"0","3","/"};
		EvalRPN1 er = new EvalRPN1();
		System.out.println(er.evalRPN(tokens));
		
	}

}
