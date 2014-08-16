package semester_i.project_one;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HeartRate {

    private int month;
    private int day;
    private int year;

    private String first;
    private String last;

    public HeartRate(int month, int day, int year, String first, String last) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.first = first;
        this.last = last;
    }

    /**
     * @return the month of birth
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month of birth to the provided parameter.
     *
     * @param month The month to be set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the day of birth
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of birth to the provided parameter.
     *
     * @param day The day to be set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the year of birth
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of birth to the provided parameter.
     *
     * @param year The year to be set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the first name
     */
    public String getFirst() {
        return first;
    }

    /**
     * Sets the first name to the provided parameter.
     *
     * @param first The first name to be set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last name
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the last name to the provided parameter.
     *
     * @param last The last name to be set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * Calculates the age using the birthday entered.
     *
     * @return the age of the entered credentials
     */
    public int getAge() {
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        return month > this.month || month == this.month && day >= this.day ? year - this.year : year - this.year - 1;
    }

    /**
     * Calculates the maximum heart beats per minute using one's age
     *
     * @return the maximum bpm of the entered credentials
     */
    public int getMaximumBPM() {
        return 220 - getAge();
    }

    /**
     * Calculates the high end of the max recommended heart beats per minute
     *
     * @return the high rate of the max bpm
     */
    public double getHighRate() {
        return .85 * getMaximumBPM();
    }

    /**
     * Calculates the low end of the max recommended heart beats per minute
     *
     * @return the low rate of the max bpm
     */
    public double getLowRate() {
        return .5 * getMaximumBPM();
    }
}