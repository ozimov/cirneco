package org.cirneco.assertions.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value a number between two numbers, lower bound included?
 */
public class IsBetweenLowerBoundInclusive<T extends Comparable<T>> extends TypeSafeMatcher<T> {

    private final T from;
    private final T to;

    public IsBetweenLowerBoundInclusive(final T from, final T to){
        this.from =from;
        this.to = to;
    }

    @Override
    protected boolean matchesSafely(final T t) {
        return t.compareTo(from) >= 0 && t.compareTo(to) < 0;
    }

    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not between ")
                .appendValue(from)
                .appendText(" included and ")
                .appendValue(to)
                .appendText(", excluded");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value between ")
                .appendValue(from)
                .appendText(" included and ")
                .appendValue(to)
                .appendText(" excluded");
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     *     For example:
     *      <pre>assertThat(10, betweenLowerBoundInclusive(10, 11))</pre>
     *      will return true.
     *     while:
     *      <pre>assertThat(11, betweenLowerBoundInclusive(10, 11))</pre>
     *      will return false.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenLowerBoundInclusive(final T from, final T to){
        return new IsBetweenLowerBoundInclusive(from, to);
    }

}