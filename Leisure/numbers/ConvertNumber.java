package numbers;

import java.math.BigInteger;

/**
 * This program assumes all values passed to it through the command line arguments are of integer representation.
 * Furthermore, it will only work on the subset of numbers from negative 999 quintillion (-999,999,999,999,999,999,999)
 * to 999 quintillion (999,999,999,999,999,999,999). Values exceeding the range will not return a value. Additionally,
 * numbers must not contain any form of punctuation, such as commas or periods. Multiple numbers can be supplied at
 * the same time, which may be done by specifying them as command line arguments with a space between each number.
 * It's important to note that this file was compiled with JDK 7, meaning the user will need to have a minimum version
 * of Java SE 7.
 *
 * @author Jacob Doiron
 * @since 10/29/2015
 */
public class ConvertNumber {

    // Contains multiples of 10
    private static final String[] TEN_MULTS = {
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    // Contains numbers with values less than 20
    private static final String[] SUB_TWENTY = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final Unit[] UNITS = Unit.values();

    private static final BigInteger MIN = new BigInteger("-999999999999999999999");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger TEN = new BigInteger("10");
    private static final BigInteger TWENTY = new BigInteger("20");
    private static final BigInteger ONE_HUNDRED = new BigInteger("100");
    private static final BigInteger MAX = new BigInteger("999999999999999999999");

    /**
     * Converts an integer into its String representation recursively. To accomplish this, it starts by checking if
     * there is a negative sign leading the number. If there is, it adds the "negative" prefix. After that, it checks
     * the current value of n and finds which branch it falls into. Each time it does this, it appends a part of the
     * number in its entirety. To avoid appending "zero" on certain cases, it checks n for equaling 0 and if the
     * builder's current length is 0 or not.
     *
     * @param n The current integer value.
     * @param text The StringBuilder to append text to.
     * @param suffix The suffix to be appended.
     * @return The number represented as its text equivalent.
     */
    public static String numToText(BigInteger n, String text, String suffix) {
        if (suffix != null) {
            text += suffix;
        }
        if (n.compareTo(MIN) != -1 && n.compareTo(MAX) != 1) {
            if (n.compareTo(BigInteger.ZERO) == -1) {
                return numToText(n.negate(), text, "negative ");
            } else if (n.compareTo(TWENTY) == -1) {
                if ((n.compareTo(BigInteger.ZERO) == 0 && text.length() == 0) || n.compareTo(BigInteger.ZERO) != 0) {
                    return text + SUB_TWENTY[n.intValue()];
                }
            } else if (n.compareTo(ONE_HUNDRED) == -1) {
                text += TEN_MULTS[n.divide(TEN).subtract(TWO).intValue()];
                return numToText(n.mod(TEN), text, " ");
            } else {
                int index = 0;
                Unit unit;
                for (int i = 0; i < UNITS.length; i++) {
                    if (n.compareTo(UNITS[i].getValue()) == -1) {
                        index = i - 1;
                        break;
                    }
                }
                unit = UNITS[index];
                String first = numToText(n.divide(unit.getValue()), text, null);
                String second = numToText(n.mod(unit.getValue()), "", unit.getRepresentation());
                return first + second;
            }
        } else {
            throw new IllegalArgumentException(n.compareTo(MIN) == -1 ? n + " < " + MIN : n + " > " + MAX);
        }
        return text;
    }

    /**
     * Main function which uses its command line arguments and recursively returns their text equivalences.
     *
     * @param args The command line arguments to supply.
     */
    public static void main(String... args) {
        for (String n : args) {
            BigInteger number = new BigInteger(n);
            String answer = numToText(number, "", null);
            if (answer != null) {
                String result = answer.replaceAll("\\s+", " ").trim();
                System.out.println(n + ": " + result);
            }
        }
    }
}
