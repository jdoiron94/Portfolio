/**
 * This program assumes all values passed to it through the command line arguments are of integer representation.
 * Furthermore, it will only work on the subset of numbers from -999,999 to 999,999. Values exceeding the range will
 * not return a value. Additionally, numbers must not contain any form of punctuation, such as commas or periods.
 * Multiple numbers can be supplied at the same time, which may be done by specifying them as command line arguments
 * with a space between each number. It's important to note that this file was compiled with JDK 7, meaning the user
 * will need to have a minimum version of Java SE 7.
 *
 * @author Jacob
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

    /**
     * Converts an integer into its String representation recursively. To accomplish this, it starts by checking if
     * there is a negative sign leading the number. If there is, it adds the "negative" prefix. After that, it checks
     * the current value of n and finds which branch it falls into. Each time it does this, it appends a part of the
     * number in its entirety. To avoid appending "zero" on certain cases, it checks n for equaling 0 and if the
     * builder's current length is 0 or not.
     *
     * @param n The current integer value.
     * @param builder The StringBuilder to append text to.
     * @param suffix The suffix to be appended.
     */
    public static void recursiveNum(int n, StringBuilder builder, String suffix) {
        if (suffix != null) {
            builder.append(suffix);
        }
        if (n < 0) {
            recursiveNum(-n, builder, "negative ");
        } else if (n < 20) {
            if ((n == 0 && builder.length() == 0) || n != 0) {
                builder.append(SUB_TWENTY[n]);
            }
        } else if (n < 100) {
            builder.append(TEN_MULTS[(n / 10) - 2]);
            recursiveNum(n % 10, builder, " ");
        } else if (n < 1000000) {
            int factor = n < 1000 ? 100 : 1000;
            String suf = factor == 100 ? " hundred " : " thousand ";
            recursiveNum(n / factor, builder, null);
            recursiveNum(n % factor, builder, suf);
        }
    }

    /**
     * Main function which uses its command line arguments and recursively gets its text representation.
     *
     * @param args The command line arguments to supply.
     */
    public static void main(String... args) {
        StringBuilder builder;
        for (String n : args) {
            builder = new StringBuilder();
            recursiveNum(Integer.parseInt(n), builder, null);
            System.out.println(builder.toString().replaceAll("\\s+", " ").trim());
        }
    }
}
