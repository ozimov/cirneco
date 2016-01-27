package it.ozimov.cirneco.hamcrest.java7.date;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import it.ozimov.cirneco.hamcrest.java7.date.utils.CalendarUtils;
import it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod;

/**
 * it.ozimov.cirneco.hamcrest.java7.Is {@linkplain Date} in a given week day?
 *
 * @since  version 0.1 for JDK7
 */
public class IsDateWithTime extends TypeSafeMatcher<Date> {

    private final Integer hour;
    private final Integer min;
    private final Integer sec;
    private final Integer millis;
    private final ClockPeriod clockPeriod;

    public IsDateWithTime(final Integer hour, final Integer min, final Integer sec, final Integer millis) {
        this(hour, ClockPeriod.TWENTYFOUR_HOURS, min, sec, millis);
    }

    public IsDateWithTime(final Integer hour, final ClockPeriod clockPeriod, final Integer min, final Integer sec,
            final Integer millis) {
        checkArgument((hour != null) || (min != null) || (sec != null) || (millis != null),
            "The matcher need one between hour, min, sec and millis to be non null");
        checkArgument((hour == null) || ((hour >= 0) && (hour <= 23) && (clockPeriod == ClockPeriod.TWENTYFOUR_HOURS))
                || ((hour >= 0) && (hour <= 12)),
            "The value hour must be null or a number between 0 and 23 in a 24hours clock period or between 0 and 11 in a 12hours clock period");
        checkArgument((min == null) || ((min >= 0) && (min <= 59)),
            "The value min must be null or a number between 0 and 59");
        checkArgument((sec == null) || ((sec >= 0) && (sec <= 59)),
            "The value sec must be null or a number between 0 and 59");
        checkArgument((millis == null) || ((millis >= 0) && (millis <= 999)),
            "The value millis must be null or a number between 0 and 999");

        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.millis = millis;
        this.clockPeriod = clockPeriod;
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>hour</code> in a 24 hours
     * clock period.
     */
    public static Matcher<Date> hasHour(final int hour) {
        return new IsDateWithTime(hour, null, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> and
     * <code>ClockPeriod</code> (e.g. <em>AM</em>).
     */
    public static Matcher<Date> hasHour(final int hour, final ClockPeriod clockPeriod) {
        return new IsDateWithTime(hour, clockPeriod, null, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>minute</code>.
     */
    public static Matcher<Date> hasMinute(final int minute) {
        return new IsDateWithTime(null, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>sec</code>.
     */
    public static Matcher<Date> hasSecond(final int second) {
        return new IsDateWithTime(null, null, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>millis</code>.
     */
    public static Matcher<Date> hasMillisecond(final int millisecond) {
        return new IsDateWithTime(null, null, null, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final int minute) {
        return new IsDateWithTime(hour, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>) and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final ClockPeriod clockPeriod, final int minute) {
        return new IsDateWithTime(hour, clockPeriod, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period, <code>minute</code> and <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final int hour, final int minute, final int second) {
        return new IsDateWithTime(hour, minute, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code> and <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final int hour, final ClockPeriod clockPeriod, final int minute,
            final int second) {
        return new IsDateWithTime(hour, clockPeriod, minute, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period, <code>minute</code>, <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final int hour, final int minute, final int second,
            final int millisecond) {
        return new IsDateWithTime(hour, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code>, <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final int hour, final ClockPeriod clockPeriod, final int minute,
            final int second, final int millisecond) {
        return new IsDateWithTime(hour, clockPeriod, minute, second, millisecond);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        boolean matches = true;

        if (hour != null) {

            switch (clockPeriod) {

                case AM :
                    matches = (hour == CalendarUtils.hour12(date)) && CalendarUtils.isAM(date);

                    break;

                case PM :
                    matches = (hour == CalendarUtils.hour12(date)) && CalendarUtils.isPM(date);

                    break;

                default :
                    matches = hour == CalendarUtils.hour24(date);

                    break;
            }
        }

        if (matches && (min != null)) {
            matches = min == CalendarUtils.minute(date);
        }

        if (matches && (sec != null)) {
            matches = sec == CalendarUtils.second(date);
        }

        if (matches && (millis != null)) {
            matches = millis == CalendarUtils.millisecond(date);
        }

        return matches;
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date).appendText(" has not");
        expectedMatching(mismatchDescription);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a date with");
        expectedMatching(description);
    }

    private void expectedMatching(final Description description) {
        boolean first = true;

        if (hour != null) {
            description.appendText(" hour ");

            switch (clockPeriod) {

                case AM :
                    description.appendText(String.format("<%d AM>", hour));

                    break;

                case PM :
                    description.appendText(String.format("<%d PM>", hour));

                    break;

                default :
                    description.appendValue(hour);

                    break;
            }

            first = false;
        }

        if (min != null) {

            if (!first) {
                description.appendText(",");
            }

            description.appendText(" minute ").appendValue(min);
            first = false;
        }

        if (sec != null) {

            if (!first) {
                description.appendText(",");
            }

            description.appendText(" second ").appendValue(sec);
            first = false;
        }

        if (millis != null) {

            if (!first) {
                description.appendText(",");
            }

            description.appendText(" millisecond ").appendValue(millis);
        }
    }

}
