package org.cirneco.assertions.hamcrest.date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.hour12;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.hour24;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.isAM;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.isPM;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.millisecond;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.minute;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.second;

/**
 * Is {@linkplain Date} in a given week day?
 */
public class IsDateWithTime extends TypeSafeMatcher<Date> {

    private Integer hour;
    private Integer min;
    private Integer sec;
    private Integer millis;
    private ClockPeriod clockPeriod;

    public IsDateWithTime(final Integer hour, final Integer min, final Integer sec, final Integer millis) {
        this(hour, ClockPeriod.TWENTYFOUR_HOURS, min, sec, millis);
    }

    public IsDateWithTime(final Integer hour, final ClockPeriod clockPeriod,
                          final Integer min, final Integer sec, final Integer millis) {
        checkArgument(hour != null || min != null || sec != null || millis != null,
                "The matcher need one between hour, min, sec and millis to be non null");
        checkArgument(hour == null ||
                        (hour >= 0 && hour <= 23 && clockPeriod == ClockPeriod.TWENTYFOUR_HOURS) ||
                        (hour >= 0 && hour <= 12),
                "The value hour must be null or a number between 0 and 23 in a 24hours clock period or between 0 and 11 in a 12hours clock period");
        checkArgument(min == null || (min >= 0 && min <= 59), "The value min must be null or a number between 0 and 59");
        checkArgument(sec == null || (sec >= 0 && sec <= 59), "The value sec must be null or a number between 0 and 59");
        checkArgument(millis == null || (millis >= 0 && millis <= 999), "The value millis must be null or a number between 0 and 999");

        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.millis = millis;
        this.clockPeriod = clockPeriod;
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>hour</code> in a 24 hours clock period.
     */
    public static Matcher<Date> hasHour(final int hour) {
        return new IsDateWithTime(hour, null, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> and <code>ClockPeriod</code> (e.g. <em>AM</em>).
     */
    public static Matcher<Date> hasHour(final int hour, final ClockPeriod clockPeriod) {
        return new IsDateWithTime(hour, clockPeriod, null, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>minute</code>.
     */
    public static Matcher<Date> hasMinute(final Integer minute) {
        return new IsDateWithTime(null, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>sec</code>.
     */
    public static Matcher<Date> hasSecond(final Integer second) {
        return new IsDateWithTime(null, null, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given  <code>millis</code>.
     */
    public static Matcher<Date> hasMillisecond(final Integer millisecond) {
        return new IsDateWithTime(null, null, null, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final int minute) {
        return new IsDateWithTime(hour, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>) and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final ClockPeriod clockPeriod, final int minute) {
        return new IsDateWithTime(hour, clockPeriod, minute, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period, <code>minute</code> and
     * <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final Integer hour,
                                                 final Integer minute, final Integer second, final Integer millisecond) {
        return new IsDateWithTime(hour, minute, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code> and
     * <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final Integer hour, final ClockPeriod clockPeriod,
                                                 final Integer minute, final Integer second, final Integer millisecond) {
        return new IsDateWithTime(hour, clockPeriod, minute, second, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period, <code>minute</code>,
     * <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final Integer hour,
                                                       final Integer minute, final Integer second, final Integer millisecond) {
        return new IsDateWithTime(hour, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code>,
     * <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final Integer hour, final ClockPeriod clockPeriod,
                                                       final Integer minute, final Integer second, final Integer millisecond) {
        return new IsDateWithTime(hour, clockPeriod, minute, second, millisecond);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        boolean matches = true;
        if (hour != null) {
            switch (clockPeriod) {
                case AM:
                    matches = hour == hour12(date) && isAM(date);
                    break;
                case PM:
                    matches = hour == hour12(date) && isPM(date);
                    break;
                default:
                    matches = hour == hour24(date);
            }
        }
        if (matches && min != null) {
            matches = min == minute(date);
        }
        if (matches && sec != null) {
            matches = sec == second(date);
        }
        if (matches && millis != null) {
            matches = millis == millisecond(date);
        }

        return matches;
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date)
                .appendText(" has not");
        expectedMatching(mismatchDescription);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a date with");
        expectedMatching(description);
    }

    private void expectedMatching(Description description) {
        boolean first = false;
        if (hour != null) {
            description.appendText(" hour ");
            switch (clockPeriod) {
                case AM:
                    description.appendValue(String.format("%d AM", hour));
                    break;
                case PM:
                    description.appendValue(String.format("%d PM", hour));
                    break;
                default:
                    description.appendValue(hour);
                    break;
            }
            first = false;
        }
        if (min != null) {
            if (!first)
                description.appendText(",");
            description.appendText(" minute ").appendValue(min);
            first = false;
        }
        if (sec != null) {
            if (!first)
                description.appendText(",");
            description.appendText(" second ").appendValue(sec);
            first = false;
        }
        if (millis != null) {
            if (!first)
                description.appendText(",");
            description.appendText(" millisecond ").appendValue(millis);
        }
    }

    public enum ClockPeriod {
        AM, PM, TWENTYFOUR_HOURS;
    }
}
