package org.cirneco.assertions.hamcrest.date;

import com.google.common.base.Optional;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.month;

/**
 * Is {@linkplain Date} in a given month?
 */
public class IsDateInMonth extends TypeSafeMatcher<Date> {

    private final Month month;

    /**
     *
     */
    public IsDateInMonth(final int month) {
        final Optional<Month> monthOptional = Month.fromId(month);
        checkArgument(monthOptional.isPresent(),
                String.format("The month %d is not a valid value (admitted values are [1,2,...,12])",
                        month));
        this.month = monthOptional.get();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>January</em>.
     */
    public static Matcher<Date> january() {
        return new IsDateInMonth(1);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>February</em>.
     */
    public static Matcher<Date> february() {
        return new IsDateInMonth(2);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>March</em>.
     */
    public static Matcher<Date> march() {
        return new IsDateInMonth(3);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>April</em>.
     */
    public static Matcher<Date> april() {
        return new IsDateInMonth(4);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>May</em>.
     */
    public static Matcher<Date> may() {
        return new IsDateInMonth(5);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>June</em>.
     */
    public static Matcher<Date> june() {
        return new IsDateInMonth(6);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>July</em>.
     */
    public static Matcher<Date> july() {
        return new IsDateInMonth(7);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>August</em>.
     */
    public static Matcher<Date> august() {
        return new IsDateInMonth(8);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>September</em>.
     */
    public static Matcher<Date> september() {
        return new IsDateInMonth(9);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>October</em>.
     */
    public static Matcher<Date> october() {
        return new IsDateInMonth(10);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>November</em>.
     */
    public static Matcher<Date> november() {
        return new IsDateInMonth(11);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the month <em>December</em>.
     */
    public static Matcher<Date> december() {
        return new IsDateInMonth(12);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        final int monthFromDay = month(date);

        switch (month) {
            case JANUARY:
                return monthFromDay == Calendar.JANUARY;
            case FEBRUARY:
                return monthFromDay == Calendar.FEBRUARY;
            case MARCH:
                return monthFromDay == Calendar.MARCH;
            case APRIL:
                return monthFromDay == Calendar.APRIL;
            case MAY:
                return monthFromDay == Calendar.MAY;
            case JUNE:
                return monthFromDay == Calendar.JUNE;
            case JULY:
                return monthFromDay == Calendar.JULY;
            case AUGUST:
                return monthFromDay == Calendar.AUGUST;
            case SEPTEMBER:
                return monthFromDay == Calendar.SEPTEMBER;
            case OCTOBER:
                return monthFromDay == Calendar.OCTOBER;
            case NOVEMBER:
                return monthFromDay == Calendar.NOVEMBER;
            case DECEMBER:
                return monthFromDay == Calendar.DECEMBER;
            default:
                return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date)
                .appendText(" has not mont ")
                .appendValue(month);
    }

    @Override
    public void describeTo(final Description description) {
        description
                .appendText("a date with month ")
                .appendValue(month);
    }

    private enum Month {
        JANUARY(Calendar.JANUARY),
        FEBRUARY(Calendar.FEBRUARY),
        MARCH(Calendar.MARCH),
        APRIL(Calendar.APRIL),
        MAY(Calendar.MAY),
        JUNE(Calendar.JUNE),
        JULY(Calendar.JULY),
        AUGUST(Calendar.AUGUST),
        SEPTEMBER(Calendar.SEPTEMBER),
        OCTOBER(Calendar.OCTOBER),
        NOVEMBER(Calendar.NOVEMBER),
        DECEMBER(Calendar.DECEMBER);

        int month;

        Month(final int month) {
            this.month = month;
        }

        static Optional<Month> fromId(final int weekDay) {
            for (Month month : values()) {
                if (month.month == weekDay) {
                    return Optional.of(month);
                }
            }
            return Optional.absent();
        }

    }

}
