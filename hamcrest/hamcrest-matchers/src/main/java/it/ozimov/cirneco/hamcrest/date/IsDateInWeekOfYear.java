package it.ozimov.cirneco.hamcrest.date;

import it.ozimov.cirneco.hamcrest.date.utils.CalendarUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Is {@linkplain Date} in a given weekOfYear of the year?
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>The matcher is compliant to the <em>ISO 8601</em> standard. Hence, a day at the end of December in year <em>X</em>
 * may be part of the subsequent year <em>X+1</em> (and similarly for the first days of January).
 *
 * @since version 0.2 for JDK7
 */
public class IsDateInWeekOfYear extends TypeSafeMatcher<Date> {

    private final Integer weekOfYear;

    public IsDateInWeekOfYear(final int weekOfYear) {
        checkArgument((weekOfYear >= 1) && (weekOfYear <= 53), "The value weekOfYear must be between 1 and 53");

        this.weekOfYear = weekOfYear;
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} is in the given weekOfYear of the year.
     */
    public static Matcher<Date> inWeekOfYear(final int week) {
        return new IsDateInWeekOfYear(week);
    }

    @Override
    protected boolean matchesSafely(final Date date) {
        return CalendarUtils.weekOfYear(date) == weekOfYear;
    }

    @Override
    protected void describeMismatchSafely(final Date date, final Description mismatchDescription) {
        mismatchDescription.appendValue(date).appendText(" is not in week of the year ").appendValue(weekOfYear);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a date in week of the year ").appendValue(weekOfYear);
    }

}
