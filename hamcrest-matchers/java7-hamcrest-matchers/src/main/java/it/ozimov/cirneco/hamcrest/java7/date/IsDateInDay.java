package it.ozimov.cirneco.hamcrest.java7.date;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkArgument;

import static it.ozimov.cirneco.hamcrest.java7.date.utils.CalendarUtils.weekDay;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;


/**
 * Is {@linkplain Date} in a given week day?
 *
 * @since version 0.1 for JDK7
 */
public class IsDateInDay extends TypeSafeMatcher<Date> {

    private enum DayOfWeek {
        SUNDAY(Calendar.SUNDAY), MONDAY(Calendar.MONDAY),
        TUESDAY(Calendar.TUESDAY), WEDNESDAY(Calendar.WEDNESDAY),
        THURSDAY(Calendar.THURSDAY), FRIDAY(Calendar.FRIDAY),
        SATURDAY(Calendar.SATURDAY);

        final int id;

        DayOfWeek(final int id) {
            this.id = id;
        }

        static Optional<DayOfWeek> fromId(final int weekDay) {

            for (DayOfWeek dayOfWeek : values()) {

                if (dayOfWeek.id == weekDay) {
                    return Optional.of(dayOfWeek);
                }
            }

            return Optional.absent();
        }

    }

    private final DayOfWeek dayOfWeek;

    /**
     *
     */
    public IsDateInDay(final int weekDay) {
        final Optional<DayOfWeek> dayOfWeekOptional = DayOfWeek.fromId(weekDay);
        checkArgument(dayOfWeekOptional.isPresent(),
            String.format(
                "The id %d is not a valid value (admitted values are [1,2,...,7] for Sunday, Monday,..., Saturday, respectively)",
                weekDay));
        dayOfWeek = dayOfWeekOptional.get();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Sunday</em>.
     */
    public static Matcher<Date> sunday() {
        return new IsDateInDay(1);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Monday</em>.
     */
    public static Matcher<Date> monday() {
        return new IsDateInDay(2);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Tuesday</em>.
     */
    public static Matcher<Date> tuesday() {
        return new IsDateInDay(3);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Wednesday</em>.
     */
    public static Matcher<Date> wednesday() {
        return new IsDateInDay(4);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Thursday</em>.
     */
    public static Matcher<Date> thursday() {
        return new IsDateInDay(5);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Friday</em>.
     */
    public static Matcher<Date> friday() {
        return new IsDateInDay(6);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Saturday</em>.
     */
    public static Matcher<Date> saturday() {
        return new IsDateInDay(7);
    }

    @Override protected boolean matchesSafely(final Date date) {
        final int dayOfWeekFromDay = weekDay(date);

        switch (dayOfWeek) {

        case SUNDAY:
            return dayOfWeekFromDay == Calendar.SUNDAY;

        case MONDAY:
            return dayOfWeekFromDay == Calendar.MONDAY;

        case TUESDAY:
            return dayOfWeekFromDay == Calendar.TUESDAY;

        case WEDNESDAY:
            return dayOfWeekFromDay == Calendar.WEDNESDAY;

        case THURSDAY:
            return dayOfWeekFromDay == Calendar.THURSDAY;

        case FRIDAY:
            return dayOfWeekFromDay == Calendar.FRIDAY;

        case SATURDAY:
            return dayOfWeekFromDay == Calendar.SATURDAY;

        default:
            return false;
        }

    }

    @Override protected void describeMismatchSafely(final Date date,
        final Description mismatchDescription) {
        mismatchDescription.appendValue(date).appendText(" has not day ")
            .appendValue(dayOfWeek);
    }

    @Override public void describeTo(final Description description) {
        description.appendText("a date with day ").appendValue(dayOfWeek);
    }

}
