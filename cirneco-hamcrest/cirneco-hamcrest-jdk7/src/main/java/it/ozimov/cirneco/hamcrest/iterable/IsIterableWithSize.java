package it.ozimov.cirneco.hamcrest.iterable;

import it.ozimov.cirneco.hamcrest.iterable.utils.IterableUtils;
import org.hamcrest.Matcher;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Does the {@linkplain Iterable} has a given size?
 * <p>
 * The matcher first checks if the given {@code Iterable} is a
 * {@linkplain Collection} (to get some speedup by using the {@linkplain Collection#size()} method, otherwise iterates
 * all the elements to get the size of the {@code Iterable}.
 *
 * @since version 0.1 for JDK7
 */
public class IsIterableWithSize<E> extends org.hamcrest.collection.IsIterableWithSize<E> {

    /**
     * Creates an instance of the matcher.
     */
    public IsIterableWithSize(final int size) {
        super(equalTo(size));
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
        return hasSize(1);
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
        return hasSize(2);
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
        return hasSize(3);
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
        return hasSize(4);
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
        return hasSize(5);
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
        checkArgument(size >= 0, "Size cannot be negative");

        return new IsIterableWithSize(size);
    }

    @Override
    protected Integer featureValueOf(final Iterable<E> actual) {
        return IterableUtils.size(actual);
    }

}