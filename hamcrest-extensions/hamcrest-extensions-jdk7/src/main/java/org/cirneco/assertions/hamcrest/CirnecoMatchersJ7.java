package org.cirneco.assertions.hamcrest;

import com.google.common.base.Equivalence;
import com.google.common.base.Optional;
import org.cirneco.assertions.hamcrest.base.IsEmptyGuavaOptional;
import org.cirneco.assertions.hamcrest.base.IsEquivalent;
import org.cirneco.assertions.hamcrest.base.IsSame;
import org.cirneco.assertions.hamcrest.date.IsDate;
import org.cirneco.assertions.hamcrest.date.IsDateInDay;
import org.cirneco.assertions.hamcrest.date.IsDateInLeapYear;
import org.cirneco.assertions.hamcrest.date.IsDateInMonth;
import org.cirneco.assertions.hamcrest.date.IsDateWithTime;
import org.cirneco.assertions.hamcrest.date.utils.ClockPeriod;
import org.cirneco.assertions.hamcrest.iterable.IsEmptyIterable;
import org.cirneco.assertions.hamcrest.iterable.IsIterableWithSize;
import org.cirneco.assertions.hamcrest.iterable.IsSortedIterable;
import org.cirneco.assertions.hamcrest.iterable.IsSortedIterableWithComparator;
import org.cirneco.assertions.hamcrest.map.IsMapWithSameKeySet;
import org.cirneco.assertions.hamcrest.number.IsBetween;
import org.cirneco.assertions.hamcrest.number.IsBetweenInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenLowerBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenUpperBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsInfinity;
import org.cirneco.assertions.hamcrest.number.IsNegativeInfinity;
import org.cirneco.assertions.hamcrest.number.IsNotANumber;
import org.cirneco.assertions.hamcrest.number.IsPositiveInfinity;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * The {@code CirnecoMatchersJ7} class groups all the matchers
 * introduced by Cirneco's Hamcrest extension for Java 7.
 * Suggested use would be to import all the static methods of this class in a unit tes.
 */
public class CirnecoMatchersJ7 {

