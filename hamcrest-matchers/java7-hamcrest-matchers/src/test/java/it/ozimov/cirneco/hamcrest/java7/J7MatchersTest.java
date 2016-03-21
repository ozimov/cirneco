package it.ozimov.cirneco.hamcrest.java7;

import it.ozimov.cirneco.hamcrest.SameCallerMatchersTest;
import it.ozimov.cirneco.hamcrest.java7.base.HasToStringContaining;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetween;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenLowerBoundInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsBetweenUpperBoundInclusive;
import it.ozimov.cirneco.hamcrest.java7.base.IsSame;
import it.ozimov.cirneco.hamcrest.java7.base.IsSameHashcode;
import it.ozimov.cirneco.hamcrest.java7.clazz.IsClassWithAnnotation;
import it.ozimov.cirneco.hamcrest.java7.clazz.IsValidNoArgumentConstructor;
import it.ozimov.cirneco.hamcrest.java7.collect.IsEmptyIterable;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInAnyOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInRelativeOrder;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableWithDistinctElements;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableWithSize;
import it.ozimov.cirneco.hamcrest.java7.collect.IsMapWithSameKeySet;
import it.ozimov.cirneco.hamcrest.java7.collect.IsSortedIterable;
import it.ozimov.cirneco.hamcrest.java7.collect.IsSortedIterableWithComparator;
import it.ozimov.cirneco.hamcrest.java7.date.IsDate;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInDay;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInLeapYear;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInMonth;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateInWeekOfYear;
import it.ozimov.cirneco.hamcrest.java7.date.IsDateWithTime;
import it.ozimov.cirneco.hamcrest.java7.date.utils.ClockPeriod;
import it.ozimov.cirneco.hamcrest.java7.filetype.IsYaml;
import it.ozimov.cirneco.hamcrest.java7.number.IsInfinity;
import it.ozimov.cirneco.hamcrest.java7.number.IsNegative;
import it.ozimov.cirneco.hamcrest.java7.number.IsNegativeInfinity;
import it.ozimov.cirneco.hamcrest.java7.number.IsNotANumber;
import it.ozimov.cirneco.hamcrest.java7.number.IsPositive;
import it.ozimov.cirneco.hamcrest.java7.number.IsPositiveInfinity;
import it.ozimov.cirneco.hamcrest.java7.web.IsEmail;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Comparator;

public class J7MatchersTest extends SameCallerMatchersTest {

    @Override
    public String setFullyQualifiedSourceClass() {
        return J7Matchers.class.getCanonicalName();
    }

    @Test
    public void testBetween() throws Exception {
        testMethodCallsRightMethod(IsBetween.class, "between");
    }

    @Test
    public void testBetweenInclusive() throws Exception {
        testMethodCallsRightMethod(IsBetweenInclusive.class, "betweenInclusive");
    }

    @Test
    public void testBetweenLowerBoundInclusive() throws Exception {
        testMethodCallsRightMethod(IsBetweenLowerBoundInclusive.class, "betweenLowerBoundInclusive");
    }

    @Test
    public void testBetweenUpperBoundInclusive() throws Exception {
        testMethodCallsRightMethod(IsBetweenUpperBoundInclusive.class, "betweenUpperBoundInclusive");
    }

    @Test
    public void testSameInstance() throws Exception {
        testMethodCallsRightMethod(IsSame.class, "sameInstance");
    }

    @Test
    public void testSameHashcode() throws Exception {
        testMethodCallsRightMethod(IsSameHashcode.class, "sameHashcode");
    }

    @Test
    public void testHasRuntimeAnnotation() throws Exception {
        testMethodCallsRightMethod(IsClassWithAnnotation.class, "hasRuntimeAnnotation");
    }

    @Test
    public void testHasNoArgumentConstructor() throws Exception {
        testMethodCallsRightMethod(IsValidNoArgumentConstructor.class, "hasNoArgumentConstructor");
    }

    @Test
    public void testHasYear() throws Exception {
        testMethodCallsRightMethod(IsDate.class, "hasYear");
    }

    @Test
    public void testHasMonth() throws Exception {
        testMethodCallsRightMethod(IsDate.class, "hasMonth");
    }

    @Test
    public void testHasDay() throws Exception {
        testMethodCallsRightMethod(IsDate.class, "hasDay");
    }

    @Test
    public void testHasYearAndMonth() throws Exception {
        testMethodCallsRightMethod(IsDate.class, "hasYearAndMonth");
    }

    @Test
    public void testHasYearMonthAndDay() throws Exception {
        testMethodCallsRightMethod(IsDate.class, "hasYearMonthAndDay");
    }

