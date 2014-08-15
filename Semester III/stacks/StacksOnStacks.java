package stacks;

import java.util.Stack;

public class StacksOnStacks {

	/* infix to postfix
       postfix to result

	   postfix evaluation algorithm:

	   - if current char is number, push it
	     else if char is operator, pop appropriate amount

	   infix to postfix:
	   - strip whitespace
	   - deal with multiple character operands
	   - error handling

	   infix to postfix algorithm:

	   - read a symbol
	     if:
	        number -> immediately output
	        close paren -> pop stack symbols until an open paren appears
	        operators -> pop all stack symbols until a symbol of lower precedence or a right-associative symbol (exponent) of equal precedence appears, then push the operator
	        open paren -> push
	     end of input, pop all remaining stack symbols
	*/

    private static boolean palindrome(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        for (char c : s.toCharArray()) {
            if (stack.peek() == c) {
                stack.pop();
            }
        }
        return stack.empty();
    }

    private static boolean equationCheck(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (c == '{') {
                stack.push('{');
            } else if (c == '}' && stack.peek() == '{') {
                stack.pop();
            } else if (c == '[') {
                stack.push('[');
            } else if (c == ']' && stack.peek() == '[') {
                stack.pop();
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println("Abc palindrome: " + palindrome("abc") + "\nRacecar palindrome: " + palindrome("racecar"));
        System.out.println("Balanced equation: " + equationCheck("4(2+x(3*9))") + "\nUnbalanced equation: " + equationCheck("{4+(5+3+(6-2])}"));
    }
}