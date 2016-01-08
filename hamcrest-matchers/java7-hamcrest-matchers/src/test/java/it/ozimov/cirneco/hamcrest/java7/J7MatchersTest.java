package it.ozimov.cirneco.hamcrest.java7;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static org.junit.Assert.assertThat;

import static it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod.AM;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import org.hamcrest.collection.IsIterableWithSize;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import it.ozimov.cirneco.hamcrest.java7.base.IsBetween;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenLowerBoundInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenUpperBoundInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsSame;
import it.ozimov.cirneco.hamcrest.java7.collect.BaseIterableMatcherTest;
import it.ozimov.cirneco.hamcrest.java7.collect.IsEmptyIterable;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInAnyOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInRelativeOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableWithDistinctElements;
import it.ozimov.cirneco.hamcrest.java7.collect.IsMapWithSameKeySet;
import it.ozimov.cirneco.hamcrest.java7.collect.IsSortedIterable;
import it.ozimov.cirneco.hamcrest.java7.collect.IsSortedIterableWithComparator;
import it.ozimov.cirneco.hamcrest.java7.date.IsDate;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInDay;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInLeapYear;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInMonth;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInWeekOfYear;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateWithTime;
import it.ozimov.cirneco.hamcrest.java7.number.IsInfinity;
import it.ozimov.cirneco.hamcrest.java7.number.IsNegative;
import it.ozimov.cirneco.hamcrest.java7.number.IsNegativeInfinity;
import it.ozimov.cirneco.hamcrest.java7.number.IsNotANumber;
import it.ozimov.cirneco.hamcrest.java7.number.IsPositive;
import it.ozimov.cirneco.hamcrest.java7.number.IsPositiveInfinity;
import it.ozimov.cirneco.hamcrest.java7.web.IsEmail;

@RunWith(MockitoJUnitRunner.class)
public class J7MatchersTest {

    @Mock
    public Object object;

    @Mock
    public Comparable comparable;

    @Mock
    public Comparator<Object> comparator;

    @Mock
    public Map map;

    @Mock
    public Set set;

    @Test
    public void testBetween() throws Exception {
        assertThat(J7Matchers.between(0, 100), instanceOf(IsBetween.class));
    }

    @Test
    public void testBetweenInclusive() throws Exception {
        assertThat(J7Matchers.betweenInclusive(0, 100), instanceOf(IsBetweenInclusive.class));
    }

    @Test
    public void testBetweenLowerBoundInclusive() throws Exception {
        assertThat(J7Matchers.betweenLowerBoundInclusive(0, 100), instanceOf(IsBetweenLowerBoundInclusive.class));
    }

    @Test
    public void testBetweenUpperBoundInclusive() throws Exception {
        assertThat(J7Matchers.betweenUpperBoundInclusive(0, 100), instanceOf(IsBetweenUpperBoundInclusive.class));
    }

    @Test
    public void testSameInstance() throws Exception {
        assertThat(J7Matchers.sameInstance(object), instanceOf(IsSame.class));
    }

    @Test
    public void testHasYear() throws Exception {
        assertThat(J7Matchers.hasYear(2015), instanceOf(IsDate.class));
    }

    @Test
    public void testHasMonth() throws Exception {
        assertThat(J7Matchers.hasMonth(12), instanceOf(IsDate.class));
    }

    @Test
    public void testHasDay() throws Exception {
        assertThat(J7Matchers.hasDay(22), instanceOf(IsDate.class));
    }

    @Test
    public void testHasYearAndMonth() throws Exception {
        assertThat(J7Matchers.hasYearAndMonth(2015, 12), instanceOf(IsDate.class));
    }

    @Test
    public void testHasYearMonthAndDay() throws Exception {
        assertThat(J7Matchers.hasYearMonthAndDay(2015, 12, 22), instanceOf(IsDate.class));
    }

