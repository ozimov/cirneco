package org.cirneco.assertions.hamcrest.base;

import com.google.common.base.Equivalence;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value equivalent to another value as by a provided {@linkplain com.google.common.base.Equivalence}?
 */
public class IsEquivalent<T> extends TypeSafeMatcher<T> {

    private final T expected;

    private final Equivalence<T> equivalence;

    public IsEquivalent(final T expected, final Equivalence<T> equivalence) {
        this.expected = expected;
        this.equivalence = equivalence;
    }

    @Override
    protected boolean matchesSafely(final T actual) {
        return equivalence.equivalent(actual, expected);
    }

    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is equivalent");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendValue(expected);
    }


    /**
     * Creates a matcher that matches when the examined object of type <code>T</code>
     * is equivalent to the specified <code>expected</code> object according to
     * the provided {@linkplain Equivalence}.
     * <p/>
     * <p>Observe that the {@linkplain Equivalence} can deal with nulls.</p>
     */
    public static <T> Matcher<T> equivalentTo(final T expected, final Equivalence<T> equivalence) {
        return new IsEquivalent<T>(expected, equivalence);
    }

}