    //BASE

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional}
     * contains no object.
     */
    public static Matcher<Optional> emptyGuavaOptional() {
        return IsEmptyGuavaOptional.emptyOptional();
    }

    /**
     * Creates a matcher that matches when the examined object of type <code>T</code>
     * is equivalent to the specified <code>comparison</code> object according to
     * the provided {@linkplain Equivalence}.
     * <p/>
     * <p>Observe that the {@linkplain Equivalence} can deal with nulls.</p>
     */
    public static <T> Matcher<T> equivalentTo(final T expected, final Equivalence<T> equivalence) {
        return IsEquivalent.equivalentTo(expected, equivalence);
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} is the same instance as
     * the provided <code>target</code> {@linkplain Object}.
     */
    public static Matcher sameInstance(final Object target) {
        return IsSame.sameInstance(target);
    }

    /**
     * Creates a matcher of a {@link Comparable} object that matches when the examined object is
     * after the given <code>value</code>, as reported by the <code>compareTo</code> method of the
     * <b>examined</b> object.
     * <p>
     * E.g.:
     * <code>
     * Date past;
     * Date now;
     * assertThat(now, after(past));
     * </code>
     * <p>
     * <p>
     * The matcher renames the Hamcrest matcher obtained with {@linkplain org.hamcrest.Matchers#greaterThan(Comparable)}.
     */
    public static <T extends Comparable<T>> Matcher<T> after(final T value) {
        return Matchers.greaterThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is
     * after or equal with respect to object <code>value</code>, as reported by the <code>compareTo</code> method
     * of the <b>examined</b> object.
     * <p>
     * E.g.:
     * <code>
     * Date past;
     * Date now;
     * assertThat(now, afterOrEqual(now));
     * assertThat(now, afterOrEqual(past));
     * </code>
     * <p>
     * <p>
     * The matcher renames the Hamcrest matcher obtained with {@linkplain org.hamcrest.Matchers#greaterThanOrEqualTo(Comparable)}.
     */
    public static <T extends java.lang.Comparable<T>> Matcher<T> afterOrEqual(final T value) {
        return Matchers.greaterThanOrEqualTo(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is
     * before the given <code>value</code>, as reported by the <code>compareTo</code> method of the
     * <b>examined</b> object.
     * <p>
     * E.g.:
     * <code>
     * Date past;
     * Date now;
     * assertThat(past, before(now));
     * </code>
     * <p>
     * <p>
     * The matcher renames the Hamcrest matcher obtained with {@linkplain org.hamcrest.Matchers#lessThan(Comparable)}.
     */
    public static <T extends java.lang.Comparable<T>> Matcher<T> before(final T value) {
        return Matchers.lessThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is
     * before or equal with respect to object <code>value</code>, as reported by the <code>compareTo</code> method
     * of the <b>examined</b> object.
     * <p>
     * E.g.:
     * <code>
     * Date past;
     * Date now;
     * assertThat(now, beforeOrEqual(now));
     * assertThat(past, beforeOrEqual(now));
     * </code>
     * <p>
     * <p>
     * The matcher renames the Hamcrest matcher obtained with {@linkplain org.hamcrest.Matchers#lessThanOrEqualTo(Comparable)}.
     */
    public static <T extends java.lang.Comparable<T>> Matcher<T> beforeOrEqual(final T value) {
        return Matchers.lessThanOrEqualTo(value);
    }


    //DATE

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>year</code>.
     */
    public static Matcher<Date> hasYear(final int year) {
        return IsDate.hasYear(year);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>id</code>.
     */
    public static Matcher<Date> hasMonth(final Integer month) {
        return IsDate.hasMonth(month);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>day</code>.
     */
    public static Matcher<Date> hasDay(final Integer day) {
        return IsDate.hasDay(day);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>year</code> and <code>id</code>.
     */
    public static Matcher<Date> hasYearAndMonth(final int year, final int month) {
        return IsDate.hasYearAndMonth(year, month);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>year</code>, <code>id</code> and
     * <code>day</code>.
     */
    public static Matcher<Date> hasYearMonthAndDay(final Integer year,
                                                   final Integer month, final Integer day) {
        return IsDate.hasYearMonthAndDay(year, month, day);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>hour</code> in a 24 hours clock period.
     */
    public static Matcher<Date> hasHour(final int hour) {
        return IsDateWithTime.hasHour(hour);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> and <code>ClockPeriod</code> (e.g. <em>AM</em>).
     */
    public static Matcher<Date> hasHour(final int hour, final ClockPeriod clockPeriod) {
        return IsDateWithTime.hasHour(hour, clockPeriod);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>minute</code>.
     */
    public static Matcher<Date> hasMinute(final Integer minute) {
        return IsDateWithTime.hasMinute(minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given <code>sec</code>.
     */
    public static Matcher<Date> hasSecond(final Integer second) {
        return IsDateWithTime.hasSecond(second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given  <code>millis</code>.
     */
    public static Matcher<Date> hasMillisecond(final Integer millisecond) {
        return IsDateWithTime.hasMillisecond(millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final int minute) {
        return IsDateWithTime.hasHourAndMin(hour, minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>) and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final ClockPeriod clockPeriod, final int minute) {
        return IsDateWithTime.hasHourAndMin(hour, clockPeriod, minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period, <code>minute</code> and
     * <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final Integer hour,
                                                 final Integer minute, final Integer second) {
        return IsDateWithTime.hasHourMinAndSec(hour, minute, second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code> and
     * <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final Integer hour, final ClockPeriod clockPeriod,
                                                 final Integer minute, final Integer second) {
        return IsDateWithTime.hasHourMinAndSec(hour, clockPeriod, minute, second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code> in a 24 hours clock period, <code>minute</code>,
     * <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final Integer hour,
                                                       final Integer minute, final Integer second, final Integer millisecond) {
        return IsDateWithTime.hasHourMinSecAndMillis(hour, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * has the given values <code>hour</code>, <code>ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code>,
     * <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final Integer hour, final ClockPeriod clockPeriod,
                                                       final Integer minute, final Integer second, final Integer millisecond) {
        return IsDateWithTime.hasHourMinSecAndMillis(hour, clockPeriod, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a leap year.
     */
    public static Matcher<Date> leapYear() {
        return IsDateInLeapYear.leapYear();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>January</em>.
     */
    public static Matcher<Date> january() {
        return IsDateInMonth.january();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>February</em>.
     */
    public static Matcher<Date> february() {
        return IsDateInMonth.february();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>March</em>.
     */
    public static Matcher<Date> march() {
        return IsDateInMonth.march();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>April</em>.
     */
    public static Matcher<Date> april() {
        return IsDateInMonth.april();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>May</em>.
     */
    public static Matcher<Date> may() {
        return IsDateInMonth.may();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>June</em>.
     */
    public static Matcher<Date> june() {
        return IsDateInMonth.june();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>July</em>.
     */
    public static Matcher<Date> july() {
        return IsDateInMonth.july();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>August</em>.
     */
    public static Matcher<Date> august() {
        return IsDateInMonth.august();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>September</em>.
     */
    public static Matcher<Date> september() {
        return IsDateInMonth.september();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>October</em>.
     */
    public static Matcher<Date> october() {
        return IsDateInMonth.october();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>November</em>.
     */
    public static Matcher<Date> november() {
        return IsDateInMonth.november();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents the id <em>December</em>.
     */
    public static Matcher<Date> december() {
        return IsDateInMonth.december();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Sunday</em>.
     */
    public static Matcher<Date> sunday() {
        return IsDateInDay.sunday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Monday</em>.
     */
    public static Matcher<Date> monday() {
        return IsDateInDay.monday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Tuesday</em>.
     */
    public static Matcher<Date> tuesday() {
        return IsDateInDay.tuesday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Wednesday</em>.
     */
    public static Matcher<Date> wednesday() {
        return IsDateInDay.wednesday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Thursday</em>.
     */
    public static Matcher<Date> thursday() {
        return IsDateInDay.thursday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Friday</em>.
     */
    public static Matcher<Date> friday() {
        return IsDateInDay.friday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date}
     * represents a <em>Saturday</em>.
     */
    public static Matcher<Date> saturday() {
        return IsDateInDay.saturday();
    }


    //ITERABLE

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} has no items.
     * <p>
     * For example:
     * <pre>assertThat(new ArrayList<>(), empty())</pre>
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<? extends E>> empty() {
        return IsEmptyIterable.empty();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>1</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeOne())</pre>
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeOne() {
        return IsIterableWithSize.hasSizeOne();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>2</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeTwo())</pre>
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeTwo() {
        return IsIterableWithSize.hasSizeTwo();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>3</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeThree())</pre>
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeThree() {
        return IsIterableWithSize.hasSizeThree();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>4</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFour())</pre>
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeFour() {
        return IsIterableWithSize.hasSizeFour();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>5</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFive())</pre>
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeFive() {
        return IsIterableWithSize.hasSizeFive();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the
     * examined {@link Iterable} yields an item count equal to <code>size</code>.
     * <p>
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSize(2))</pre>
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSize(final int size) {
        return IsIterableWithSize.hasSize(size);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has
     * the elements of type <code>K</code> that extends {@linkplain Comparable} and the elements
     * are sorted according to the natural ordering.
     */
    public static <K> Matcher<Iterable<K>> sorted() {
        return IsSortedIterable.sorted();
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has
     * the elements of type <code>K</code> that extends {@linkplain Comparable} and the elements
     * are sorted according to the inverse of natural ordering.
     */
    public static <K> Matcher<Iterable<K>> sortedReversed() {
        return IsSortedIterable.sortedReversed();
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has
     * the elements sorted according to the given {@linkplain Comparator}.
     */
    public static <K> Matcher<Iterable<K>> sorted(final Comparator<K> comparator) {
        return IsSortedIterableWithComparator.sorted(comparator);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has
     * the elements sorted according to the given {@linkplain Comparator}, but in a reverse order.
     */
    public static <K> Matcher<Iterable<K>> sortedReversed(final Comparator<K> comparator) {
        return IsSortedIterableWithComparator.sortedReversed(comparator);
    }

    //MAP

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined {@link java.util.Map} has exactly
     * the same key set of the given map.
     * For example:
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     */
    public static <K> Matcher<Map<? extends K, ?>> hasSameKeySet(final Map<? extends K, ?> comparisonMap) {
        return IsMapWithSameKeySet.hasSameKeySet(comparisonMap);
    }

    //NUMBER

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both excluded.
     * <p>
     * For example:
     * <pre>assertThat(10, between(10, 11))</pre>
     * will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> between(final T from, final T to) {
        return IsBetween.between(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     * For example:
     * <pre>assertThat(10, betweenInclusive(10, 11))</pre>
     * will return true.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenInclusive(final T from, final T to) {
        return IsBetweenInclusive.betweenInclusive(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     * For example:
     * <pre>assertThat(10, betweenLowerBoundInclusive(10, 11))</pre>
     * will return true.
     * while:
     * <pre>assertThat(11, betweenLowerBoundInclusive(10, 11))</pre>
     * will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenLowerBoundInclusive(final T from, final T to) {
        return IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     * For example:
     * <pre>assertThat(11, betweenUpperBoundInclusive(10, 11))</pre>
     * will return true.
     * while:
     * <pre>assertThat(10, betweenUpperBoundInclusive(10, 11))</pre>
     * will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenUpperBoundInclusive(final T from, final T to) {
        return IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to either <code>POSITIVE_INFINITY</code> or <code>NEGATIVE_INFINITY</code>.
     * <p>
     * For example:
     * <pre>assertThat(10, negativeInfinity())</pre>
     * will return false.
     * while:
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     * will both return true.
     */
    public static <T extends Number> Matcher<T> infinity() {
        return IsInfinity.infinity();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to <code>NEGATIVE_INFINITY</code>.
     * <p>
     * For example:
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     * will return <code>true</code>.
     * while:
     * <pre>assertThat(10, negativeInfinity())</pre>
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     * will both return <code>false</code>.
     */
    public static <T extends Number> Matcher<T> negativeInfinity() {
        return IsNegativeInfinity.negativeInfinity();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} such that a call to method <code>isNaN()</code> returns <code>true</code>.
     * <p>
     * For example:
     * <pre>assertThat((1.0/.0D), notANumber())</pre>
     * will return true.
     */
    public static <T extends Number> Matcher<T> notANumber() {
        return IsNotANumber.notANumber();
    }


    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to <code>POSITIVE_INFINITY</code>.
     * <p>
     * For example:
     * <pre>assertThat(Double.PositiveInfinity, positiveInfinity())</pre>
     * will return <code>true</code>.
     * while:
     * <pre>assertThat(10, positiveInfinity())</pre>
     * <pre>assertThat(Float.NegativeInfinity, positiveInfinity())</pre>
     * will both return <code>false</code>.
     */
    public static <T extends Number> Matcher<T> positiveInfinity() {
        return IsPositiveInfinity.positiveInfinity();
    }
}
