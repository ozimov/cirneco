package it.ozimov.cirneco.hamcrest.java7.collect;

import static org.hamcrest.Matchers.equalTo;

import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.listCopy;

import java.util.ArrayList;
import java.util.Collection;

import org.hamcrest.Matcher;

/**
 * {@inheritDoc}
 */
public class IsIterableContainingInOrder<T> extends org.hamcrest.collection.IsIterableContainingInOrder<T> {

    public IsIterableContainingInOrder(final Iterable<Matcher<? super T>> matchers) {
        super(listCopy(matchers));
    }

    /**
     * Creates a matcher for {@linkplain Iterable}s that matches when a single pass over the examined
     * {@linkplain Iterable} yields a series of items, each logically equal to the corresponding item in the specified
     * items. For a positive match, the examined iterable must be of the same length as the number of specified items.
     * For example:<br />
     *
     * <p>
     * <p>
     * <pre>
       //Arrange
       Iterable<String> actual = Arrays.asList("foo", "bar");
       Iterable<String> expected = Arrays.asList("foo", "bar");

       //Assert
       assertThat(actual, containsInOrder(expected));
     * </pre>
     *
     * @param  items  the items that must equal the items provided by an examined {@linkplain Iterable}
     */
    public static <T> Matcher<Iterable<? extends T>> containsInOrder(final Iterable<T> items) {
        final Collection<Matcher<? super T>> matchers = new ArrayList<>();
        for (final T item : items) {
            matchers.add(equalTo(item));
        }

        return new IsIterableContainingInOrder<T>(matchers);
    }

}
