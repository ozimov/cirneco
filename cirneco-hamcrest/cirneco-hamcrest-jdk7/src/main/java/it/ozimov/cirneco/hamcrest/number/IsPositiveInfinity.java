package it.ozimov.cirneco.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value a number with positive infinite value?
 *
 * @since 0.1
 */
public class IsPositiveInfinity<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code N} that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} with value equal to <code>POSITIVE_INFINITY</code>.
     * <p>
     * For example:
     * <pre>assertThat(Double.PositiveInfinity, positiveInfinity())</pre>
     * will return <code>true</code>.
     * while:
     * <pre>assertThat(10, positiveInfinity())</pre>
     * <pre>assertThat(Float.NegativeInfinity, positiveInfinity())</pre>
     * will both return <code>false</code>.
     */
    public static <N extends Number> Matcher<N> positiveInfinity() {
        return new IsPositiveInfinity<>();
    }

    @Override
    protected boolean matchesSafely(final N number) {
        if (number instanceof Double) {
            return ((Double) number).compareTo(Double.POSITIVE_INFINITY) == 0;
        } else if (number instanceof Float) {
            return ((Float) number).compareTo(Float.POSITIVE_INFINITY) == 0;
        }
        return false;
    }

    @Override
    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not positive notANumber");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to positive notANumber");
    }

}