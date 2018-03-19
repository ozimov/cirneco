package it.ozimov.cirneco.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static java.lang.String.format;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseMatcherTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public static <T> void assertMatches(final String message, final boolean matches) {
        assertTrue(message, matches);
    }

    public static <T> void assertMatches(final boolean matches) {
        assertMatches("Expected to match, but mismatched", matches);
    }

    public static <T> void assertDoesNotMatch(final String message, final boolean matches) {
        assertFalse(message, matches);
    }

    public static <T> void assertDoesNotMatch(final boolean matches) {
        assertDoesNotMatch("Unexpected matching", matches);
    }

    public static void assertIsDescribedTo(final String expectedDescription, final Matcher<?> matcher) {

        // Act
        final String actualDescription = descriptionFromMatcher(matcher);

        // Assert
        assertThat("Expected description does not match the one returned by the Matcher " + getSimpleName(matcher),
                expectedDescription, equalTo(actualDescription));
    }

    public static <T> void assertHasMismatchDescription(final String expectedDescription,
                                                        final Matcher<? super T> matcher, final T matcherArgument) {

        // Assert
        assertThat(format("The given matcher has no mismatch with value \"%s\"", matcherArgument),
                matcher.matches(matcherArgument), is(false));

        // Act
        final String actualMismatchDescription = mismatchDescriptionForMatcherFromArgument(matcher, matcherArgument);

        // Assert
        assertThat("Expected mismatch description does not match the one returned by the Matcher "
                + getSimpleName(matcher), expectedDescription, equalTo(actualMismatchDescription));
    }

    private static String descriptionFromMatcher(final Matcher<?> matcher) {
        return new StringDescription().appendDescriptionOf(matcher).toString().trim();
    }

    protected static <T> String mismatchDescriptionForMatcherFromArgument(final Matcher<? super T> matcher,
                                                                          final T arg) {
        final Description description = new StringDescription();
        matcher.describeMismatch(arg, description);

        return description.toString().trim();
    }

    private static String getSimpleName(final Matcher<?> matcher) {
        return matcher.getClass().getSimpleName();
    }

}
