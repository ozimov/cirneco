package it.ozimov.cirneco.hamcrest.java7.date;

import static java.lang.String.format;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import static org.junit.Assert.fail;

import static org.junit.Assume.assumeThat;

import static it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod.AM;
import static it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod.PM;
import static it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod.TWENTYFOUR_HOURS;

import java.util.Date;

import org.hamcrest.Matcher;

import org.joda.time.DateTime;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod;

public class IsDateWithTimeTest extends BaseDateMatcherTest {
    public static final int LENGTH_12_HOUR_CLOCK = 12;
    public Integer morningDateHour;
    public Integer morningDateMinute;
    public Integer morningDateSecond;
    public Integer morningDateMillisecond;
    public Integer eveningDateHour;
    public Integer eveningDateMinute;
    public Integer eveningDateSecond;
    public Integer eveningDateMillisecond;
    public Matcher<Date> isWithTimeMatcher;
    public Date morningDate;
    public Date eveningDate;

    @Before
    public void setUp() {

        // Arrange
        morningDateHour = 11;
        morningDateMinute = 30;
        morningDateSecond = 40;
        morningDateMillisecond = 449;
        eveningDateHour = 23;
        eveningDateMinute = 40;
        eveningDateSecond = 50;
        eveningDateMillisecond = 999;

        morningDate = date(morningDateHour, morningDateMinute, morningDateSecond, morningDateMillisecond);
        eveningDate = date(eveningDateHour, eveningDateMinute, eveningDateSecond, eveningDateMillisecond);

        // We want them completely different for the sake of unit tests
        assumeThat(morningDateHour, not(is(eveningDateHour)));
        assumeThat(morningDateMinute, not(is(eveningDateMinute)));
        assumeThat(morningDateSecond, not(is(eveningDateSecond)));
        assumeThat(morningDateMillisecond, not(is(eveningDateMillisecond)));
    }

    @Test
    public void testGivenAnHourGreaterThan23WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(24, null, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAnHourSmallerThan0WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(-1, null, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAnHourGreaterThan12AMWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(13, AM, null, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAnHourGreaterThan12PMWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(13, PM, null, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAnHourGreaterThan23InA24HoursClockPeriodWhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(24, TWENTYFOUR_HOURS, null, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAnHourBetween12And24InA24HoursClockPeriodWhenCreateInstanceThenNothingIsThrown()
        throws Exception {

        // Act
        isWithTimeMatcher = new IsDateWithTime(13, TWENTYFOUR_HOURS, null, null, null);

        // Assert
        assertThat(isWithTimeMatcher, not(is(nullValue())));
    }

    @Test
    public void testGivenAMinuteGreaterThan59WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, 60, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAMinuteSmallerThan0WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, -1, null, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenASecondGreaterThan59WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, null, 60, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenASecondSmallerThan0WhenCreateInstanceThenIllegalArgumentExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, null, -1, null);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAMillisecondGreaterThan999WhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, null, null, 1000);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenAMillisecondSmallerThan0WhenCreateInstanceThenIllegalArgumentExceptionIsThrown()
        throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsDateWithTime(null, null, null, -1);

        // Assert
        fail("Exception comparison");
    }

    @Test
    public void testGivenLowerValuesForMinuteSecondAndMillisecondWithZeroValueWhenCreateInstanceThenNothingIsThrown()
        throws Exception {

        // Act
        isWithTimeMatcher = new IsDateWithTime(null, 0, 0, 0);

        // Assert
        assertThat(isWithTimeMatcher, not(is(nullValue())));
    }

    @Test
    public void testGivenUpperValuesForMinuteSecondAndMillisecondWithZeroValueWhenCreateInstanceThenNothingIsThrown()
        throws Exception {

        // Act
        isWithTimeMatcher = new IsDateWithTime(null, 59, 59, 999);

        // Assert
        assertThat(isWithTimeMatcher, not(is(nullValue())));
    }

    @Test
    public void testGivenValidHourWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(morningDateHour);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        BaseMatcherTest.assertMatches(dateMatches);
    }

    @Test
    public void testGivenValidHourAMWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(morningDateHour, AM);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        BaseMatcherTest.assertMatches(dateMatches);
    }

