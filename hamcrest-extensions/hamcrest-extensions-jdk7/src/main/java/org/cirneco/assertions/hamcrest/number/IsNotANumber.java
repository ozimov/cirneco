package org.cirneco.assertions.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the numeric value a number?
 *
 * @since 0.1
 */
public class IsNotANumber<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code T}s that matches when the number is a {@linkplain Double}
     * or {@linkplain Float} such that a call to method <code>isNaN()</code> returns true.
     * <p>
     * For example:
     * <pre>assertThat((1.0/.0D), notANumber())</pre>
     * will return true.
     */
    public static <T extends Number> Matcher<T> notANumber() {
        return new IsNotANumber<>();
    }

    @Override
    protected boolean matchesSafely(final N number) {
        if (number instanceof Double) {
            return ((Double) number).isNaN();
        } else if (number instanceof Float) {
            return ((Float) number).isNaN();
        }
        return false;
    }

    @Override
    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item)
                .appendText(" is not a number (i.e. Double.NaN or Float.NaN)");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText(" not a number (i.e. Double.NaN or Float.NaN)");
    }

}