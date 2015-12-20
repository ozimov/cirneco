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
 * Is {@linkplain Date} in a given id?
 */
public class IsDateInMonth extends TypeSafeMatcher<Date> {

    private final Month month;

    /**
     *
     */
    public IsDateInMonth(final int month) {
        //Months in {@linkplain Calendar} are zero-based
        final Optional<Month> monthOptional = Month.fromId(month);
        checkArgument(monthOptional.isPresent(),
                String.format("The id %d is not a valid value (admitted values are [1,2,...,12])",
                        month));
        this.month = monthOptional.get();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>January</em>.
     */
    public static Matcher<Date> january() {
        return new IsDateInMonth(1);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>February</em>.
     */
    public static Matcher<Date> february() {
        return new IsDateInMonth(2);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>March</em>.
     */
    public static Matcher<Date> march() {
        return new IsDateInMonth(3);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>April</em>.
     */
    public static Matcher<Date> april() {
        return new IsDateInMonth(4);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>May</em>.
     */
    public static Matcher<Date> may() {
        return new IsDateInMonth(5);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>June</em>.
     */
    public static Matcher<Date> june() {
        return new IsDateInMonth(6);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>July</em>.
     */
    public static Matcher<Date> july() {
        return new IsDateInMonth(7);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>August</em>.
     */
    public static Matcher<Date> august() {
        return new IsDateInMonth(8);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>September</em>.
     */
    public static Matcher<Date> september() {
        return new IsDateInMonth(9);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>October</em>.
     */
    public static Matcher<Date> october() {
        return new IsDateInMonth(10);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>November</em>.
     */
    public static Matcher<Date> november() {
        return new IsDateInMonth(11);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>December</em>.
     */
    public static Matcher<Date> december() {
        return new IsDateInMonth(12);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        final int monthFromDate = month(date);

        switch (month) {
            case JANUARY:
                return monthFromDate == Month.JANUARY.id;
            case FEBRUARY:
                return monthFromDate == Month.FEBRUARY.id;
            case MARCH:
                return monthFromDate == Month.MARCH.id;
            case APRIL:
                return monthFromDate == Month.APRIL.id;
            case MAY:
                return monthFromDate == Month.MAY.id;
            case JUNE:
                return monthFromDate == Month.JUNE.id;
            case JULY:
                return monthFromDate == Month.JULY.id;
            case AUGUST:
                return monthFromDate == Month.AUGUST.id;
            case SEPTEMBER:
                return monthFromDate == Month.SEPTEMBER.id;
            case OCTOBER:
                return monthFromDate == Month.OCTOBER.id;
            case NOVEMBER:
                return monthFromDate == Month.NOVEMBER.id;
            case DECEMBER:
                return monthFromDate == Month.DECEMBER.id;
            default:
                return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date)
                .appendText(" has not id ")
                .appendValue(month);
    }

    @Override
    public void describeTo(final Description description) {
        description
                .appendText("a date with id ")
                .appendValue(month);
    }

    /**
     * Months in {@linkplain Calendar} are zero-based
     */
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

        final int id;

        Month(final int id) {
            this.id = id + 1;
        }

        static Optional<Month> fromId(final int weekDay) {
            for (Month month : values()) {
                if (month.id == weekDay) {
                    return Optional.of(month);
                }
            }
            return Optional.absent();
        }

    }

}
