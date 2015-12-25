package org.cirneco.assertions.hamcrest;

import com.google.common.base.Equivalence;
import org.cirneco.assertions.hamcrest.base.IsEmptyGuavaOptional;
import org.cirneco.assertions.hamcrest.base.IsEquivalent;
import org.cirneco.assertions.hamcrest.base.IsSame;
import org.cirneco.assertions.hamcrest.date.IsDate;
import org.cirneco.assertions.hamcrest.date.IsDateInDay;
import org.cirneco.assertions.hamcrest.date.IsDateInLeapYear;
import org.cirneco.assertions.hamcrest.date.IsDateInMonth;
import org.cirneco.assertions.hamcrest.date.IsDateWithTime;
import org.cirneco.assertions.hamcrest.iterable.IsEmptyIterable;
import org.cirneco.assertions.hamcrest.iterable.IsSortedIterable;
import org.cirneco.assertions.hamcrest.iterable.IsSortedIterableWithComparator;
import org.cirneco.assertions.hamcrest.map.IsMapWithSameKeySet;
import org.cirneco.assertions.hamcrest.base.IsBetween;
import org.cirneco.assertions.hamcrest.base.IsBetweenInclusive;
import org.cirneco.assertions.hamcrest.base.IsBetweenLowerBoundInclusive;
import org.cirneco.assertions.hamcrest.base.IsBetweenUpperBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsInfinity;
import org.cirneco.assertions.hamcrest.number.IsNegativeInfinity;
import org.cirneco.assertions.hamcrest.number.IsNotANumber;
import org.cirneco.assertions.hamcrest.number.IsPositiveInfinity;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableWithSize;
import org.hamcrest.number.OrderingComparison;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Comparator;
import java.util.Map;