    @Test
    public void testGivenValidHourPMWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        BaseMatcherTest.assertMatches(dateMatches);
    }

    @Test
    public void testGivenNonValidHourWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(morningDateHour);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenNonValidHourAMWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(morningDateHour, AM);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenNonValidHourPMWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHour(morningDateHour, PM);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenValidMinuteWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasMinute(morningDateMinute);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        assertThat(format("Date %s should match", morningDate), dateMatches, is(true));
    }

    @Test
    public void testGivenNonValidMinuteWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasMinute(morningDateMinute);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenValidSecondWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasSecond(morningDateSecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        assertThat(format("Date %s should match", morningDate), dateMatches, is(true));
    }

    @Test
    public void testGivenNonValidSecondWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasSecond(morningDateSecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenValidMillisecondWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasMillisecond(morningDateMillisecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(morningDate);

        // Assert
        assertThat(format("Date %s should match", morningDate), dateMatches, is(true));
    }

    @Test
    public void testGivenNonValidMillisecondWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasMillisecond(morningDateMillisecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(eveningDate);

        // Assert
        assertThat(format("Date %s should not match", eveningDate), dateMatches, is(false));
    }

    @Test
    public void testGivenValidHourAndMinuteWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        testMatchesHourAndMinute(morningDateHour, morningDateMinute, morningDate, true);
        testMatchesHourAndMinute(morningDateHour, AM, morningDateMinute, morningDate, true);
        testMatchesHourAndMinute(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute, eveningDate, true);
    }

    @Test
    public void testGivenNonValidHourAndOrMinuteWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        testMatchesHourAndMinute(eveningDateHour, morningDateMinute, morningDate, false);
        testMatchesHourAndMinute(morningDateHour, eveningDateMinute, morningDate, false);
        testMatchesHourAndMinute(morningDateHour, morningDateMinute, eveningDate, false);
        testMatchesHourAndMinute(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute, morningDate, false);
        testMatchesHourAndMinute(morningDateHour, AM, eveningDateMinute, morningDate, false);
        testMatchesHourAndMinute(morningDateHour, AM, morningDateMinute, eveningDate, false);
        testMatchesHourAndMinute(morningDateHour, AM, eveningDateMinute, eveningDate, false);
        testMatchesHourAndMinute(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute, eveningDate, false);
        testMatchesHourAndMinute(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute, morningDate, false);
    }

    @Test
    public void testGivenValidHourMinuteAndSecondWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        testMatchesHourMinuteAndSecond(morningDateHour, morningDateMinute, morningDateSecond, morningDate, true);
        testMatchesHourMinuteAndSecond(morningDateHour, AM, morningDateMinute, morningDateSecond, morningDate, true);
        testMatchesHourMinuteAndSecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute, eveningDateSecond,
            eveningDate, true);
    }

    @Test
    public void testGivenNonValidHourAndOrMinuteAndOrSecondWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        testMatchesHourMinuteAndSecond(eveningDateHour, morningDateMinute, morningDateSecond, morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, eveningDateMinute, morningDateSecond, morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, morningDateMinute, eveningDateSecond, morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, morningDateMinute, morningDateSecond, eveningDate, false);
        testMatchesHourMinuteAndSecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute, morningDateSecond,
            morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, AM, eveningDateMinute, morningDateSecond, morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, AM, morningDateMinute, eveningDateSecond, morningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, AM, morningDateMinute, morningDateSecond, eveningDate, false);
        testMatchesHourMinuteAndSecond(morningDateHour, AM, eveningDateMinute, eveningDateSecond, eveningDate, false);
        testMatchesHourMinuteAndSecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute, eveningDateSecond,
            eveningDate, false);
        testMatchesHourMinuteAndSecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute, morningDateSecond,
            eveningDate, false);
        testMatchesHourMinuteAndSecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute, eveningDateSecond,
            morningDate, false);
    }

    @Test
    public void testGivenValidHourMinuteSecondAndMillisecondWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, morningDateMinute, morningDateSecond,
            morningDateMillisecond, morningDate, true);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, morningDateMinute, morningDateSecond,
            morningDateMillisecond, morningDate, true);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
            eveningDateSecond, eveningDateMillisecond, eveningDate, true);
    }

    @Test
    public void testGivenNonValidHourAndOrMinuteAndOrSecondAndOrMillisecondWhenMatchesIsCalledThenFalseIsReturned()
        throws Exception {
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour, morningDateMinute, morningDateSecond,
            morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, eveningDateMinute, morningDateSecond,
            morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, morningDateMinute, eveningDateSecond,
            morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, morningDateMinute, morningDateSecond,
            eveningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, morningDateMinute, morningDateSecond,
            morningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute,
            morningDateSecond, morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, eveningDateMinute, morningDateSecond,
            morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, morningDateMinute, eveningDateSecond,
            morningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, morningDateMinute, morningDateSecond,
            eveningDateMillisecond, morningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, morningDateMinute, morningDateSecond,
            morningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(morningDateHour, AM, eveningDateMinute, eveningDateSecond,
            eveningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, morningDateMinute,
            eveningDateSecond, eveningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
            morningDateSecond, eveningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
            eveningDateSecond, morningDateMillisecond, eveningDate, false);
        testMatchesHourMinuteSecondAndMillisecond(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
            eveningDateSecond, eveningDateMillisecond, morningDate, false);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d>", eveningDate, morningDateHour),
            IsDateWithTime.hasHour(morningDateHour), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d AM>", eveningDate, morningDateHour),
            IsDateWithTime.hasHour(morningDateHour, AM), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d PM>", morningDate,
                eveningDateHour - LENGTH_12_HOUR_CLOCK),
            IsDateWithTime.hasHour(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM), morningDate);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not minute <%d>", eveningDate, morningDateMinute),
            IsDateWithTime.hasMinute(morningDateMinute), eveningDate);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not second <%d>", eveningDate, morningDateSecond),
            IsDateWithTime.hasSecond(morningDateSecond), eveningDate);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not millisecond <%d>", eveningDate,
                morningDateMillisecond), IsDateWithTime.hasMillisecond(morningDateMillisecond), eveningDate);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d>, minute <%d>", eveningDate,
                morningDateHour, morningDateMinute), IsDateWithTime.hasHourAndMin(morningDateHour, morningDateMinute),
            eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d AM>, minute <%d>", eveningDate,
                morningDateHour, morningDateMinute),
            IsDateWithTime.hasHourAndMin(morningDateHour, AM, morningDateMinute), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d PM>, minute <%d>", morningDate,
                eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute),
            IsDateWithTime.hasHourAndMin(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute), morningDate);

        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d>, minute <%d>, second <%d>",
                eveningDate, morningDateHour, morningDateMinute, morningDateSecond),
            IsDateWithTime.hasHourMinAndSec(morningDateHour, morningDateMinute, morningDateSecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d AM>, minute <%d>, second <%d>",
                eveningDate, morningDateHour, morningDateMinute, morningDateSecond),
            IsDateWithTime.hasHourMinAndSec(morningDateHour, AM, morningDateMinute, morningDateSecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not hour <%d PM>, minute <%d>, second <%d>",
                morningDate, eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute, eveningDateSecond),
            IsDateWithTime.hasHourMinAndSec(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
                eveningDateSecond), morningDate);

        BaseMatcherTest.assertHasMismatchDescription(format(
                "<%s> has not hour <%d>, minute <%d>, second <%d>, millisecond <%d>", eveningDate, morningDateHour,
                morningDateMinute, morningDateSecond, morningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(morningDateHour, morningDateMinute, morningDateSecond,
                morningDateMillisecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format(
                "<%s> has not hour <%d AM>, minute <%d>, second <%d>, millisecond <%d>", eveningDate, morningDateHour,
                morningDateMinute, morningDateSecond, morningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(morningDateHour, AM, morningDateMinute, morningDateSecond,
                morningDateMillisecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format(
                "<%s> has not hour <%d PM>, minute <%d>, second <%d>, millisecond <%d>", morningDate,
                eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute, eveningDateSecond, eveningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
                eveningDateSecond, eveningDateMillisecond), morningDate);

        // description covered by the constructor
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not minute <%d>, second <%d>, millisecond <%d>",
                eveningDate, morningDateMinute, morningDateSecond, morningDateMillisecond),
            new IsDateWithTime(null, morningDateMinute, morningDateSecond, morningDateMillisecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not second <%d>, millisecond <%d>", eveningDate,
                morningDateSecond, morningDateMillisecond),
            new IsDateWithTime(null, null, morningDateSecond, morningDateMillisecond), eveningDate);
        BaseMatcherTest.assertHasMismatchDescription(format("<%s> has not minute <%d>, millisecond <%d>", eveningDate,
                morningDateMinute, morningDateMillisecond),
            new IsDateWithTime(null, morningDateMinute, null, morningDateMillisecond), eveningDate);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d>", morningDateHour),
            IsDateWithTime.hasHour(morningDateHour));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d AM>", morningDateHour),
            IsDateWithTime.hasHour(morningDateHour, AM));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d PM>", eveningDateHour - LENGTH_12_HOUR_CLOCK),
            IsDateWithTime.hasHour(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM));

        BaseMatcherTest.assertIsDescribedTo(format("a date with minute <%d>", morningDateMinute),
            IsDateWithTime.hasMinute(morningDateMinute));

        BaseMatcherTest.assertIsDescribedTo(format("a date with second <%d>", morningDateSecond),
            IsDateWithTime.hasSecond(morningDateSecond));

        BaseMatcherTest.assertIsDescribedTo(format("a date with millisecond <%d>", morningDateMillisecond),
            IsDateWithTime.hasMillisecond(morningDateMillisecond));

        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d>, minute <%d>", morningDateHour,
                morningDateMinute), IsDateWithTime.hasHourAndMin(morningDateHour, morningDateMinute));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d AM>, minute <%d>", morningDateHour,
                morningDateMinute), IsDateWithTime.hasHourAndMin(morningDateHour, AM, morningDateMinute));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d PM>, minute <%d>",
                eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute),
            IsDateWithTime.hasHourAndMin(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute));

        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d>, minute <%d>, second <%d>", morningDateHour,
                morningDateMinute, morningDateSecond),
            IsDateWithTime.hasHourMinAndSec(morningDateHour, morningDateMinute, morningDateSecond));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d AM>, minute <%d>, second <%d>",
                morningDateHour, morningDateMinute, morningDateSecond),
            IsDateWithTime.hasHourMinAndSec(morningDateHour, AM, morningDateMinute, morningDateSecond));
        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d PM>, minute <%d>, second <%d>",
                eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute, eveningDateSecond),
            IsDateWithTime.hasHourMinAndSec(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
                eveningDateSecond));

        BaseMatcherTest.assertIsDescribedTo(format("a date with hour <%d>, minute <%d>, second <%d>, millisecond <%d>",
                morningDateHour, morningDateMinute, morningDateSecond, morningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(morningDateHour, morningDateMinute, morningDateSecond,
                morningDateMillisecond));
        BaseMatcherTest.assertIsDescribedTo(format(
                "a date with hour <%d AM>, minute <%d>, second <%d>, millisecond <%d>", morningDateHour,
                morningDateMinute, morningDateSecond, morningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(morningDateHour, AM, morningDateMinute, morningDateSecond,
                morningDateMillisecond));
        BaseMatcherTest.assertIsDescribedTo(format(
                "a date with hour <%d PM>, minute <%d>, second <%d>, millisecond <%d>",
                eveningDateHour - LENGTH_12_HOUR_CLOCK, eveningDateMinute, eveningDateSecond, eveningDateMillisecond),
            IsDateWithTime.hasHourMinSecAndMillis(eveningDateHour - LENGTH_12_HOUR_CLOCK, PM, eveningDateMinute,
                eveningDateSecond, eveningDateMillisecond));

        // description covered by the constructor
        BaseMatcherTest.assertIsDescribedTo(format("a date with minute <%d>, second <%d>, millisecond <%d>",
                morningDateMinute, morningDateSecond, morningDateMillisecond),
            new IsDateWithTime(null, morningDateMinute, morningDateSecond, morningDateMillisecond));
        BaseMatcherTest.assertIsDescribedTo(format("a date with second <%d>, millisecond <%d>", morningDateSecond,
                morningDateMillisecond), new IsDateWithTime(null, null, morningDateSecond, morningDateMillisecond));
        BaseMatcherTest.assertIsDescribedTo(format("a date with minute <%d>, millisecond <%d>", morningDateMinute,
                morningDateMillisecond), new IsDateWithTime(null, morningDateMinute, null, morningDateMillisecond));
    }

    private void testMatchesHourAndMinute(final Integer dateHour, final Integer dateMinute, final Date date,
            final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourAndMin(dateHour, dateMinute);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private void testMatchesHourAndMinute(final Integer dateHour, final ClockPeriod clockPeriod,
            final Integer dateMinute, final Date date, final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourAndMin(dateHour, clockPeriod, dateMinute);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private void testMatchesHourMinuteAndSecond(final Integer dateHour, final Integer dateMinute,
            final Integer dateSecond, final Date date, final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourMinAndSec(dateHour, dateMinute, dateSecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private void testMatchesHourMinuteAndSecond(final Integer dateHour, final ClockPeriod clockPeriod,
            final Integer dateMinute, final Integer dateSecond, final Date date, final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourMinAndSec(dateHour, clockPeriod, dateMinute, dateSecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private void testMatchesHourMinuteSecondAndMillisecond(final Integer dateHour, final Integer dateMinute,
            final Integer dateSecond, final Integer dateMillisecond, final Date date, final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourMinSecAndMillis(dateHour, dateMinute, dateSecond, dateMillisecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private void testMatchesHourMinuteSecondAndMillisecond(final Integer dateHour, final ClockPeriod clockPeriod,
            final Integer dateMinute, final Integer dateSecond, final Integer dateMillisecond, final Date date,
            final boolean expectedMatching) {

        // Arrange
        isWithTimeMatcher = IsDateWithTime.hasHourMinSecAndMillis(dateHour, clockPeriod, dateMinute, dateSecond,
                dateMillisecond);

        // Act
        final boolean dateMatches = isWithTimeMatcher.matches(date);

        // Assert
        assertThat(dateMatches, is(expectedMatching));
    }

    private Date date(final Integer dateHour, final Integer dateMinute, final Integer dateSecond,
            final Integer dateMillisecond) {
        return new DateTime(2000, 2, 29, dateHour, dateMinute, dateSecond, dateMillisecond, TIME_ZONE).toDate();
    }

}
