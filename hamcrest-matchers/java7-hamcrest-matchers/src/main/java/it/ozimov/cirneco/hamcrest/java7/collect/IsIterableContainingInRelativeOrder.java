package it.ozimov.cirneco.hamcrest.java7.collect;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Collection;

import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.listCopy;
import static org.hamcrest.Matchers.equalTo;

/**
 * {@inheritDoc}
 */
public class IsIterableContainingInRelativeOrder<T>
        extends org.hamcrest.collection.IsIterableContainingInRelativeOrder<T> {

    public IsIterableContainingInRelativeOrder(final Iterable<Matcher<? super T>> matchers) {
        super(listCopy(matchers));
    }

    /**
     * Creates a matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, that contains items logically equal to the corresponding item in
     * the specified items, in the same relative order For example:<br />
     * <p>
     * <p>
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
        final Collection<Matcher<? super T>> matchers = new ArrayList<>();
        for (final T item : items) {
            matchers.add(equalTo(item));
        }

        return new IsIterableContainingInRelativeOrder<T>(matchers);
    }

}
