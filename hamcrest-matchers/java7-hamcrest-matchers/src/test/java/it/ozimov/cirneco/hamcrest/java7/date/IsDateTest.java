package it.ozimov.cirneco.hamcrest.java7.date;

import static java.lang.String.format;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import static org.junit.Assert.fail;

import static org.junit.Assume.assumeThat;

import static it.ozimov.cirneco.hamcrest.java7.date.DateTestUtils.date;

import java.text.ParseException;

import java.util.Date;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsDateTest extends BaseMatcherTest {

    public Integer dateYear;
    public Integer dateMonth;
    public Integer dateDay;
    public Integer dateWrongYear;
    public Integer dateWrongMonth;
    public Integer dateWrongDay;
    public Matcher<Date> isDateMatcher;
    public Date date;

    @Before
    public void setUp() throws ParseException {
        dateYear = 2015;
        dateMonth = 12;
        dateDay = 25;

        dateWrongYear = 2000;
        dateWrongMonth = 1;
        dateWrongDay = 1;

        assumeThat(dateWrongYear, not(is(dateYear)));
        assumeThat(dateWrongMonth, not(is(dateMonth)));
        assumeThat(dateWrongDay, not(is(dateDay)));

        date = DateTestUtils.date(dateYear, dateMonth, dateDay);
    }

    @Test
    public void testGivenAMonthGreaterThan12WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 13, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAMonthSmallerThan1WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 0, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayBetween1And31WhenCreateInstanceThenNothingIsThrown() throws Exception {

        // Act
        for (int day = 1; day <= 31; day++) {
            createSafely(null, null, day);
        }
    }

    @Test
    public void testGivenADayGreaterThan31WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, null, 32);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADaySmallerThan1WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, null, 0);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayEqualTo31AndAllowedMonthWhenCreateInstanceThenNothingIsThrown() throws Exception {

        // Act
        for (int month : ImmutableList.of(1, 3, 5, 7, 8, 10, 12)) {

            for (int day = 1; day <= 31; day++) {
                createSafely(null, month, day);
            }
        }
    }

    @Test
    public void testGivenADayGreaterThan30AndMonthAprilWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 4, 31);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterThan30AndMonthJuneWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 6, 31);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterThan30AndMonthSeptemberWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 9, 31);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterThan30AndMonthNovemberWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 11, 31);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterThan29AndMonthFebruaryWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(null, 2, 30);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterThan28AndMonthFebruaryInNonLeapYearWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDate(2001, 2, 29);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenADayGreaterEqualsTo29AndMonthFebruaryInLeapYearWhenCreateInstanceThenNothingIsThrown()
        throws Exception {

        // Act
        isDateMatcher = new IsDate(2000, 2, 29);

        // Assert
        assertThat(isDateMatcher, not(is(nullValue())));
    }

    @Test
    public void testGivenValidYearWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYear(dateYear);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonValidYearWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYear(dateWrongYear);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenValidMonthWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasMonth(dateMonth);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonValidMonthWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasMonth(dateWrongMonth);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenValidDayWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasDay(dateDay);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonValidDayWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasDay(dateWrongDay);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenValidYearAndMonthWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYearAndMonth(dateYear, dateMonth);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonValidYearAndOrMonthWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        testHasYearAndMonthDoesNotMatchOnDate(dateWrongYear, dateMonth);
        testHasYearAndMonthDoesNotMatchOnDate(dateYear, dateWrongMonth);
        testHasYearAndMonthDoesNotMatchOnDate(dateWrongYear, dateWrongMonth);
    }

    @Test
    public void testGivenValidYearMonthAndDayWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYearMonthAndDay(dateYear, dateMonth, dateDay);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonValidYearAndOrMonthAndOrDayWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        testHasYearMonthAndDayDoesNotMatchOnDate(dateWrongYear, dateMonth, dateDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateYear, dateWrongMonth, dateDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateYear, dateMonth, dateWrongDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateWrongYear, dateWrongMonth, dateDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateWrongYear, dateMonth, dateWrongDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateYear, dateWrongMonth, dateWrongDay);
        testHasYearMonthAndDayDoesNotMatchOnDate(dateWrongYear, dateWrongMonth, dateWrongDay);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not year <%d>", date, dateWrongYear),
            IsDate.hasYear(dateWrongYear), date);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not id <%d>", date, dateWrongMonth),
            IsDate.hasMonth(dateWrongMonth), date);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not day <%d>", date, dateWrongDay),
            IsDate.hasDay(dateWrongDay), date);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not year <%d>, id <%d>", date, dateWrongYear,
                dateWrongMonth), IsDate.hasYearAndMonth(dateWrongYear, dateWrongMonth), date);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not year <%d>, id <%d>, day <%d>", date,
                dateWrongYear, dateWrongMonth, dateWrongDay),
            IsDate.hasYearMonthAndDay(dateWrongYear, dateWrongMonth, dateWrongDay), date);

        // mismatch description covered by the constructor
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not year <%d>, day <%d>", date, dateWrongYear,
                dateWrongDay), new IsDate(dateWrongYear, null, dateWrongDay), date);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not id <%d>, day <%d>", date, dateWrongMonth,
                dateWrongDay), new IsDate(null, dateWrongMonth, dateWrongDay), date);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo(format("a date with year <%d>", dateYear), IsDate.hasYear(dateYear));
        BaseMatcherTest.assertIsDescribedTo(format("a date with id <%d>", dateMonth), IsDate.hasMonth(dateMonth));
        BaseMatcherTest.assertIsDescribedTo(format("a date with day <%d>", dateDay), IsDate.hasDay(dateDay));

        BaseMatcherTest.assertIsDescribedTo(format("a date with year <%d>, id <%d>", dateYear, dateMonth),
            IsDate.hasYearAndMonth(dateYear, dateMonth));
        BaseMatcherTest.assertIsDescribedTo(format("a date with year <%d>, id <%d>, day <%d>", dateYear, dateMonth,
                dateDay), IsDate.hasYearMonthAndDay(dateYear, dateMonth, dateDay));

        // description covered by the constructor
        BaseMatcherTest.assertIsDescribedTo(format("a date with year <%d>, day <%d>", dateYear, dateDay),
            new IsDate(dateYear, null, dateDay));
        BaseMatcherTest.assertIsDescribedTo(format("a date with id <%d>, day <%d>", dateMonth, dateDay),
            new IsDate(null, dateMonth, dateDay));
    }

    private void createSafely(final Integer year, final Integer month, final Integer day) {

        // Act
        isDateMatcher = new IsDate(year, month, day);

        assertThat(String.format("Created matcher for year %d, id %d and day %d", year, month, day),
            not(is(nullValue())));
    }

    private void testHasYearAndMonthDoesNotMatchOnDate(final Integer year, final Integer month) throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYearAndMonth(year, month);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    private void testHasYearMonthAndDayDoesNotMatchOnDate(final Integer year, final Integer month, final Integer day)
        throws Exception {

        // Arrange
        isDateMatcher = IsDate.hasYearMonthAndDay(year, month, day);

        // Act
        final boolean matches = isDateMatcher.matches(date);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

}
