package it.ozimov.cirneco.hamcrest.java7.date;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static it.ozimov.cirneco.hamcrest.java7.date.DateTestUtils.date;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsDateInLeapYearTest extends BaseMatcherTest {

    public Matcher<Date> isLeapYearMatcher;
    public Date leapYear;
    public Date nonLeapYear;

    @Before
    public void setUp() throws ParseException {

        // Arrange
        isLeapYearMatcher = IsDateInLeapYear.leapYear();

        leapYear = date(2000, 2, 29);
        nonLeapYear = date(1982, 2, 28);
    }

    @Test
    public void testGivenALeapYearWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isLeapYear = isLeapYearMatcher.matches(leapYear);

        // Assert
        assertThat(String.format("%tD is leap year", leapYear), isLeapYear, is(true));
    }

    @Test
    public void testGivenANonLeapYearWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isLeapYear = isLeapYearMatcher.matches(nonLeapYear);

        // Assert
        assertThat(String.format("%tD is not leap year", leapYear), isLeapYear, is(false));
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> is not a leap year", nonLeapYear),
                isLeapYearMatcher, nonLeapYear);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("a date in a leap year", isLeapYearMatcher);
    }

}
