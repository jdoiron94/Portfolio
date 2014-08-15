package semester_iii.stacks;

import java.util.Stack;

public class Stacks {

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        for (char c : "((()())())".toCharArray()) {
            if (c == '(') {
                System.out.println("Pushing (");
                stack.push(c);
            } else if (c == ')' && !stack.empty()) {
                System.out.println("Popping )");
                stack.pop();
            } else {
                System.err.println("ERROR");
                return;
            }
        }
        System.out.println(stack.empty() ? "\nBalanced" : "\nUnbalanced");
    }
}