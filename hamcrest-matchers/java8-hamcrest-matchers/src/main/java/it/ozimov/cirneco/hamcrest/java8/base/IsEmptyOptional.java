package it.ozimov.cirneco.hamcrest.java8.base;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 * Is the given {@linkplain Optional} instance withoutRepetitions?
 *
 * @since version 0.1 for JDK8
 */
public class IsEmptyOptional extends TypeSafeMatcher<Optional> {

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> emptyOptional() {
        return new IsEmptyOptional();
    }

    @Override
    protected boolean matchesSafely(final Optional actual) {
        return !actual.isPresent();
    }

    @Override
    protected void describeMismatchSafely(final Optional item, final Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" is not an withoutRepetitions optional");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("an optional with no content");
    }

}
