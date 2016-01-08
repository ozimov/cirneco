package it.ozimov.cirneco.hamcrest.java7.collect;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Collection;

import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.listCopy;
import static org.hamcrest.Matchers.equalTo;

/**
 * {@inheritDoc}
 */
public class IsIterableContainingInAnyOrder<T> extends org.hamcrest.collection.IsIterableContainingInAnyOrder<T> {

    public IsIterableContainingInAnyOrder(final Iterable<Matcher<? super T>> matchers) {
        super(listCopy(matchers));
    }

    /**
     * <p>Creates an order agnostic matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, each logically equal to one item anywhere in the specified items.
     * For a positive match, the examined iterable must be of the same length as the number of specified items.</p>
     * <p>N.B. each of the specified items will only be used once during a given examination, so be careful when
     * specifying items that may be equal to more than one entry in an examined iterable. For example:</p>
     * <br />
     * <pre>
     *     //Arrange
     *     Iterable<String> actual = Arrays.asList("foo", "bar");
     *     Iterable<String> expected = Arrays.asList("bar", "foo");
     *
     *     //Assert
     *     assertThat(actual, containsInAnyOrder(expected));
     * </pre>
     *
     * @param items the items that must equal the items provided by an examined {@linkplain Iterable} in any order
     */
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final Iterable<T> items) {
        final Collection<Matcher<? super T>> matchers = new ArrayList<>();
        for (final T item : items) {
            matchers.add(equalTo(item));
        }

        return new IsIterableContainingInAnyOrder<T>(matchers);
    }

}
