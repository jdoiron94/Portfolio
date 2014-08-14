package stack_calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Jacob Doiron
 * COSC 201
 * StackCalculator.java, Project II
 * Wrapper class used as a text-based calculator, using stacks
 */
public class StackCalculator {

    private final Map<Character, Integer> variables;
    private String error;
    private String infix;
    private String postfix;
    private boolean assignment = false;
    private char variable = '\n';

    public StackCalculator() {
        variables = new HashMap<>();
    }

    /*
     *  Main method which will take the equation as the parameter and then perform all of the necessary tasks in an
     *  attempt to solve said equation
     */
    public void processInput(String input) {
        infix = input;
        infix = clean();
        if (balancedParentheses()) {
            if (validSymbols()) {
                if (balancedOperators()) {
                    if (validVariable(infix.split(" ")[0]) && infix.split(" ")[1].equals("=") && number(infix.split(" ")[2])) {
                        if (infix.split(" ").length == 3) {
                            setVariable(infix.charAt(0), Integer.parseInt(infix.split(" ")[2]));
                            return;
                        } else {
                            variable = infix.split(" ")[0].charAt(0);
                            infix = infix.split(" = ")[1];
                            assignment = true;
                        }
                    }
                    for (int i = 0; i < infix.split(" ").length; i++) {
                        String string = infix.split(" ")[i];
                        if (!number(string) && !operator(string)) {
                            if (validVariable(string)) {
                                if (definedVariable(string.charAt(0))) {
                                    infix = infix.replace(string, Integer.toString(variables.get(string.charAt(0))));
                                } else {
                                    System.out.println("Undefined variable: " + string);
                                    return;
                                }
                            } else {
                                System.out.println("Invalid variable: " + string);
                                return;
                            }
                        }
                    }
                    if (hasAssignment()) {
                        String[] split = infix.split(" ");
                        if (split[1].equals("=") && split.length == 3) {
                            setVariable(infix.charAt(0), Integer.parseInt(split[2]));
                        } else {
                            infix = infix.split(" = ")[1];
                            postfix = convert();
                            setVariable(split[0].charAt(0), evaluate());
                        }
                    } else {
                        postfix = convert();
                        System.out.println(postfix);
                        int answer = evaluate();
                        if (assignment) {
                            setVariable(variable, answer);
                            assignment = false;
                            variable = '\n';
                        } else {
                            System.out.println(answer);
                        }
                    }
                } else {
                    System.out.println("Nonsensical input: " + input);
                }
            } else {
                System.out.println(error);
            }
        } else {
            System.out.println(error);
        }
    }

    /*
     *  A simple method which will compute the value of the postfix String and return the result
    */
    private int evaluate() {
        if (postfix.length() == 0) {
            return 0;
        }
        Stack<Integer> operands = new Stack<>();
        for (String string : postfix.split(" ")) {
            if (number(string)) {
                operands.push(Integer.parseInt(string));
            } else if (operator(string)) {
                int[] values = {operands.pop(), operands.pop()};
                operands.push(math(values, string));
            } else {
                try {
                    operands.push(variables.get(string.charAt(0)));
                } catch (Exception ignored) {
                    System.out.println("Undefined variable: " + string.charAt(0));
                }
            }
        }
        return operands.pop();
    }

    /*
     *  A method which takes the two operands in an array and a String representing the operator and performs the
     *  correct operation between the two
    */
    private int math(int[] operands, String operator) {
        switch (operator) {
            case "-":
                return operands[1] - operands[0];
            case "+":
                return operands[1] + operands[0];
            case "%":
                return operands[1] % operands[0];
            case "/":
                return operands[1] / operands[0];
            case "*":
                return operands[1] * operands[0];
            case "^":
                return (int) Math.pow((double) operands[1], (double) operands[0]);
            default:
                return -1;
        }
    }

    /*
     *  The conversion method which takes the clean infix String and utilizes a stack and StringBuilder to form the
     *  postfix equation (RPN)
    */
    private String convert() {
        Stack<Operator> operators = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (String string : infix.split(" ")) {
            if (string.length() > 0) {
                char first = string.charAt(0);
                if (number(string)) {
                    builder.append(string);
                    builder.append(' ');
                } else if (operator(string)) {
                    if (first == '(') {
                        operators.push(Operator.PARENTHESES);
                    } else if (first == ')') {
                        while (!operators.empty()) {
                            Operator operator = operators.pop();
                            if (operator == Operator.PARENTHESES) {
                                break;
                            }
                            builder.append(operator.getOperator());
                            builder.append(' ');
                        }
                    } else {
                        Operator operator = null;
                        for (Operator op : Operator.values()) {
                            if (first == op.getOperator()) {
                                operator = op;
                                break;
                            }
                        }
                        for (int i = 0; i < operators.size(); i++) {
                            Operator o = operators.peek();
                            if (o == Operator.PARENTHESES || (operator == Operator.EXPONENTIATION && o == Operator.EXPONENTIATION)) {
                                break;
                            } else if (operator.getPrecedence() <= o.getPrecedence()) {
                                builder.append(o.getOperator());
                                builder.append(' ');
                                operators.pop();
                            } else {
                                break;
                            }
                        }
                        operators.push(operator);
                    }
                }
            }
        }
        while (!operators.empty()) {
            Operator operator = operators.pop();
            builder.append(operator.getOperator());
            builder.append(' ');
        }
        return builder.toString().trim();
    }

