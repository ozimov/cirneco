package it.ozimov.cirneco.hamcrest.collect;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Is the {@linkplain Iterable} with distinct elements?
 * <p>To verify that only distinct elements are in the {@code Iterable},
 *
 * @since version 0.2 for JDK7
 */
public class IsIterableWithDistinctElements<E> extends TypeSafeMatcher<Iterable<? extends E>> {

    /**
     * Creates a matcher for {@linkplain Iterable}s that matches when the examined {@linkplain Iterable} has only
     * distinct elements.
     * <p>For example:
     * <pre>assertThat(Arrays.asList(1, 2, 3), hasDistinctElements())</pre>
     *
     * returns <code>true</code>.
     */
    public static <E> Matcher<Iterable<? extends E>> hasDistinctElements() {
        return new IsIterableWithDistinctElements<>();
    }

    @Override
    public boolean matchesSafely(final Iterable<? extends E> actual) {
        final Set<E> elements = new HashSet<>();

        for (final E element : actual) {
            if (!elements.add(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeMismatchSafely(final Iterable<? extends E> actual, final Description mismatchDescription) {
        final Set<E> elements = new HashSet<>();
        final Collection<E> nonDistinctElements = new ArrayList<>(); // keep actual's order for reporting mismatch

        for (final E element : actual) {
            if (!elements.add(element)) {
                nonDistinctElements.add(element);
            }
        }

        mismatchDescription.appendText(" non distinct elements are ").appendValueList("[", ", ", "]",
                nonDistinctElements);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("an iterable with all distinct elements");
    }
}