    @Test
    public void testHasHour() throws Exception {
        assertThat(J7Matchers.hasHour(11), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourWithClockPeriod() throws Exception {
        assertThat(J7Matchers.hasHour(11, AM), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasMinute() throws Exception {
        assertThat(J7Matchers.hasMinute(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasSecond() throws Exception {
        assertThat(J7Matchers.hasSecond(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasMillisecond() throws Exception {
        assertThat(J7Matchers.hasMillisecond(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourAndMin() throws Exception {
        assertThat(J7Matchers.hasHourAndMin(11, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourAndMinWithClockPeriod() throws Exception {
        assertThat(J7Matchers.hasHourAndMin(11, AM, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinAndSec() throws Exception {
        assertThat(J7Matchers.hasHourMinAndSec(11, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinAndSecWithClockPeriod() throws Exception {
        assertThat(J7Matchers.hasHourMinAndSec(11, AM, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinSecAndMillis() throws Exception {
        assertThat(J7Matchers.hasHourMinSecAndMillis(11, 0, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinSecAndMillisWithClockPeriod() throws Exception {
        assertThat(J7Matchers.hasHourMinSecAndMillis(11, AM, 0, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testInWeekOfYear() throws Exception {
        assertThat(J7Matchers.inWeekOfYear(1), instanceOf(IsDateInWeekOfYear.class));
    }

    @Test
    public void testLeapYear() throws Exception {
        assertThat(J7Matchers.leapYear(), instanceOf(IsDateInLeapYear.class));
    }

    @Test
    public void testJanuary() throws Exception {
        assertThat(J7Matchers.january(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testFebruary() throws Exception {
        assertThat(J7Matchers.february(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testMarch() throws Exception {
        assertThat(J7Matchers.march(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testApril() throws Exception {
        assertThat(J7Matchers.april(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testMay() throws Exception {
        assertThat(J7Matchers.may(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testJune() throws Exception {
        assertThat(J7Matchers.june(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testJuly() throws Exception {
        assertThat(J7Matchers.july(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testAugust() throws Exception {
        assertThat(J7Matchers.august(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testSeptember() throws Exception {
        assertThat(J7Matchers.september(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testOctober() throws Exception {
        assertThat(J7Matchers.october(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testNovember() throws Exception {
        assertThat(J7Matchers.november(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testDecember() throws Exception {
        assertThat(J7Matchers.december(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testSunday() throws Exception {
        assertThat(J7Matchers.sunday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testMonday() throws Exception {
        assertThat(J7Matchers.monday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testTuesday() throws Exception {
        assertThat(J7Matchers.tuesday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testWednesday() throws Exception {
        assertThat(J7Matchers.wednesday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testThursday() throws Exception {
        assertThat(J7Matchers.thursday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testFriday() throws Exception {
        assertThat(J7Matchers.friday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testSaturday() throws Exception {
        assertThat(J7Matchers.saturday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testAfter() throws Exception {
        assertThat(J7Matchers.after(comparable), instanceOf(TypeSafeMatcher.class));
    }

    @Test
    public void testAfterOrEqual() throws Exception {
        assertThat(J7Matchers.afterOrEqual(comparable), instanceOf(TypeSafeMatcher.class));
    }

    @Test
    public void testBefore() throws Exception {
        assertThat(J7Matchers.before(comparable), instanceOf(TypeSafeMatcher.class));
    }

    @Test
    public void testBeforeOrEqual() throws Exception {
        assertThat(J7Matchers.beforeOrEqual(comparable), instanceOf(TypeSafeMatcher.class));
    }

    @Test
    public void testEmpty() throws Exception {
        assertThat(J7Matchers.empty(), instanceOf(IsEmptyIterable.class));
    }

    @Test
    public void testContainsInAnyOrder() throws Exception {
        assertThat(J7Matchers.containsInAnyOrder(Arrays.asList(object, object)),
            instanceOf(IsIterableContainingInAnyOrder.class));
    }

    @Test
    public void testContainsInOrder() throws Exception {
        assertThat(J7Matchers.containsInOrder(Arrays.asList(object, object)),
            instanceOf(IsIterableContainingInOrder.class));
    }

    @Test
    public void testContainsInRelativeOrder() throws Exception {
        assertThat(J7Matchers.containsInRelativeOrder(Arrays.asList(object, object)),
            instanceOf(IsIterableContainingInRelativeOrder.class));
    }

    @Test
    public void testHasDistinctElements() throws Exception {
        assertThat(J7Matchers.hasDistinctElements(), instanceOf(IsIterableWithDistinctElements.class));
    }

    @Test
    public void testHasSizeOne() throws Exception {

        // Arrange
        final int expectedSize = 1;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSizeOne();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeTwo() throws Exception {

        // Arrange
        final int expectedSize = 2;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSizeTwo();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeThree() throws Exception {

        // Arrange
        final int expectedSize = 3;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSizeThree();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFour() throws Exception {

        // Arrange
        final int expectedSize = 4;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSizeFour();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFive() throws Exception {

        // Arrange
        final int expectedSize = 5;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSizeFive();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSize() throws Exception {

        // Arrange
        final int expectedSize = 100;
        final Iterable<Object> iterableWithExpectedSize = BaseIterableMatcherTest.createIterableWithSize(expectedSize);

        // Act
        final Matcher<Iterable<Object>> matcher = J7Matchers.hasSize(expectedSize);
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        // Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testIsSorted() throws Exception {
        assertThat(J7Matchers.sorted(), instanceOf(IsSortedIterable.class));
    }

    @Test
    public void testIsSortedReversed() throws Exception {
        assertThat(J7Matchers.sortedReversed(), instanceOf(IsSortedIterable.class));
    }

    @Test
    public void testHasSameKeySetWithComparator() throws Exception {
        assertThat(J7Matchers.sorted(comparator), instanceOf(IsSortedIterableWithComparator.class));
    }

    @Test
    public void testIsSortedReversedWithComparator() throws Exception {
        assertThat(J7Matchers.sortedReversed(comparator), instanceOf(IsSortedIterableWithComparator.class));
    }

    @Test
    public void testHasSameKeySetForMap() throws Exception {
        assertThat(J7Matchers.hasSameKeySet(map), instanceOf(IsMapWithSameKeySet.class));
    }

    @Test
    public void testPositive() throws Exception {
        assertThat(J7Matchers.positive(), instanceOf(IsPositive.class));
    }

    @Test
    public void testNegative() throws Exception {
        assertThat(J7Matchers.negative(), instanceOf(IsNegative.class));
    }

    @Test
    public void testInfinity() throws Exception {
        assertThat(J7Matchers.infinity(), instanceOf(IsInfinity.class));
    }

    @Test
    public void testNegativeInfinity() throws Exception {
        assertThat(J7Matchers.negativeInfinity(), instanceOf(IsNegativeInfinity.class));
    }

    @Test
    public void testNotANumber() throws Exception {
        assertThat(J7Matchers.notANumber(), instanceOf(IsNotANumber.class));
    }

    @Test
    public void testPositiveInfinity() throws Exception {
        assertThat(J7Matchers.positiveInfinity(), instanceOf(IsPositiveInfinity.class));
    }

    @Test
    public void testEmail() throws Exception {
        assertThat(J7Matchers.email(), instanceOf(IsEmail.class));
    }

}
