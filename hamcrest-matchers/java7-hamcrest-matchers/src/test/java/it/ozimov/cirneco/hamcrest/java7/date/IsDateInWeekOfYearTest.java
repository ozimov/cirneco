package it.ozimov.cirneco.hamcrest.java7.date;

import static java.lang.String.format;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import static org.junit.Assert.fail;

import java.util.Date;

import org.hamcrest.Matcher;

import org.joda.time.DateTime;

import org.junit.Before;
import org.junit.Test;

public class IsDateInWeekOfYearTest extends BaseDateMatcherTest {

    public Matcher<Date> isWeek1Matcher;
    public Matcher<Date> isWeek52Matcher;
    public Matcher<Date> isWeek53Matcher;
    public Date januaryDateInWeek1;
    public Date januaryDateInWeek53;
    public Date decemberDateInWeek1;
    public Date decemberDateInWeek52;
    public Date decemberDateInWeek53;

    @Before
    public void setUp() {

        // Arrange
        januaryDateInWeek1 = new DateTime(2019, 1, 1, 0, 0, 0, TIME_ZONE).toDate();
        januaryDateInWeek53 = new DateTime(2016, 1, 1, 0, 0, 0, TIME_ZONE).toDate();
        decemberDateInWeek1 = new DateTime(2019, 12, 31, 0, 0, 0, TIME_ZONE).toDate();
        decemberDateInWeek52 = new DateTime(2018, 12, 30, 0, 0, 0, TIME_ZONE).toDate();
        decemberDateInWeek53 = new DateTime(2015, 12, 31, 0, 0, 0, TIME_ZONE).toDate();

        isWeek1Matcher = IsDateInWeekOfYear.inWeekOfYear(1);
        isWeek52Matcher = IsDateInWeekOfYear.inWeekOfYear(52);
        isWeek53Matcher = IsDateInWeekOfYear.inWeekOfYear(53);
    }

    @Test
    public void testGivenAWeekNumberGreaterThan53WhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateInWeekOfYear(54);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAWeekNumberSmallerThan1WhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateInWeekOfYear(0);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAWeekNumberBetween1And53WhenCreateInstanceThenNothingIsThrown() throws Exception {

        // Act
        for (int weekOfYear = 1; weekOfYear <= 53; weekOfYear++) {
            createSafely(weekOfYear);
        }
    }

    @Test
    public void testGivenValidWeekOfYearWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean matchesJanuaryDateInWeek1 = isWeek1Matcher.matches(januaryDateInWeek1);
        final boolean matchesJanuaryDateInWeek53 = isWeek53Matcher.matches(januaryDateInWeek53);
        final boolean matchesDecemberDateInWeek1 = isWeek1Matcher.matches(decemberDateInWeek1);
        final boolean matchesDecemberDateInWeek52 = isWeek52Matcher.matches(decemberDateInWeek52);
        final boolean matchesDecemberDateInWeek53 = isWeek53Matcher.matches(decemberDateInWeek53);

        // Assert
        assertMatches(matchesJanuaryDateInWeek1);
        assertMatches(matchesJanuaryDateInWeek53);
        assertMatches(matchesDecemberDateInWeek1);
        assertMatches(matchesDecemberDateInWeek52);
        assertMatches(matchesDecemberDateInWeek53);
    }

    @Test
    public void testGivenNonValidWeekOfYearWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean matchesJanuaryDateInWeek1 = isWeek52Matcher.matches(januaryDateInWeek1);
        final boolean matchesJanuaryDateInWeek53 = isWeek1Matcher.matches(januaryDateInWeek53);
        final boolean matchesDecemberDateInWeek1 = isWeek52Matcher.matches(decemberDateInWeek1);
        final boolean matchesDecemberDateInWeek52 = isWeek53Matcher.matches(decemberDateInWeek52);
        final boolean matchesDecemberDateInWeek53 = isWeek1Matcher.matches(decemberDateInWeek53);

        // Assert
        assertDoesNotMatch(matchesJanuaryDateInWeek1);
        assertDoesNotMatch(matchesJanuaryDateInWeek53);
        assertDoesNotMatch(matchesDecemberDateInWeek1);
        assertDoesNotMatch(matchesDecemberDateInWeek52);
        assertDoesNotMatch(matchesDecemberDateInWeek53);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", januaryDateInWeek53, 1),
            isWeek1Matcher, januaryDateInWeek53);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek52, 1),
            isWeek1Matcher, decemberDateInWeek52);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek53, 1),
            isWeek1Matcher, decemberDateInWeek53);

        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", januaryDateInWeek1, 52),
            isWeek52Matcher, januaryDateInWeek1);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek1, 52),
            isWeek52Matcher, decemberDateInWeek1);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek53, 52),
            isWeek52Matcher, decemberDateInWeek53);

        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", januaryDateInWeek1, 53),
            isWeek53Matcher, januaryDateInWeek1);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek1, 53),
            isWeek53Matcher, decemberDateInWeek1);
        assertHasMismatchDescription(format("<%s> is not in week of the year <%d>", decemberDateInWeek52, 53),
            isWeek53Matcher, decemberDateInWeek52);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(format("a date in week of the year <%d>", 1), isWeek1Matcher);
        assertIsDescribedTo(format("a date in week of the year <%d>", 52), isWeek52Matcher);
        assertIsDescribedTo(format("a date in week of the year <%d>", 53), isWeek53Matcher);
    }

    private void createSafely(final Integer weekOfYear) {

        // Act
        final Matcher<Date> isDateInWeekOfYearMatcher = new IsDateInWeekOfYear(weekOfYear);

        assertThat(String.format("Created matcher for week of the year %s", isDateInWeekOfYearMatcher),
            not(is(nullValue())));
    }

}