import static org.cirneco.assertions.hamcrest.date.utils.ClockPeriod.AM;
import static org.cirneco.assertions.hamcrest.iterable.BaseIterableMatcherTest.createIterableWithSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CirnecoMatchersJ7Test {

    @Mock
    public Object object;

    @Mock
    public Comparable comparable;

    @Mock
    public Comparator<Object> comparator;

    @Mock
    public Map map;

    @Mock
    public Equivalence<Object> equivalence;

    @Test
    public void testBetween() throws Exception {
        assertThat(CirnecoMatchersJ7.between(0, 100), instanceOf(IsBetween.class));
    }

    @Test
    public void testBetweenInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenInclusive(0, 100), instanceOf(IsBetweenInclusive.class));
    }

    @Test
    public void testBetweenLowerBoundInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenLowerBoundInclusive(0, 100), instanceOf(IsBetweenLowerBoundInclusive.class));
    }

    @Test
    public void testBetweenUpperBoundInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenUpperBoundInclusive(0, 100), instanceOf(IsBetweenUpperBoundInclusive.class));
    }

    @Test
    public void testEmptyGuavaOptional() throws Exception {
        assertThat(CirnecoMatchersJ7.emptyGuavaOptional(), instanceOf(IsEmptyGuavaOptional.class));
    }

    @Test
    public void testEquivalentTo() throws Exception {
        assertThat(CirnecoMatchersJ7.equivalentTo(object, equivalence), instanceOf(IsEquivalent.class));
    }

    @Test
    public void testSameInstance() throws Exception {
        assertThat(CirnecoMatchersJ7.sameInstance(object), instanceOf(IsSame.class));
    }

    @Test
    public void testHasYear() throws Exception {
        assertThat(CirnecoMatchersJ7.hasYear(2015), instanceOf(IsDate.class));
    }

    @Test
    public void testHasMonth() throws Exception {
        assertThat(CirnecoMatchersJ7.hasMonth(12), instanceOf(IsDate.class));
    }

    @Test
    public void testHasDay() throws Exception {
        assertThat(CirnecoMatchersJ7.hasDay(22), instanceOf(IsDate.class));
    }

    @Test
    public void testHasYearAndMonth() throws Exception {
        assertThat(CirnecoMatchersJ7.hasYearAndMonth(2015, 12), instanceOf(IsDate.class));
    }

    @Test
    public void testHasYearMonthAndDay() throws Exception {
        assertThat(CirnecoMatchersJ7.hasYearMonthAndDay(2015, 12, 22), instanceOf(IsDate.class));
    }

    @Test
    public void testHasHour() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHour(11), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourWithClockPeriod() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHour(11, AM), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasMinute() throws Exception {
        assertThat(CirnecoMatchersJ7.hasMinute(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasSecond() throws Exception {
        assertThat(CirnecoMatchersJ7.hasSecond(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasMillisecond() throws Exception {
        assertThat(CirnecoMatchersJ7.hasMillisecond(0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourAndMin() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourAndMin(11, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourAndMinWithClockPeriod() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourAndMin(11, AM, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinAndSec() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourMinAndSec(11, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinAndSecWithClockPeriod() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourMinAndSec(11, AM, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinSecAndMillis() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourMinSecAndMillis(11, 0, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testHasHourMinSecAndMillisWithClockPeriod() throws Exception {
        assertThat(CirnecoMatchersJ7.hasHourMinSecAndMillis(11, AM, 0, 0, 0), instanceOf(IsDateWithTime.class));
    }

    @Test
    public void testLeapYear() throws Exception {
        assertThat(CirnecoMatchersJ7.leapYear(), instanceOf(IsDateInLeapYear.class));
    }

    @Test
    public void testJanuary() throws Exception {
        assertThat(CirnecoMatchersJ7.january(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testFebruary() throws Exception {
        assertThat(CirnecoMatchersJ7.february(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testMarch() throws Exception {
        assertThat(CirnecoMatchersJ7.march(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testApril() throws Exception {
        assertThat(CirnecoMatchersJ7.april(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testMay() throws Exception {
        assertThat(CirnecoMatchersJ7.may(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testJune() throws Exception {
        assertThat(CirnecoMatchersJ7.june(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testJuly() throws Exception {
        assertThat(CirnecoMatchersJ7.july(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testAugust() throws Exception {
        assertThat(CirnecoMatchersJ7.august(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testSeptember() throws Exception {
        assertThat(CirnecoMatchersJ7.september(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testOctober() throws Exception {
        assertThat(CirnecoMatchersJ7.october(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testNovember() throws Exception {
        assertThat(CirnecoMatchersJ7.november(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testDecember() throws Exception {
        assertThat(CirnecoMatchersJ7.december(), instanceOf(IsDateInMonth.class));
    }

    @Test
    public void testSunday() throws Exception {
        assertThat(CirnecoMatchersJ7.sunday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testMonday() throws Exception {
        assertThat(CirnecoMatchersJ7.monday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testTuesday() throws Exception {
        assertThat(CirnecoMatchersJ7.tuesday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testWednesday() throws Exception {
        assertThat(CirnecoMatchersJ7.wednesday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testThursday() throws Exception {
        assertThat(CirnecoMatchersJ7.thursday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testFriday() throws Exception {
        assertThat(CirnecoMatchersJ7.friday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testSaturda() throws Exception {
        assertThat(CirnecoMatchersJ7.saturday(), instanceOf(IsDateInDay.class));
    }

    @Test
    public void testAfter() throws Exception {
        assertThat(CirnecoMatchersJ7.after(comparable), instanceOf(OrderingComparison.class));
    }

    @Test
    public void testAfterOrEqual() throws Exception {
        assertThat(CirnecoMatchersJ7.afterOrEqual(comparable), instanceOf(OrderingComparison.class));
    }

    @Test
    public void testBefore() throws Exception {
        assertThat(CirnecoMatchersJ7.before(comparable), instanceOf(OrderingComparison.class));
    }

    @Test
    public void testBeforeOrEqual() throws Exception {
        assertThat(CirnecoMatchersJ7.beforeOrEqual(comparable), instanceOf(OrderingComparison.class));
    }

    @Test
    public void testEmpty() throws Exception {
        assertThat(CirnecoMatchersJ7.empty(), instanceOf(IsEmptyIterable.class));
    }

    @Test
    public void testHasSizeOne() throws Exception {
        //Arrange
        final int expectedSize = 1;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeOne();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeTwo() throws Exception {
        //Arrange
        final int expectedSize = 2;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeTwo();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeThree() throws Exception {
        //Arrange
        final int expectedSize = 3;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeThree();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFour() throws Exception {
        //Arrange
        final int expectedSize = 4;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeFour();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFive() throws Exception {
        //Arrange
        final int expectedSize = 5;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeFive();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSize() throws Exception {
        //Arrange
        final int expectedSize = 100;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSize(expectedSize);
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testIsSorted() throws Exception {
        assertThat(CirnecoMatchersJ7.sorted(), instanceOf(IsSortedIterable.class));
    }

    @Test
    public void testIsSortedReversed() throws Exception {
        assertThat(CirnecoMatchersJ7.sortedReversed(), instanceOf(IsSortedIterable.class));
    }

    @Test
    public void testHasSameKeySetWithComparator() throws Exception {
        assertThat(CirnecoMatchersJ7.sorted(comparator), instanceOf(IsSortedIterableWithComparator.class));
    }

    @Test
    public void testIsSortedReversedWithComparator() throws Exception {
        assertThat(CirnecoMatchersJ7.sortedReversed(comparator), instanceOf(IsSortedIterableWithComparator.class));
    }

    @Test
    public void testHasSameKeySet() throws Exception {
        assertThat(CirnecoMatchersJ7.hasSameKeySet(map), instanceOf(IsMapWithSameKeySet.class));
    }

    @Test
    public void testInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.infinity(), instanceOf(IsInfinity.class));
    }

    @Test
    public void testNegativeInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.negativeInfinity(), instanceOf(IsNegativeInfinity.class));
    }

    @Test
    public void testNotANumber() throws Exception {
        assertThat(CirnecoMatchersJ7.notANumber(), instanceOf(IsNotANumber.class));
    }

    @Test
    public void testPositiveInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.positiveInfinity(), instanceOf(IsPositiveInfinity.class));
    }
}