package it.ozimov.cirneco.hamcrest.collect;

import it.ozimov.cirneco.hamcrest.collect.utils.IterableUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Iterator;

import static it.ozimov.cirneco.hamcrest.collect.utils.IterableUtils.listCopy;
import static it.ozimov.cirneco.hamcrest.collect.utils.IterableUtils.sortedListCopy;

/**
 * Is the {@linkplain Iterable} sorted according to natural ordering?
 *
 * @since version 0.1 for JDK7
 */
public class IsSortedIterable<K extends Comparable> extends TypeSafeMatcher<Iterable<K>> {

    private final boolean reversed;

    public IsSortedIterable(final boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements of type
     * <code>K</code> that extends {@linkplain Comparable} and the elements are sorted according to the natural
     * ordering.
     */
    public static <K> Matcher<Iterable<K>> sorted() {
        return new IsSortedIterable(false);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has the elements of type
     * <code>K</code> that extends {@linkplain Comparable} and the elements are sorted according to the inverse of
     * natural ordering.
     */
    public static <K> Matcher<Iterable<K>> sortedReversed() {
        return new IsSortedIterable(true);
    }

    @Override
    public boolean matchesSafely(final Iterable<K> actual) {
        final Iterator<K> iterator = actual.iterator();

        K previous = null;

        while (iterator.hasNext()) {
            final K current = iterator.next();

            if ((previous != null)
                    && ((!reversed && (previous.compareTo(current) >= 0))
                    || (reversed && (previous.compareTo(current) <= 0)))) {
                return false;
            }

            previous = current;
        }

        return true;
    }

    @Override
    public void describeMismatchSafely(final Iterable<K> iterable, final Description mismatchDescription) {
        mismatchDescription.appendText("iterable was ").appendValueList("[", ", ", "]", listCopy(iterable))
                .appendText(", while expected ordering was ").appendValueList("[", ", ", "]",
                reversed ? IterableUtils.sortedReversedListCopy(iterable) : sortedListCopy(iterable));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("iterable sorted according to natural ordering");

        if (reversed) {
            description.appendText(" in reverse order");
        }
    }

}
