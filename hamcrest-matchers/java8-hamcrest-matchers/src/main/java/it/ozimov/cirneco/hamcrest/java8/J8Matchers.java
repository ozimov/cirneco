package it.ozimov.cirneco.hamcrest.java8;

import it.ozimov.cirneco.hamcrest.java7.J7Matchers;
import it.ozimov.cirneco.hamcrest.java8.base.IsEmptyOptional;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 * The {@code J8Matchers} class groups all the matchers from {J7Matchers} plus the new ones introduced by Cirneco's
 * Hamcrest extension for Java 8. Suggested use would be to import all the static methods of this class in a unit tes.
 *
 * @since version 0.1 for JDK8
 */
public class J8Matchers extends J7Matchers {

    // BASE

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> emptyOptional() {
        return IsEmptyOptional.emptyOptional();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> notPresent() {
        return IsEmptyOptional.emptyOptional();
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> present() {
        return Matchers.not(IsEmptyOptional.emptyOptional());
    }

    /**
     * Creates a matcher from an inner matcher for {@linkplain Optional}s which are present.
     */
    public static <T> TypeSafeMatcher<Optional<T>> presentAnd(final Matcher<? super T> innerMatcher) {
        return new OptionalMatcher<>(innerMatcher);
    }

    private static class OptionalMatcher<T> extends TypeSafeMatcher<Optional<T>> {
        Matcher<? super T> innerMatcher;

        public OptionalMatcher(final Matcher<? super T> innerMatcher) {
            this.innerMatcher = innerMatcher;
        }

        @Override
        protected boolean matchesSafely(final Optional<T> optionalValue) {
            return optionalValue.map(innerMatcher::matches).orElse(false);
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("is present with ");
            description.appendDescriptionOf(innerMatcher);
        }

        @Override
        protected void describeMismatchSafely(final Optional<T> item, final Description mismatchDescription) {
            if (!item.isPresent()) {
                mismatchDescription.appendText("is not present");
            } else {
                mismatchDescription.appendText("is present, but ");
                innerMatcher.describeMismatch(item.get(), mismatchDescription);
            }
        }
    }
}
