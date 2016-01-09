package it.ozimov.cirneco.hamcrest.java7.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Is the value a number between two numbers, lower bound included?
 *
 * @since  version 0.1 for JDK7
 */
public class IsBetweenLowerBoundInclusive<T extends Comparable<T>> extends TypeSafeMatcher<T> {

    private final T from;
    private final T to;

    /**
     * Creates and instance of the matcher. Observe that <code>from</code> and <code>to</code> cannot be null and <code>
     * from.compareTo(to)</code> must be negative.
     */
    public IsBetweenLowerBoundInclusive(final T from, final T to) {
        checkNotNull(from);
        checkNotNull(to);
        checkArgument(from.compareTo(to) < 0, "from must be before to");

        this.from = from;
        this.to = to;
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns a value between
     * <code>from</code> and <code>to</code>, both included.
     *
     * <p>
     * <p>
     * <p>
     * <p>For example:
     *
     * <p>
     * <p>
     * <p>
     * <pre>assertThat(10, betweenLowerBoundInclusive(10, 11))</pre>
     *
     * will return true. while:
     *
     * <pre>assertThat(11, betweenLowerBoundInclusive(10, 11))</pre>
     *
     * will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenLowerBoundInclusive(final T from, final T to) {
        return new IsBetweenLowerBoundInclusive(from, to);
    }

    @Override
    protected boolean matchesSafely(final T t) {
        return (t.compareTo(from) >= 0) && (t.compareTo(to) < 0);
    }

    @Override
    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" is not between ").appendValue(from)
                           .appendText(" included and ").appendValue(to).appendText(" excluded");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value between ").appendValue(from).appendText(" included and ").appendValue(to)
                   .appendText(" excluded");
    }

}
