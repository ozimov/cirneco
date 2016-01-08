package it.ozimov.cirneco.hamcrest.java7.date;

import java.util.Date;

import org.hamcrest.Matcher;

import org.joda.time.DateTime;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

public class IsDateInMonthTest extends BaseDateMatcherTest {

    public Date january;
    public Date february;
    public Date march;
    public Date april;
    public Date may;
    public Date june;
    public Date july;
    public Date august;
    public Date september;
    public Date october;
    public Date november;
    public Date december;

    public Matcher<Date> januaryMatcher;
    public Matcher<Date> februaryMatcher;
    public Matcher<Date> marchMatcher;
    public Matcher<Date> aprilMatcher;
    public Matcher<Date> mayMatcher;
    public Matcher<Date> juneMatcher;
    public Matcher<Date> julyMatcher;
    public Matcher<Date> augustMatcher;
    public Matcher<Date> septemberMatcher;
    public Matcher<Date> octoberMatcher;
    public Matcher<Date> novemberMatcher;
    public Matcher<Date> decemberMatcher;

    @Before
    public void setUp() {

        // Arrange
        january = new DateTime(2015, 1, 1, 0, 0, TIME_ZONE).toDate();
        february = new DateTime(2015, 2, 1, 0, 0, TIME_ZONE).toDate();
        march = new DateTime(2015, 3, 1, 0, 0, TIME_ZONE).toDate();
        april = new DateTime(2015, 4, 1, 0, 0, TIME_ZONE).toDate();
        may = new DateTime(2015, 5, 1, 0, 0, TIME_ZONE).toDate();
        june = new DateTime(2015, 6, 1, 0, 0, TIME_ZONE).toDate();
        july = new DateTime(2015, 7, 1, 0, 0, TIME_ZONE).toDate();
        august = new DateTime(2015, 8, 1, 0, 0, TIME_ZONE).toDate();
        september = new DateTime(2015, 9, 1, 0, 0, TIME_ZONE).toDate();
        october = new DateTime(2015, 10, 1, 0, 0, TIME_ZONE).toDate();
        november = new DateTime(2015, 11, 1, 0, 0, TIME_ZONE).toDate();
        december = new DateTime(2015, 12, 1, 0, 0, TIME_ZONE).toDate();

        januaryMatcher = IsDateInMonth.january();
        februaryMatcher = IsDateInMonth.february();
        marchMatcher = IsDateInMonth.march();
        aprilMatcher = IsDateInMonth.april();
        mayMatcher = IsDateInMonth.may();
        juneMatcher = IsDateInMonth.june();
        julyMatcher = IsDateInMonth.july();
        augustMatcher = IsDateInMonth.august();
        septemberMatcher = IsDateInMonth.september();
        octoberMatcher = IsDateInMonth.october();
        novemberMatcher = IsDateInMonth.november();
        decemberMatcher = IsDateInMonth.december();
    }

    @Test
    public void testJanuary() throws Exception {

        // Act
        final boolean matchesRightMonth = januaryMatcher.matches(january);
        final boolean matchesWrongMonth = januaryMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testFebruary() throws Exception {

        // Act
        final boolean matchesRightMonth = februaryMatcher.matches(february);
        final boolean matchesWrongMonth = februaryMatcher.matches(january);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testMarch() throws Exception {

        // Act
        final boolean matchesRightMonth = marchMatcher.matches(march);
        final boolean matchesWrongMonth = marchMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testApril() throws Exception {

        // Act
        final boolean matchesRightMonth = aprilMatcher.matches(april);
        final boolean matchesWrongMonth = aprilMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testMay() throws Exception {

        // Act
        final boolean matchesRightMonth = mayMatcher.matches(may);
        final boolean matchesWrongMonth = mayMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testJune() throws Exception {

        // Act
        final boolean matchesRightMonth = juneMatcher.matches(june);
        final boolean matchesWrongMonth = juneMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testJuly() throws Exception {

        // Act
        final boolean matchesRightMonth = julyMatcher.matches(july);
        final boolean matchesWrongMonth = julyMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testAugust() throws Exception {

        // Act
        final boolean matchesRightMonth = augustMatcher.matches(august);
        final boolean matchesWrongMonth = augustMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testSeptember() throws Exception {

        // Act
        final boolean matchesRightMonth = septemberMatcher.matches(september);
        final boolean matchesWrongMonth = septemberMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testOctober() throws Exception {

        // Act
        final boolean matchesRightMonth = octoberMatcher.matches(october);
        final boolean matchesWrongMonth = octoberMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testNovember() throws Exception {

        // Act
        final boolean matchesRightMonth = novemberMatcher.matches(november);
        final boolean matchesWrongMonth = novemberMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testDecember() throws Exception {

        // Act
        final boolean matchesRightMonth = decemberMatcher.matches(december);
        final boolean matchesWrongMonth = decemberMatcher.matches(february);

        // Assert
        BaseMatcherTest.assertMatches(matchesRightMonth);
        BaseMatcherTest.assertDoesNotMatch(matchesWrongMonth);
    }

    @Test
    public void testMatchesSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <JANUARY>", february),
            januaryMatcher, february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <FEBRUARY>", january),
            februaryMatcher, january);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <MARCH>", february), marchMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <APRIL>", february), aprilMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <MAY>", february), mayMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <JUNE>", february), juneMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <JULY>", february), julyMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <AUGUST>", february), augustMatcher,
            february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <SEPTEMBER>", february),
            septemberMatcher, february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <OCTOBER>", february),
            octoberMatcher, february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <NOVEMBER>", february),
            novemberMatcher, february);
        BaseMatcherTest.assertHasMismatchDescription(String.format("<%s> has not id <DECEMBER>", february),
            decemberMatcher, february);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("a date with id <JANUARY>", januaryMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <FEBRUARY>", februaryMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <MARCH>", marchMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <APRIL>", aprilMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <MAY>", mayMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <JUNE>", juneMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <JULY>", julyMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <AUGUST>", augustMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <SEPTEMBER>", septemberMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <OCTOBER>", octoberMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <NOVEMBER>", novemberMatcher);
        BaseMatcherTest.assertIsDescribedTo("a date with id <DECEMBER>", decemberMatcher);
    }

}
