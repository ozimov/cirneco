package org.cirneco.assertions.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value a number between two numbers, upper bound included?
 */
public class IsBetweenUpperBoundInclusive<T extends Comparable<T>> extends TypeSafeMatcher<T> {

    private final T from;
    private final T to;

    public IsBetweenUpperBoundInclusive(final T from, final T to){
        this.from =from;
        this.to = to;
    }

    @Override
    protected boolean matchesSafely(final T t) {
        return t.compareTo(from) > 0 && t.compareTo(to) <= 0;
    }

    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not between ")
                .appendValue(from)
                .appendText(" excluded and ")
                .appendValue(to)
                .appendText(" included");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value between ")
                .appendValue(from)
                .appendText(" excluded and ")
                .appendValue(to)
                .appendText(" included");
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     *     For example:
     *      <pre>assertThat(11, betweenUpperBoundInclusive(10, 11))</pre>
     *      will return true.
     *     while:
     *      <pre>assertThat(10, betweenUpperBoundInclusive(10, 11))</pre>
     *      will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenUpperBoundInclusive(final T from, final T to){
        return new IsBetweenUpperBoundInclusive(from, to);
    }

}