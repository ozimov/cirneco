package it.ozimov.cirneco.hamcrest.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTestUtils {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static Date date(final int year, final int month, final int day, final int hour, final int minute,
                            final int second, final int millisecond) throws ParseException {
        final DateTimeZone dateTimeZone = DateTimeZone.forOffsetHours(0);
        final DateTime dateTime = new DateTime(year, month, day, hour, minute, second, millisecond, dateTimeZone);

        return new SimpleDateFormat(FORMAT).parse(dateTime.toString(FORMAT));
    }

    public static Date date(final int year, final int month, final int day, final int hour) throws ParseException {
        return date(year, month, day, hour, 0, 0, 0);
    }

    public static Date date(final int year, final int month, final int day, final int hour, final int minute)
            throws ParseException {
        return date(year, month, day, hour, minute, 0, 0);
    }

    public static Date date(final int year, final int month, final int day, final int hour, final int minute,
                            final int second) throws ParseException {
        return date(year, month, day, hour, minute, second, 0);
    }

    public static Date date(final int year, final int month, final int day) throws ParseException {
        return date(year, month, day, 0, 0, 0, 0);
    }

}
