package org.cirneco.assertions.hamcrest.collection;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableWithSize;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Does the {@linkplain Iterable} has a given size?
 */
public class IsIterableWithFixedSize<E> extends IsIterableWithSize<E> {

    public IsIterableWithFixedSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass over the
     * examined {@link Iterable} yields an item count equal to <code>1</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), iterableWithSizeOne())</pre>
     */
    public static <E> Matcher<Iterable<? extends E>> iterableWithSizeOne() {
        return withSize(1);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass over the
     * examined {@link Iterable} yields an item count equal to <code>2</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), iterableWithSizeTwo())</pre>
     */
    public static <E> Matcher<Iterable<? extends E>> iterableWithSizeTwo() {
        return withSize(2);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass over the
     * examined {@link Iterable} yields an item count equal to <code>3</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), iterableWithSizeThree())</pre>
     */
    public static <E> Matcher<Iterable<? extends E>> iterableWithSizeThree() {
        return withSize(3);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass over the
     * examined {@link Iterable} yields an item count equal to <code>4</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), iterableWithSizeFour())</pre>
     */
    public static <E> Matcher<Iterable<? extends E>> iterableWithSizeFour() {
        return withSize(4);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass over the
     * examined {@link Iterable} yields an item count equal to <code>5</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), iterableWithSizeFive())</pre>
     */
    public static <E> Matcher<Iterable<? extends E>> iterableWithSizeFive() {
        return withSize(5);
    }

    static <E> Matcher<Iterable<? extends E>> withSize(final int size) {
        return new IsIterableWithFixedSize(equalTo(size));
    }

}