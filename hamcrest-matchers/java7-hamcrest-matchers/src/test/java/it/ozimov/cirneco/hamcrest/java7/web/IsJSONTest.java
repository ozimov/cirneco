package it.ozimov.cirneco.hamcrest.java7.web;

import static java.lang.String.format;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsJSONTest extends BaseMatcherTest {

    private static final String[] VALID_JSON = {
        "{\"foo\": [\"bar\", \"baz\"]}", "{\"foo\": 100}", "{\"foo\": 100}", "{\"foo\": [100, 200]}"
    };
    private static final String[] INVALID_JSON = {
        "{\"foo\": [\"bar\", \"baz\",]}", "{'foo': 100}", "{\"foo\": 100", "{\"foo\": [100, 200}"
    };

    public Matcher<String> isJSONMatcher;

    @Before
    public void setUp() {

        // Arrange
        isJSONMatcher = IsJSON.validJSON();
    }

    @Test
    public void testValidJson() throws Exception {
        for (final String json : VALID_JSON) {
            assertMatches(json);
        }
    }

    @Test
    public void testInvalidJson() throws Exception {
        for (final String json : INVALID_JSON) {
            assertDoesNotMatch(json);
        }
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {

        for (final String json : INVALID_JSON) {
            assertHasMismatchDescription(format("<%s> is not a valid JSON string", json), isJSONMatcher, json);
        }
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value that is a valid JSON", isJSONMatcher);

    }

    private void assertMatches(final String json) {

        // Act
        final boolean matches = isJSONMatcher.matches(json);

        // Arrange
        assertMatches(json + " is not a valid JSON", matches);
    }

    private void assertDoesNotMatch(final String json) {

        // Act
        final boolean matches = isJSONMatcher.matches(json);

        // Arrange
        assertDoesNotMatch(json + " is a valid JSON", matches);
    }
}
