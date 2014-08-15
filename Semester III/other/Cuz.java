package semester_iii.other;

import java.util.Arrays;

public class Cuz {

    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static int[] getChars(String str) {
        int[] values = new int[str.length()];
        char[] chars = str.toCharArray();
        for (int i = 0; i < values.length; i++) {
            values[i] = ALPHABET.indexOf(chars[i]);
        }
        return values;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(getChars("kqfnjzykwj qpmljd uki xaegd aaf dua")));
    }
}