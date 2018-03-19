package it.ozimov.cirneco.hamcrest.date;

import com.google.common.collect.ImmutableSet;
import it.ozimov.cirneco.hamcrest.date.utils.CalendarUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Is {@linkplain Date} in a given week day?
 *
 * @since version 0.1 for JDK7
 */
public class IsDate extends TypeSafeMatcher<Date> {

    private final Integer year;
    private final Integer month;
    private final Integer day;

    public IsDate(final Integer year, final Integer month, final Integer day) {
        checkArgument((year != null) || (month != null) || (day != null),
                "The matcher need one between year, id, day and millis to be non null");
        checkArgument((year == null) || (year >= 0), "The value year must be null or a number greater than 0");
        checkArgument((month == null) || ((month >= 1) && (month <= 12)),
                "The value id must be null or a number between 1 and 12");
        checkArgument((day == null) || isDayValidForMonthAndYear(year, month, day),
                "The value day must be null or a number between 1 and 31 ");

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>year</code>.
     */
    public static Matcher<Date> hasYear(final int year) {
        return new IsDate(year, null, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>id</code>.
     */
    public static Matcher<Date> hasMonth(final int month) {
        return new IsDate(null, month, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>day</code>.
     */
    public static Matcher<Date> hasDay(final int day) {
        return new IsDate(null, null, day);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>year</code> and
     * <code>id</code>.
     */
    public static Matcher<Date> hasYearAndMonth(final int year, final int month) {
        return new IsDate(year, month, null);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>year</code>, <code>
     * id</code> and <code>day</code>.
     */
    public static Matcher<Date> hasYearMonthAndDay(final int year, final int month, final int day) {
        return new IsDate(year, month, day);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        boolean matches = true;

        if (year != null) {
            matches = year == CalendarUtils.year(date);
        }

        if (matches && (month != null)) {
            matches = month == CalendarUtils.month(date);
        }

        if (matches && (day != null)) {
            matches = day == CalendarUtils.day(date);
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

        if (year != null) {
            description.appendText(" year ");
            description.appendValue(year);
            first = false;
        }

        if (month != null) {

            if (!first) {
                description.appendText(",");
            }

            description.appendText(" id ").appendValue(month);
            first = false;
        }

        if (day != null) {

            if (!first) {
                description.appendText(",");
            }

            description.appendText(" day ").appendValue(day);
        }
    }

    private boolean isDayValidForMonthAndYear(final Integer year, final Integer month, final Integer day) {

        if (month != null) {

            if (ImmutableSet.of(4, 6, 9, 11).contains(month)) {
                return (day >= 1) && (day <= 30);
            } else if (month == 2) {
                final int leapYear;

                if ((year == null) || (((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0)))) {
                    leapYear = 1;
                } else {
                    leapYear = 0;
                }

                return (day >= 1) && (day <= (28 + leapYear));
            }
        }

        return (day >= 1) && (day <= 31);
    }

}
