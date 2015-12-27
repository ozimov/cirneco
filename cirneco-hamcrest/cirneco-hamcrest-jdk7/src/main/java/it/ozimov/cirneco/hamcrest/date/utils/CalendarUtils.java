package it.ozimov.cirneco.hamcrest.date.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.AM_PM;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

public class CalendarUtils {

    private CalendarUtils() {
    }

    public static Calendar fromDateToCalendar(final Date date) {
        final Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.setMinimalDaysInFirstWeek(4);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal;
    }

    public static int weekDay(final Date date) {
        return fromDateToCalendar(date).get(DAY_OF_WEEK);
    }

    /**
     * Returns the month from the day, i.e. a number between 1 (January) and 12 (December).
     */
    public static int month(final Date date) {
        return fromDateToCalendar(date).get(MONTH) + 1;
    }

    /**
     * Returns the week fo the year according to the ISO 8601 standard
     */
    public static int weekOfYear(final Date date) {
        return fromDateToCalendar(date).get(Calendar.WEEK_OF_YEAR);
    }

    public static int day(final Date date) {
        return fromDateToCalendar(date).get(Calendar.DAY_OF_MONTH);
    }

    public static int dayOfYear(final Date date) {
        return fromDateToCalendar(date).get(Calendar.DAY_OF_YEAR);
    }

    public static int year(final Date date) {
        return fromDateToCalendar(date).get(YEAR);
    }

    public static boolean isLeapYear(final Date date) {
        return new GregorianCalendar().isLeapYear(year(date));
    }

    public static boolean isAM(final Date date) {
        return fromDateToCalendar(date).get(AM_PM) == 0;
    }

    public static boolean isPM(final Date date) {
        return !isAM(date);
    }

    public static int hour12(final Date date) {
        return fromDateToCalendar(date).get(HOUR);
    }

    public static int hour24(final Date date) {
        return fromDateToCalendar(date).get(HOUR_OF_DAY);
    }

    public static int minute(final Date date) {
        return fromDateToCalendar(date).get(MINUTE);
    }

    public static int second(final Date date) {
        return fromDateToCalendar(date).get(SECOND);
    }

    public static int millisecond(final Date date) {
        return fromDateToCalendar(date).get(MILLISECOND);
    }

}
