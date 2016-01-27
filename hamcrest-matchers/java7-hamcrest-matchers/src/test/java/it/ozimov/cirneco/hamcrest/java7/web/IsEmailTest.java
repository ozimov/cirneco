package it.ozimov.cirneco.hamcrest.java7.web;

import static java.lang.String.format;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsEmailTest extends BaseMatcherTest {

    private static final Object[] VALID_EMAILS = {
        "email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com",
        "firstname+lastname@example.com", "perché@example.com", "あいうえお@example.com", "email@[123.123.123.123]",
        "\"email\"@example.com", "1234567890@example.com", "email@example-one.com", "email@example.name",
        "email@example.museum", "email@example.co.jp", "firstname-lastname@example.com",
        "much.\"more\\ unusual\"@example.com", "very.unusual.\"@\".unusual.com@example.com",
        "very.\"(),:;<>[]\".VERY.\"very@\\ ”very\".unusual@strange.example.com"
    };

    private static final String[] NON_VALID_EMAILS = {
        "plainaddress", "#@%^%#$@#$@#.com", "@example.com", "Joe Smith <email@example.com>", "email.example.com",
        "email@example@example.com", ".email@example.com", "email.@example.com", "email..email@example.com",
        "email@example.com (Joe Smith)", "email@example", "email@-example.com", "email@example.web",
        "email@111.222.333.44444", "email@example..com", "Abc..123@example.com", "\"(),:;<>[\\]@example.com",
        "just\"not\"right@example.com", "this\\ is\"really\"not\\allowed@example.com"
    };

    public Matcher<Object> isEmailMatcher;

    @Before
    public void setUp() {

        // Arrange
        isEmailMatcher = IsEmail.email();
    }

    @Test
    public void testGivenValidEmailAddressWhenMatchesIsCalledThenReturnTrue() throws Exception {

        for (final Object email : VALID_EMAILS) {
            assertMatches(email.toString());
        }
    }

    @Test
    public void testGivenNonValidEmailAddressWhenMatchesIsCalledThenReturnFalse() throws Exception {

        for (final Object email : NON_VALID_EMAILS) {
            assertDoesNotMatch(email.toString());
        }
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {

        for (final Object email : NON_VALID_EMAILS) {
            assertHasMismatchDescription(format("<%s> is not a valid email address", email), isEmailMatcher, email);
        }
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to a valid email address", isEmailMatcher);

    }

    private void assertMatches(final String email) {

        // Act
        final boolean matches = isEmailMatcher.matches(email);

        // Arrange
        assertMatches(email + " is not a valid email address", matches);
    }

    private void assertDoesNotMatch(final String email) {

        // Act
        final boolean matches = isEmailMatcher.matches(email);

        // Arrange
        assertDoesNotMatch(email + " is a valid email address", matches);
    }

}
