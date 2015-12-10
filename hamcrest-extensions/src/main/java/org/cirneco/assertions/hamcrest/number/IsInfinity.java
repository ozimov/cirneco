package org.cirneco.assertions.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value a number with infinite value?
 */
public class IsInfinity<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to either <code>POSITIVE_INFINITY</code> or <code>NEGATIVE_INFINITY</code>.
     * <p/>
     * For example:
     * <pre>assertThat(10, negativeInfinity())</pre>
     * will return false.
     * while:
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     * will both return true.
     */
    public static <T extends Number> Matcher<T> infinity() {
        return new IsInfinity<>();
    }

    @Override
    protected boolean matchesSafely(final N number) {
        if (number instanceof Double) {
            return ((Double) number).isInfinite();
        } else if (number instanceof Float) {
            return ((Float) number).isInfinite();
        }
        return false;
    }

    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not negativeInfinity");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to negativeInfinity");
    }

}