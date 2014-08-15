package semester_i.valid_date;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Day {

    private final int month;
    private final int day;
    private final int year;

    public Day(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public boolean isValid() {
        GregorianCalendar calendar = new GregorianCalendar();
        int[] thirty = {Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER};
        int[] thirtyOne = {Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER};
        if (month == Calendar.FEBRUARY + 1) {
            if (calendar.isLeapYear(year)) {
                return day <= 29;
            }
            return day <= 28;
        }
        for (int month : thirty) {
            if (month + 1 == this.month) {
                return day <= 30;
            }
        }
        for (int month : thirtyOne) {
            if (month + 1 == this.month) {
                return day <= 31;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}