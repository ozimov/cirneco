package it.ozimov.cirneco.hamcrest.java7.date;

import java.util.Date;

import org.hamcrest.Matcher;

import org.joda.time.DateTime;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

public class IsDateInDayTest extends BaseDateMatcherTest {

    public Date sunday;
    public Date monday;
    public Date tuesday;
    public Date wednesday;
    public Date thursday;
    public Date friday;
    public Date saturday;

    public Matcher<Date> sundayMatcher;
    public Matcher<Date> mondayMatcher;
    public Matcher<Date> tuesdayMatcher;
    public Matcher<Date> wednesdayMatcher;
    public Matcher<Date> thursdayMatcher;
    public Matcher<Date> fridayMatcher;
    public Matcher<Date> saturdayMatcher;

    @Before
    public void setUp() {

        // Arrange
        sunday = new DateTime(2015, 12, 20, 0, 0, TIME_ZONE).toDate();
        monday = new DateTime(2015, 12, 21, 0, 0, TIME_ZONE).toDate();
        tuesday = new DateTime(2015, 12, 22, 0, 0, TIME_ZONE).toDate();
        wednesday = new DateTime(2015, 12, 23, 0, 0, TIME_ZONE).toDate();
        thursday = new DateTime(2015, 12, 24, 0, 0, TIME_ZONE).toDate();
        friday = new DateTime(2015, 12, 25, 0, 0, TIME_ZONE).toDate();
        saturday = new DateTime(2015, 12, 26, 0, 0, TIME_ZONE).toDate();

        sundayMatcher = IsDateInDay.sunday();
        mondayMatcher = IsDateInDay.monday();
        tuesdayMatcher = IsDateInDay.tuesday();
        wednesdayMatcher = IsDateInDay.wednesday();
        thursdayMatcher = IsDateInDay.thursday();
        fridayMatcher = IsDateInDay.friday();
        saturdayMatcher = IsDateInDay.saturday();
    }

    @Test
    public void testSunday() throws Exception {

        // Act
        final boolean matchesRightDay = sundayMatcher.matches(sunday);
        final boolean matchesWrongDay = sundayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testMonday() throws Exception {

        // Act
        final boolean matchesRightDay = mondayMatcher.matches(monday);
        final boolean matchesWrongDay = mondayMatcher.matches(sunday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testTuesday() throws Exception {

        // Act
        final boolean matchesRightDay = tuesdayMatcher.matches(tuesday);
        final boolean matchesWrongDay = tuesdayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testWednesday() throws Exception {

        // Act
        final boolean matchesRightDay = wednesdayMatcher.matches(wednesday);
        final boolean matchesWrongDay = wednesdayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testThursday() throws Exception {

        // Act
        final boolean matchesRightDay = thursdayMatcher.matches(thursday);
        final boolean matchesWrongDay = thursdayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testFriday() throws Exception {

        // Act
        final boolean matchesRightDay = fridayMatcher.matches(friday);
        final boolean matchesWrongDay = fridayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testSaturday() throws Exception {

        // Act
        final boolean matchesRightDay = saturdayMatcher.matches(saturday);
        final boolean matchesWrongDay = saturdayMatcher.matches(monday);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightDay);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongDay);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <SUNDAY>", monday), sundayMatcher,
            monday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <MONDAY>", sunday), mondayMatcher,
            sunday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <TUESDAY>", monday),
            tuesdayMatcher, monday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <WEDNESDAY>", monday),
            wednesdayMatcher, monday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <THURSDAY>", monday),
            thursdayMatcher, monday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <FRIDAY>", monday), fridayMatcher,
            monday);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not day <SATURDAY>", monday),
            saturdayMatcher, monday);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("a date with day <SUNDAY>", sundayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <MONDAY>", mondayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <TUESDAY>", tuesdayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <WEDNESDAY>", wednesdayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <THURSDAY>", thursdayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <FRIDAY>", fridayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with day <SATURDAY>", saturdayMatcher);
    }

}
