package it.ozimov.cirneco.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;


/**
 * Is the value a {@linkplain Number} with infinite value?
 * <p>
 * <p>
 * Observe that for Cirneco, only {@linkplain Double} and {@linkplain Float} admit an
 * infinite value according to the JDK 6 and superior (included {@linkplain BigDecimal}).
 * Any third party implementation of a <code>Number</code> is not handled.
 *
 * @since version 0.1 for JDK7
 */
public class IsInfinity<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code N} that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to either <code>POSITIVE_INFINITY</code> or <code>NEGATIVE_INFINITY</code>.
     * <p>
     * For example:
     * <pre>assertThat(10, negativeInfinity())</pre>
     * will return <code>false</code>.
     * while:
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     * will both return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> infinity() {
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

    @Override
    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not infinite (positive or negative)");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to infinite (positive or negative)");
    }

}