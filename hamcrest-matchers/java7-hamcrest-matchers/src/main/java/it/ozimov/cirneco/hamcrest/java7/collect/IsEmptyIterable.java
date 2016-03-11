package it.ozimov.cirneco.hamcrest.java7.collect;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;

/**
 * Is the {@linkplain Iterable} empty?
 * <p>
 * <p>The matcher first checks if the given {@code Iterable} is a {@linkplain Collection} (to get some speedup by using
 * the {@linkplain Collection#isEmpty()}} method, otherwise get the iterator from the {@code Iterable} and check if has
 * a next item.
 *
 * @since version 0.1 for JDK7
 */
public class IsEmptyIterable<E> extends TypeSafeMatcher<Iterable<? extends E>> {

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
        return new IsEmptyIterable<>();
    }

    @Override
    public boolean matchesSafely(final Iterable<? extends E> actual) {

        if (actual instanceof Collection) {
            return ((Collection) actual).isEmpty();
        } else {
            return !actual.iterator().hasNext();
        }
    }

    @Override
    public void describeMismatchSafely(final Iterable<? extends E> actual, final Description mismatchDescription) {
        mismatchDescription.appendValueList("[", ", ", "]", actual);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("an empty iterable");
    }
}
