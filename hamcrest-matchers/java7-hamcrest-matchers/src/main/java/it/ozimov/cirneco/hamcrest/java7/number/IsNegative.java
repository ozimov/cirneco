package it.ozimov.cirneco.hamcrest.java7.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Is the value a {@linkplain Number} with negative value?
 *
 * @since  version 0.2 for JDK7
 */
public class IsNegative<N extends Number> extends TypeSafeMatcher<N> {

    /**
     * Creates a matcher for {@code N} that matches when the it has a value that is less than zero.
     *
     * <p>For example:
     *
     * <pre>assertThat(-100, negative())</pre>
     *
     * will return <code>true</code>.
     */
    public static <N extends Number> Matcher<N> negative() {
        return new IsNegative<>();
    }

    @Override
    protected boolean matchesSafely(final N number) {
        return number.doubleValue() < 0D;
    }

    @Override
    protected void describeMismatchSafely(final N item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" is not negative");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a negative value");
    }

}