    @Test
    public void testHasHour() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHour", int.class);
    }

    @Test
    public void testHasHourWithClockPeriod() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHour", int.class, ClockPeriod.class);
    }

    @Test
    public void testHasMinute() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasMinute");
    }

    @Test
    public void testHasSecond() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasSecond");
    }

    @Test
    public void testHasMillisecond() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasMillisecond");
    }

    @Test
    public void testHasHourAndMin() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourAndMin", int.class, int.class);
    }

    @Test
    public void testHasHourAndMinWithClockPeriod() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourAndMin", int.class, ClockPeriod.class, int.class);
    }

    @Test
    public void testHasHourMinAndSec() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourMinAndSec", int.class, int.class, int.class);
    }

    @Test
    public void testHasHourMinAndSecWithClockPeriod() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourMinAndSec", int.class, ClockPeriod.class, int.class,
                int.class);
    }

    @Test
    public void testHasHourMinSecAndMillis() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourMinSecAndMillis", int.class, int.class, int.class,
                int.class);
    }

    @Test
    public void testHasHourMinSecAndMillisWithClockPeriod() throws Exception {
        testMethodCallsRightMethod(IsDateWithTime.class, "hasHourMinSecAndMillis", int.class, int.class, int.class,
                int.class);
    }

    @Test
    public void testInWeekOfYear() throws Exception {
        testMethodCallsRightMethod(IsDateInWeekOfYear.class, "inWeekOfYear");
    }

    @Test
    public void testLeapYear() throws Exception {
        testMethodCallsRightMethod(IsDateInLeapYear.class, "leapYear");
    }

    @Test
    public void testJanuary() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "january");
    }

    @Test
    public void testFebruary() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "february");
    }

    @Test
    public void testMarch() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "march");
    }

    @Test
    public void testApril() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "april");
    }

    @Test
    public void testMay() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "may");
    }

    @Test
    public void testJune() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "june");
    }

    @Test
    public void testJuly() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "july");
    }

    @Test
    public void testAugust() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "august");
    }

    @Test
    public void testSeptember() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "september");
    }

    @Test
    public void testOctober() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "october");
    }

    @Test
    public void testNovember() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "november");
    }

    @Test
    public void testDecember() throws Exception {
        testMethodCallsRightMethod(IsDateInMonth.class, "december");
    }

    @Test
    public void testSunday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "sunday");
    }

    @Test
    public void testMonday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "monday");
    }

    @Test
    public void testTuesday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "tuesday");
    }

    @Test
    public void testWednesday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "wednesday");
    }

    @Test
    public void testThursday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "thursday");
    }

    @Test
    public void testFriday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "friday");
    }

    @Test
    public void testSaturday() throws Exception {
        testMethodCallsRightMethod(IsDateInDay.class, "saturday");
    }

    @Test
    public void testAfter() throws Exception {
        testMethodCallsRightMethod(Matchers.class, "greaterThan", "after");
    }

    @Test
    public void testAfterOrEqual() throws Exception {
        testMethodCallsRightMethod(Matchers.class, "greaterThanOrEqualTo", "afterOrEqual");
    }

    @Test
    public void testBefore() throws Exception {
        testMethodCallsRightMethod(Matchers.class, "lessThan", "before");
    }

    @Test
    public void testBeforeOrEqual() throws Exception {
        testMethodCallsRightMethod(Matchers.class, "lessThanOrEqualTo", "beforeOrEqual");
    }

    @Test
    public void testHasToStringContainingInOrder() throws Exception {
        testMethodCallsRightMethod(HasToStringContaining.class, "hasToStringContainingInOrder", "hasToStringContainingInOrder");
    }


    @Test
    public void testEmpty() throws Exception {
        testMethodCallsRightMethod(IsEmptyIterable.class, "empty");
    }

    @Test
    public void testContainsInAnyOrder() throws Exception {
        testMethodCallsRightMethod(IsIterableContainingInAnyOrder.class, "containsInAnyOrder");
    }

    @Test
    public void testContainsInOrder() throws Exception {
        testMethodCallsRightMethod(IsIterableContainingInOrder.class, "containsInOrder");
    }

    @Test
    public void testContainsInRelativeOrder() throws Exception {
        testMethodCallsRightMethod(IsIterableContainingInRelativeOrder.class, "containsInRelativeOrder");
    }

    @Test
    public void testHasDistinctElements() throws Exception {
        testMethodCallsRightMethod(IsIterableWithDistinctElements.class, "hasDistinctElements");
    }

    @Test
    public void testHasSizeOne() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSizeOne");
    }

    @Test
    public void testHasSizeTwo() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSizeTwo");
    }

    @Test
    public void testHasSizeThree() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSizeThree");
    }

    @Test
    public void testHasSizeFour() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSizeFour");
    }

    @Test
    public void testHasSizeFive() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSizeFive");
    }

    @Test
    public void testHasSize() throws Exception {
        testMethodCallsRightMethod(IsIterableWithSize.class, "hasSize");
    }

    @Test
    public void testIsSorted() throws Exception {
        testMethodCallsRightMethod(IsSortedIterable.class, "sorted");
    }

    @Test
    public void testIsSortedReversed() throws Exception {
        testMethodCallsRightMethod(IsSortedIterable.class, "sortedReversed");
    }

    @Test
    public void testIsSortedWithComparator() throws Exception {
        testMethodCallsRightMethod(IsSortedIterableWithComparator.class, "sorted", Comparator.class);
    }

    @Test
    public void testIsSortedReversedWithComparator() throws Exception {
        testMethodCallsRightMethod(IsSortedIterableWithComparator.class, "sortedReversed", Comparator.class);
    }

    @Test
    public void testHasSameKeySetForMap() throws Exception {
        testMethodCallsRightMethod(IsMapWithSameKeySet.class, "hasSameKeySet");
    }

    @Test
    public void testYaml() throws Exception {
        testMethodCallsRightMethod(IsYaml.class, "yaml");
    }

    @Test
    public void testPositive() throws Exception {
        testMethodCallsRightMethod(IsPositive.class, "positive");
    }

    @Test
    public void testNegative() throws Exception {
        testMethodCallsRightMethod(IsNegative.class, "negative");
    }

    @Test
    public void testInfinity() throws Exception {
        testMethodCallsRightMethod(IsInfinity.class, "infinity");
    }

    @Test
    public void testNegativeInfinity() throws Exception {
        testMethodCallsRightMethod(IsNegativeInfinity.class, "negativeInfinity");
    }

    @Test
    public void testPositiveInfinity() throws Exception {
        testMethodCallsRightMethod(IsPositiveInfinity.class, "positiveInfinity");
    }

    @Test
    public void testNotANumber() throws Exception {
        testMethodCallsRightMethod(IsNotANumber.class, "notANumber");
    }

    @Test
    public void testEmail() throws Exception {
        testMethodCallsRightMethod(IsEmail.class, "email");
    }

}
