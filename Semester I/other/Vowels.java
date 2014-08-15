package semester_i.other;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vowels {

    public static void main(String[] args) {
        int vowels = 0;
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);
        System.out.print("Enter a word or a line of text: ");
        String line = scanner.nextLine();
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            vowels++;
        }
        System.out.printf("Found %d vowels in \"%s\"", vowels, line);
    }
}