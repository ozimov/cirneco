package it.ozimov.cirneco.hamcrest.java7.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Is the value a number with negative infinite value?
 *
 * @since version 0.1 for JDK7
 */
public class IsNegativeInfinity<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code N} that matches when the number is a {@linkplain Double} or {@linkplain Float} with
     * value equal to <code>NEGATIVE_INFINITY</code>.
     * <p>For example:
     * <pre>assertThat(Float.NegativeInfinity, negativeInfinity())</pre>
     *
     * will return <code>true</code>. while:
     *
     * <pre>assertThat(10, negativeInfinity())</pre>
     *
     * <pre>assertThat(Double.PositiveInfinity, negativeInfinity())</pre>
     *
     * will both return <code>false</code>.
     */
    public static <N extends Number> Matcher<N> negativeInfinity() {
        return new IsNegativeInfinity<>();
    }

    @Override
    protected boolean matchesSafely(final N number) {

        if (number instanceof Double) {
            return ((Double) number).compareTo(Double.NEGATIVE_INFINITY) == 0;
        } else if (number instanceof Float) {
            return ((Float) number).compareTo(Float.NEGATIVE_INFINITY) == 0;
        }

        return false;
    }

    @Override
    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" is not negative infinity");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to negative infinity");
    }

}
