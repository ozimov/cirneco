package org.cirneco.assertions.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.Matchers.allOf;


/**
 * Is the value a number between two numbers, bounds inclusive?
 */
public class IsBetweenInclusive<T extends Comparable<T>> extends TypeSafeMatcher<T> {

    private final T from;
    private final T to;

    public IsBetweenInclusive(final T from, final T to){
        this.from =from;
        this.to = to;
    }

    @Override
    protected boolean matchesSafely(final T t) {
        return t.compareTo(from) >= 0 && t.compareTo(to) <= 0;
    }

    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not between ")
                .appendValue(from)
                .appendText(" and ")
                .appendValue(to)
                .appendText(", both included");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value between ")
                .appendValue(from)
                .appendText(" and ")
                .appendValue(to)
                .appendText(", both included");
    }

    /**
     * Creates a matcher for {@code T}s that matches when the <code>compareTo()</code> method returns
     * a value between <code>from</code> and <code>to</code>, both included.
     * <p>
     *     For example:
     *      <pre>assertThat(10, betweenInclusive(10, 11))</pre>
     *      will return true.
     */
    public static <T extends Comparable<T>> Matcher<T> betweenInclusive(final T from, final T to){
        return new IsBetweenInclusive(from, to);
    }

}