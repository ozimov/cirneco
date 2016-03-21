package it.ozimov.cirneco.hamcrest.java7;

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
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * {@inheritDoc} The {@code J7Matchers} class groups all the matchers introduced by Cirneco's Hamcrest extension for
 * Java 7. Suggested use would be to import all the static methods of this class in a unit tes.
 *
 * @since version 0.1 for JDK7
 */
public class J7Matchers {

    // BASE

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns a value between
     * <code>from</code> and <code>to</code>, both excluded.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(10, between(10, 11))</pre>
     *
     * will return <code>false</code>.
     */
    public static <T extends Comparable<T>> Matcher<T> between(final T from, final T to) {
        return IsBetween.between(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns a value between
     * <code>from</code> and <code>to</code>, both included.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(10, betweenInclusive(10, 11))</pre>
     *
     * will return <code>true</code>.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenInclusive(final T from, final T to) {
        return IsBetweenInclusive.betweenInclusive(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns a value between
     * <code>from</code> and <code>to</code>, both included.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(10, betweenLowerBoundInclusive(10, 11))</pre>
     *
     * will return <code>true</code>. while:
     *
     * <pre>assertThat(11, betweenLowerBoundInclusive(10, 11))</pre>
     *
     * will return <code>false</code>.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenLowerBoundInclusive(final T from, final T to) {
        return IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive(from, to);
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns a value between
     * <code>from</code> and <code>to</code>, both included.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(11, betweenUpperBoundInclusive(10, 11))</pre>
     *
     * will return <code>true</code>. while:
     *
     * <pre>assertThat(10, betweenUpperBoundInclusive(10, 11))</pre>
     *
     * will return <code>false</code>.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenUpperBoundInclusive(final T from, final T to) {
        return IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(from, to);
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} is the same instance as the provided
     * <code>target</code> {@linkplain Object}.
     */
    public static Matcher sameInstance(final Object target) {
        return IsSame.sameInstance(target);
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} is the same instance as the provided
     * <code>target</code> {@linkplain Object}.
     */
    public static Matcher sameHashcode(final Object target) {
        return IsSameHashcode.sameHashcode(target);
    }

    /**
     * Creates a matcher of a {@link Comparable} object that matches when the examined object is after the given <code>
     * value</code>, as reported by the <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * <p>E.g.: <code>Date past; Date now; assertThat(now, after(past));</code>
     * <p>
     * <p>The matcher renames the Hamcrest matcher obtained with
     * {@linkplain org.hamcrest.Matchers#greaterThan(Comparable)}.
     */
    public static <T extends Comparable<T>> Matcher<T> after(final T value) {
        return Matchers.greaterThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is after or equal with
     * respect to object <code>value</code>, as reported by the <code>compareTo</code> method of the <b>examined</b>
     * object.
     * <p>
     * <p>E.g.: <code>Date past; Date now; assertThat(now, afterOrEqual(now)); assertThat(now, afterOrEqual(past));
     * </code>
     * <p>
     * <p>The matcher renames the Hamcrest matcher obtained with
     * {@linkplain org.hamcrest.Matchers#greaterThanOrEqualTo(Comparable)}.
     */
    public static <T extends Comparable<T>> Matcher<T> afterOrEqual(final T value) {
        return Matchers.greaterThanOrEqualTo(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is before the given <code>
     * value</code>, as reported by the <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * <p>E.g.: <code>Date past; Date now; assertThat(past, before(now));</code>
     * <p>
     * <p>The matcher renames the Hamcrest matcher obtained with {@linkplain org.hamcrest.Matchers#lessThan(Comparable)}.
     */
    public static <T extends Comparable<T>> Matcher<T> before(final T value) {
        return Matchers.lessThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the examined object is before or equal with
     * respect to object <code>value</code>, as reported by the <code>compareTo</code> method of the <b>examined</b>
     * object.
     * <p>
     * <p>E.g.: <code>Date past; Date now; assertThat(now, beforeOrEqual(now)); assertThat(past, beforeOrEqual(now));
     * </code>
     * <p>
     * <p>The matcher renames the Hamcrest matcher obtained with
     * {@linkplain org.hamcrest.Matchers#lessThanOrEqualTo(Comparable)}.
     */
    public static <T extends Comparable<T>> Matcher<T> beforeOrEqual(final T value) {
        return Matchers.lessThanOrEqualTo(value);
    }


    /**
     * Creates a matcher that matches any examined object whose <code>toString</code> method
     * returns a value that contains all the substrings given by the <code>toString()</code> method called
     * on the given list of items, considering the order of their appearance.
     * For example:
     * <pre>assertThat("AClass{a_field=a value, another_field=another value}",
     *  ContainingInOrder("a value","another value"))</pre>
     */
    public static <T> Matcher<T> hasToStringContainingInOrder(final Object... items) {
        return HasToStringContaining.hasToStringContainingInOrder(items);
    }

    // CLAZZ

    /**
     * Creates a matcher that matches when the examined {@linkplain Class} has the given runtime available annotation
     */
    public static Matcher<Class> hasRuntimeAnnotation(final Class<? extends Annotation> annotation) {
        return IsClassWithAnnotation.hasRuntimeAnnotation(annotation);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Class} has a valid default constructor with no
     * parameters.
     */
    public static Matcher<Class> hasNoArgumentConstructor() {
        return IsValidNoArgumentConstructor.hasNoArgumentConstructor();
    }


    // DATE

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>year</code>.
     */
    public static Matcher<Date> hasYear(final int year) {
        return IsDate.hasYear(year);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>id</code>.
     */
    public static Matcher<Date> hasMonth(final int month) {
        return IsDate.hasMonth(month);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>day</code>.
     */
    public static Matcher<Date> hasDay(final int day) {
        return IsDate.hasDay(day);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>year</code> and
     * <code>id</code>.
     */
    public static Matcher<Date> hasYearAndMonth(final int year, final int month) {
        return IsDate.hasYearAndMonth(year, month);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>year</code>, <code>
     * id</code> and <code>day</code>.
     */
    public static Matcher<Date> hasYearMonthAndDay(final int year, final int month, final int day) {
        return IsDate.hasYearMonthAndDay(year, month, day);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>hour</code> in a 24 hours
     * clock period.
     */
    public static Matcher<Date> hasHour(final int hour) {
        return IsDateWithTime.hasHour(hour);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> and
     * <code>ClockPeriod</code> (e.g. <em>AM</em>).
     */
    public static Matcher<Date> hasHour(final int hour, final ClockPeriod clockPeriod) {
        return IsDateWithTime.hasHour(hour, clockPeriod);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>minute</code>.
     */
    public static Matcher<Date> hasMinute(final int minute) {
        return IsDateWithTime.hasMinute(minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>sec</code>.
     */
    public static Matcher<Date> hasSecond(final int second) {
        return IsDateWithTime.hasSecond(second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given <code>millis</code>.
     */
    public static Matcher<Date> hasMillisecond(final int millisecond) {
        return IsDateWithTime.hasMillisecond(millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final int minute) {
        return IsDateWithTime.hasHourAndMin(hour, minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>) and <code>minute</code>.
     */
    public static Matcher<Date> hasHourAndMin(final int hour, final ClockPeriod clockPeriod, final int minute) {
        return IsDateWithTime.hasHourAndMin(hour, clockPeriod, minute);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period, <code>minute</code> and <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final int hour, final int minute, final int second) {
        return IsDateWithTime.hasHourMinAndSec(hour, minute, second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code> and <code>sec</code>.
     */
    public static Matcher<Date> hasHourMinAndSec(final int hour, final ClockPeriod clockPeriod, final int minute,
                                                 final int second) {
        return IsDateWithTime.hasHourMinAndSec(hour, clockPeriod, minute, second);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code> in a 24
     * hours clock period, <code>minute</code>, <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final int hour, final int minute, final int second,
                                                       final int millisecond) {
        return IsDateWithTime.hasHourMinSecAndMillis(hour, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} has the given values <code>hour</code>, <code>
     * ClockPeriod</code> (e.g. <em>AM</em>), <code>minute</code>, <code>sec</code> and <code>millis</code>.
     */
    public static Matcher<Date> hasHourMinSecAndMillis(final int hour, final ClockPeriod clockPeriod, final int minute,
                                                       final int second, final int millisecond) {
        return IsDateWithTime.hasHourMinSecAndMillis(hour, clockPeriod, minute, second, millisecond);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} is in the given weekOfYear of the year.
     */
    public static Matcher<Date> inWeekOfYear(final int week) {
        return IsDateInWeekOfYear.inWeekOfYear(week);
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a leap year.
     */
    public static Matcher<Date> leapYear() {
        return IsDateInLeapYear.leapYear();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>January</em>.
     */
    public static Matcher<Date> january() {
        return IsDateInMonth.january();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>February</em>.
     */
    public static Matcher<Date> february() {
        return IsDateInMonth.february();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>March</em>.
     */
    public static Matcher<Date> march() {
        return IsDateInMonth.march();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>April</em>.
     */
    public static Matcher<Date> april() {
        return IsDateInMonth.april();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>May</em>.
     */
    public static Matcher<Date> may() {
        return IsDateInMonth.may();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>June</em>.
     */
    public static Matcher<Date> june() {
        return IsDateInMonth.june();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>July</em>.
     */
    public static Matcher<Date> july() {
        return IsDateInMonth.july();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>August</em>.
     */
    public static Matcher<Date> august() {
        return IsDateInMonth.august();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>September</em>.
     */
    public static Matcher<Date> september() {
        return IsDateInMonth.september();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>October</em>.
     */
    public static Matcher<Date> october() {
        return IsDateInMonth.october();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>November</em>.
     */
    public static Matcher<Date> november() {
        return IsDateInMonth.november();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents the id <em>December</em>.
     */
    public static Matcher<Date> december() {
        return IsDateInMonth.december();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Sunday</em>.
     */
    public static Matcher<Date> sunday() {
        return IsDateInDay.sunday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Monday</em>.
     */
    public static Matcher<Date> monday() {
        return IsDateInDay.monday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Tuesday</em>.
     */
    public static Matcher<Date> tuesday() {
        return IsDateInDay.tuesday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Wednesday</em>.
     */
    public static Matcher<Date> wednesday() {
        return IsDateInDay.wednesday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Thursday</em>.
     */
    public static Matcher<Date> thursday() {
        return IsDateInDay.thursday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Friday</em>.
     */
    public static Matcher<Date> friday() {
        return IsDateInDay.friday();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Date} represents a <em>Saturday</em>.
     */
    public static Matcher<Date> saturday() {
        return IsDateInDay.saturday();
    }

    // ITERABLE

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} has no items.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(new ArrayList<>(), empty())</pre>
     *
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<? extends E>> empty() {
        return IsEmptyIterable.empty();
    }

    /**
     * <p>Creates an order agnostic matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, each logically equal to one item anywhere in the specified items.
     * For a positive match, the examined iterable must be of the same length as the number of specified items.</p>
     * <p>
     * <p>N.B. each of the specified items will only be used once during a given examination, so be careful when
     * specifying items that may be equal to more than one entry in an examined iterable. For example:</p>
     * <br />
     * <p>
     * <pre>
     * //Arrange
     * Iterable<String> actual = Arrays.asList("foo", "bar");
     * Iterable<String> expected = Arrays.asList("bar", "foo");
     *
     * //Assert
     * assertThat(actual, containsInAnyOrder(expected));
     * </pre>
     *
     * @param items the items that must equal the items provided by an examined {@linkplain Iterable} in any order
     */
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final Iterable<T> items) {
        return IsIterableContainingInAnyOrder.containsInAnyOrder(items);
    }

    /**
     * Creates a matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, each logically equal to the corresponding item in the specified
     * items. For a positive match, the examined iterable must be of the same length as the number of specified items.
     * <p>
     * <p>For example:
     * <p>
     * <pre>
     * //Arrange
     * Iterable<String> actual = Arrays.asList("foo", "bar");
     * Iterable<String> expected = Arrays.asList("foo", "bar");
     *
     * //Assert
     * assertThat(actual, containsInOrder(expected));
     * </pre>
     *
     * @param items the items that must equal the items provided by an examined {@linkplain Iterable}
     */
    public static <T> Matcher<Iterable<? extends T>> containsInOrder(final Iterable<T> items) {
        return IsIterableContainingInOrder.containsInOrder(items);
    }

    /**
     * Creates a matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, that contains items logically equal to the corresponding item in
     * the specified items, in the same relative order For example:<br />
     * <p>
     * <p>For example:
     * <p>
     * <pre>
     * //Arrange
     * Iterable<String> actual = Arrays.asList("a", "b", "c", "d");
     * Iterable<String> expected = Arrays.asList("a", "c");
     *
     * //Assert
     * assertThat(actual, containsInRelativeOrder(expected));
     * </pre>
     *
     * @param items the items that must be contained within items provided by an examined {@linkplain Iterable} in the
     *              same relative order
     */
    public static <T> Matcher<Iterable<? extends T>> containsInRelativeOrder(final Iterable<T> items) {
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder(items);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} has only distinct
     * elements.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(new ArrayList<>(), empty())</pre>
     *
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<? extends E>> hasDistinctElements() {
        return IsIterableWithDistinctElements.hasDistinctElements();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>1</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeOne())</pre>
     *
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeOne() {
        return IsIterableWithSize.hasSizeOne();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>2</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeTwo())</pre>
     *
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeTwo() {
        return IsIterableWithSize.hasSizeTwo();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>3</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeThree())</pre>
     *
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeThree() {
        return IsIterableWithSize.hasSizeThree();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>4</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFour())</pre>
     *
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeFour() {
        return IsIterableWithSize.hasSizeFour();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>5</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFive())</pre>
     *
     * returns <code>false</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSizeFive() {
        return IsIterableWithSize.hasSizeFive();
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when the examined {@link Iterable} yields an item count
     * equal to <code>size</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSize(2))</pre>
     *
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<E>> hasSize(final int size) {
        return IsIterableWithSize.hasSize(size);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements of type
     * <code>K</code> that extends {@linkplain Comparable} and the elements are sorted according to the natural
     * ordering.
     */
    public static <K> Matcher<Iterable<K>> sorted() {
        return IsSortedIterable.sorted();
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements of type
     * <code>K</code> that extends {@linkplain Comparable} and the elements are sorted according to the inverse of
     * natural ordering.
     */
    public static <K> Matcher<Iterable<K>> sortedReversed() {
        return IsSortedIterable.sortedReversed();
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements sorted
     * according to the given {@linkplain Comparator}.
     */
    public static <K> Matcher<Iterable<K>> sorted(final Comparator<K> comparator) {
        return IsSortedIterableWithComparator.sorted(comparator);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements sorted
     * according to the given {@linkplain Comparator}, but in a reverse order.
     */
    public static <K> Matcher<Iterable<K>> sortedReversed(final Comparator<K> comparator) {
        return IsSortedIterableWithComparator.sortedReversed(comparator);
    }

    // MAP

    /**
     * Creates a matcher for {@link Map}s matching when the examined {@link Map} has exactly the same key set of the
     * given map. For example:
     * <p>
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     */
    public static <K> Matcher<Map<? extends K, ?>> hasSameKeySet(final Map<? extends K, ?> comparisonMap) {
        return IsMapWithSameKeySet.hasSameKeySet(comparisonMap);
    }

    // FILE TYPE

    /**
     * Creates a matcher for {@code T}s that matches when the {@code toString()} method of the given object returns a
     * valid yaml address.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat("this.is.valid.yaml: YES", yaml())</pre>
     */
    public static <T> Matcher<T> yaml() {
        return IsYaml.yaml();
    }

    // NUMBER

    /**
     * Creates a matcher for {@code N} that matches when the it has a value that is greater than zero.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(100, positive())</pre>
     *
     * will return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> positive() {
        return IsPositive.positive();
    }

    /**
     * Creates a matcher for {@code N} that matches when the it has a value that is less than zero.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(-100, negative())</pre>
     *
     * will return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> negative() {
        return IsNegative.negative();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double} or {@linkplain Float} with
     * value equal to either <code>POSITIVE_INFINITY</code> or <code>NEGATIVE_INFINITY</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(10, negativeInfinity())</pre>
     *
     * will return <code>false</code>. while:
     *
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     *
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     *
     * will both return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> infinity() {
        return IsInfinity.infinity();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double} or {@linkplain Float} with
     * value equal to <code>NEGATIVE_INFINITY</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     *
     * will return <code>true</code>. while:
     *
     * <pre>assertThat(10, negativeInfinity())</pre>
     *
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     *
     * will both return <code>false</code>.
     */
    public static <N extends Number> Matcher<N> negativeInfinity() {
        return IsNegativeInfinity.negativeInfinity();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double} or {@linkplain Float} with
     * value equal to <code>POSITIVE_INFINITY</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat(Double.PositiveInfinity, positiveInfinity())</pre>
     *
     * will return <code>true</code>. while:
     *
     * <pre>assertThat(10, positiveInfinity())</pre>
     *
     * <pre>assertThat(Float.NegativeInfinity, positiveInfinity())</pre>
     *
     * will both return <code>false</code>.
     */
    public static <N extends Number> Matcher<N> positiveInfinity() {
        return IsPositiveInfinity.positiveInfinity();
    }

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double} or {@linkplain Float} such
     * that a call to method <code>isNaN()</code> returns <code>true</code>.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat((1.0/.0D), notANumber())</pre>
     *
     * will return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> notANumber() {
        return IsNotANumber.notANumber();
    }

    // Web

    /**
     * Creates a matcher for {@code T}s that matches when the {@code toString()} method of the given object returns a
     * valid email address.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat("john.doe@test.test", email())</pre>
     */
    public static <T> Matcher<T> email() {
        return IsEmail.email();
    }

}
