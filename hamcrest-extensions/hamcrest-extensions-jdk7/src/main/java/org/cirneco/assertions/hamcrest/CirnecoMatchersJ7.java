package org.cirneco.assertions.hamcrest;

import com.google.common.base.Equivalence;
import com.google.common.base.Optional;
import org.cirneco.assertions.hamcrest.base.IsEmptyGuavaOptional;
import org.cirneco.assertions.hamcrest.base.IsEquivalent;
import org.cirneco.assertions.hamcrest.collection.IsIterableWithSize;
import org.cirneco.assertions.hamcrest.collection.IsMapWithSameKeySet;
import org.cirneco.assertions.hamcrest.number.IsBetween;
import org.cirneco.assertions.hamcrest.number.IsBetweenInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenLowerBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenUpperBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsInfinity;
import org.cirneco.assertions.hamcrest.number.IsNegativeInfinity;
import org.cirneco.assertions.hamcrest.number.IsNotANumber;
import org.cirneco.assertions.hamcrest.number.IsPositiveInfinity;
import org.hamcrest.Matcher;

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

    //COLLECTION

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
     * Creates a matcher for {@link java.util.Map}s matching when the examined {@link java.util.Map} has exactly
     * the same key set of the given map.
     * For example:
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     */
    public static <K> Matcher<Map<? extends K, ?>> hasSameKeySet(final Map<? extends K, ?> comparisonMap) {
        return IsMapWithSameKeySet.hasSameKeySet(comparisonMap);
    }

    //DATE

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
