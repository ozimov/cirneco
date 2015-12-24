package org.cirneco.assertions.hamcrest.date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static org.cirneco.assertions.hamcrest.date.utils.CalendarUtils.isLeapYear;

/**
 * Is {@linkplain Date} in a leap year?
 *
 * @since 0.1
 */
public class IsDateInLeapYear extends TypeSafeMatcher<Date> {

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a leap year.
     */
    public static Matcher<Date> leapYear() {
        return new IsDateInLeapYear();
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        return isLeapYear(date);
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date)
                .appendText(" is not a leap year");
    }

    @Override
    public void describeTo(final Description description) {
        description
                .appendText("a date in a leap year");
    }

}
