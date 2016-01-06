package it.ozimov.cirneco.hamcrest.guava.base;

import com.google.common.base.Equivalence;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value equivalent to another value as specified by the provided {@linkplain Equivalence}?
 *
 * @since version 0.1 for JDK7
 */
public class IsEquivalent<T> extends TypeSafeMatcher<T> {

    private final T comparison;

    private final Equivalence<T> equivalence;

    /**
     * Creates an instance of the class. <code>comparison</code> can be null,
     * while <code>equivalence</code> cannot.
     */
    public IsEquivalent(final T comparison, final Equivalence<T> equivalence) {
        Preconditions.checkNotNull(equivalence);

        this.comparison = comparison;
        this.equivalence = equivalence;
    }

    /**
     * Creates a matcher that matches when the examined object of type <code>T</code>
     * is equivalent to the specified <code>comparison</code> object according to
     * the provided {@linkplain Equivalence}.
     * <p/>
     * <p>Observe that the {@linkplain Equivalence} can deal with nulls.</p>
     */
    public static <T> Matcher<T> equivalentTo(final T expected,
        final Equivalence<T> equivalence) {
        return new IsEquivalent<>(expected, equivalence);
    }

    @Override protected boolean matchesSafely(final T actual) {
        return equivalence.equivalent(actual, comparison);
    }

    @Override protected void describeMismatchSafely(final T item,
        final Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(
            " is not equivalent to ");

        if (comparison == null) {
            mismatchDescription.appendText("null value");
        } else {
            mismatchDescription.appendValue(comparison);
        }

        mismatchDescription.appendText(" according with Equivalence ")
            .appendValue(equivalence);
    }

    @Override public void describeTo(final Description description) {
        description.appendText("a value equivalent to ").appendValue(comparison)
            .appendText(" according with Equivalence ").appendValue(
            equivalence);
    }

}
