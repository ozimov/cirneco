package org.cirneco.assertions.hamcrest.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseMatcherTest {

    public static void assertIsDescribedTo(final String expectedDescription, final Matcher<?> matcher) {
        //Act
        final String actualDescription = descriptionFromMatcher(matcher);

        //Assert
        assertThat("Expected description does not match the one returned by the Matcher " + getSimpleName(matcher),
                expectedDescription, equalTo(actualDescription));
    }

    public static <T> void assertHasMismatchDescription(final String expectedDescription,
                                                        final Matcher<? super T> matcher,
                                                        final T matcherArgument) {
        //Assert
        assertThat(format("The given matcher has no mismatch with value %s", matcherArgument),
                matcher.matches(matcherArgument), is(false));

        //Act
        final String actualMismatchDescription = mismatchDescriptionForMatcherFromArgument(matcher, matcherArgument);

        //Assert
        assertThat("Expected mismatch description does not match the one returned by the Matcher " + getSimpleName(matcher),
                expectedDescription, equalTo(actualMismatchDescription));
    }

    private static String descriptionFromMatcher(final Matcher<?> matcher) {
        return new StringDescription().appendDescriptionOf(matcher).toString().trim();
    }

    protected static <T> String mismatchDescriptionForMatcherFromArgument(final Matcher<? super T> matcher, final T arg) {
        final Description description = new StringDescription();
        matcher.describeMismatch(arg, description);
        return description.toString().trim();
    }

    private static String getSimpleName(final Matcher<?> matcher) {
        return matcher.getClass().getSimpleName();
    }

}