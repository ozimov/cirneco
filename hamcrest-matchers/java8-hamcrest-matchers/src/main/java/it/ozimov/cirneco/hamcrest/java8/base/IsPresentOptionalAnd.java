package it.ozimov.cirneco.hamcrest.java8.base;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 * Is the given {@linkplain Optional} present and matching the given matcher?
 *
 * @since version 0.8.0 for JDK8
 */
public class IsPresentOptionalAnd<T> extends TypeSafeMatcher<Optional<T>> {

    private final Matcher<? super T> innerMatcher;

    private IsPresentOptionalAnd(final Matcher<? super T> innerMatcher) {
        this.innerMatcher = innerMatcher;
    }


    /**
     * Creates a matcher from an inner matcher for {@linkplain Optional}s which are present.
     */
    public static <T> TypeSafeMatcher<Optional<T>> presentAnd(final Matcher<? super T> innerMatcher) {
        return new IsPresentOptionalAnd<>(innerMatcher);
    }

    @Override
    protected boolean matchesSafely(final Optional<T> optionalValue) {
        return optionalValue.map(innerMatcher::matches).orElse(false);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("is present and ");
        description.appendDescriptionOf(innerMatcher);
    }

    @Override
    protected void describeMismatchSafely(final Optional<T> item, final Description mismatchDescription) {
        if (!item.isPresent()) {
            mismatchDescription.appendText(" is not present");
        } else {
            mismatchDescription.appendText(" is present, but ");
            innerMatcher.describeMismatch(item.get(), mismatchDescription);
        }
    }
}
