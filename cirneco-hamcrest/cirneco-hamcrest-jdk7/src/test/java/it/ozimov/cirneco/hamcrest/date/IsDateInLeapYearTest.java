package it.ozimov.cirneco.hamcrest.date;

import org.hamcrest.Matcher;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsDateInLeapYearTest extends BaseDateMatcherTest {

    public Matcher<Date> isLeapYearMatcher;
    public Date leapYear;
    public Date nonLeapYear;

    @Before
    public void setUp() {
        //Arrange
        isLeapYearMatcher = IsDateInLeapYear.leapYear();

        leapYear = new DateTime(2000, 2, 29, 0, 0, TIME_ZONE).toDate();
        nonLeapYear = new DateTime(1982, 2, 28, 0, 0, TIME_ZONE).toDate();
    }

    @Test
    public void testGivenALeapYearWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        //Act
        final boolean isLeapYear = isLeapYearMatcher.matches(leapYear);

        //Assert
        assertThat(String.format("%tD is leap year", leapYear), isLeapYear, is(true));
    }

    @Test
    public void testGivenANonLeapYearWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        //Act
        final boolean isLeapYear = isLeapYearMatcher.matches(nonLeapYear);

        //Assert
        assertThat(String.format("%tD is not leap year", leapYear), isLeapYear, is(false));
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(String.format("<%s> is not a leap year", nonLeapYear),
                isLeapYearMatcher, nonLeapYear);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a date in a leap year", isLeapYearMatcher);
    }

}