    /*
     *  A method which takes the infix equation and then strips whitespace before making it legible
    */
    private String clean() {
        infix = infix.replaceAll("\\s+", "");
        StringBuilder builder = new StringBuilder();
        for (char character : infix.toCharArray()) {
            if (!validVariable(Character.toString(character)) && !number(Character.toString(character))) {
                builder.append(' ');
                builder.append(character);
                builder.append(' ');
                continue;
            }
            builder.append(character);
        }
        infix = infix.replace("(  ", "( ").replace("  )", " )");
        return builder.toString().trim();
    }

    /*
     *  Helper method to determine if the equation has balanced parentheses
     */
    private boolean balancedParentheses() {
        Stack<Character> parentheses = new Stack<>();
        for (char c : infix.toCharArray()) {
            switch (c) {
                case '(':
                    parentheses.push('(');
                    break;
                case ')':
                    if (parentheses.empty()) {
                        error = "Unbalanced parentheses (too many right)";
                        return false;
                    } else {
                        parentheses.pop();
                    }
                    break;
                default:
                    break;
            }
        }
        if (!parentheses.empty()) {
            error = "Unbalanced parentheses (too many left)";
            return false;
        }
        return true;
    }

    /*
     *  A helper method which checks for irregular operator frequencies (consecutive operators)
     */
    private boolean balancedOperators() {
        String last = "\n";
        for (String string : infix.split(" ")) {
            if (string.replaceAll("[()]", "").length() != 0) {
                if (operator(string) && operator(last)) {
                    return false;
                }
                last = string;
            }
        }
        return true;
    }

    /*
     *  Checks for irregular symbols such as the tilde
     */
    private boolean validSymbols() {
        for (String string : infix.split(" ")) {
            if (string.replaceAll("[a-zA-Z]", "").length() > 0 && !number(string) && !operator(string)) {
                error = "Invalid symbol: " + string;
                return false;
            }
        }
        return true;
    }

    /*
     *  Determines if the infix contains a basic assignment (variable = value)
     */
    private boolean hasAssignment() {
        String[] split = infix.split(" ");
        return split[0].length() == 1 && validVariable(split[0]) && split[1].equals("=") && number(split[2]);
    }

    /*
     *  A simple method to determine if a String represents a valid variable name
     */
    private boolean validVariable(String variable) {
        if (variable.length() == 1) {
            int value = (int) variable.charAt(0);
            return (value >= 65 && value <= 90) || (value >= 97 && value <= 122);
        }
        return false;
    }

    /*
     *  Checks if the given character has been previously defined throughout the program
     */
    private boolean definedVariable(char variable) {
        return variables.containsKey(variable);
    }

    /*
     *  A method which sets the given variable to its associated value
     */
    private void setVariable(char variable, int value) {
        variables.put(variable, value);
        System.out.println(variable + " set to " + value);
    }

    /*
     *  A simple boolean method which determines if a given String represents a number
    */
    private boolean number(String string) {
        return string.replaceAll("[0-9]", "").length() == 0;
    }

    /*
     *  A simple boolean method which determines if a given String represents an operator
     */
    private boolean operator(String string) {
        return string.replaceAll("[=+%/*^()-]", "").length() == 0;
    }

    /*
     *  A basic enum which represents all operators and their precedences
     */
    private enum Operator {

        EQUALS('=', 0),
        SUBTRACTION('-', 1),
        ADDITION('+', 1),
        MODULUS('%', 2),
        DIVISION('/', 2),
        MULTIPLICATION('*', 2),
        EXPONENTIATION('^', 3),
        PARENTHESES('(', 4);

        private final char operator;
        private final int precedence;

        private Operator(char operator, int precedence) {
            this.operator = operator;
            this.precedence = precedence;
        }

        public char getOperator() {
            return operator;
        }

        public int getPrecedence() {
            return precedence;
        }
    }
}