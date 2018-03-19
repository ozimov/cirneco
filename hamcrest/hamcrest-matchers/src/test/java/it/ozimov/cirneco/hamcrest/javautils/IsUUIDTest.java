package it.ozimov.cirneco.hamcrest.javautils;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.UUID;

public class IsUUIDTest extends BaseMatcherTest {

    public Matcher isUUIDMatcher;

    @Before
    public void setUp() {

        // Arrange
        isUUIDMatcher = IsUUID.UUID();
    }

    @Test
    public void testGivenAUUIDWhenMatchesIsCalledThenTrueIsReturened() throws Exception {

        // Arrange
        Object actual = UUID.nameUUIDFromBytes("Test".getBytes(Charset.forName("UTF-8")));

        // Act
        final boolean matches = isUUIDMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenAStringRepresentingUUIDWhenMatchesIsCalledThenTrueIsReturened() throws Exception {

        // Arrange
        String actual = UUID.nameUUIDFromBytes("Test".getBytes(Charset.forName("UTF-8"))).toString();

        // Act
        final boolean matches = isUUIDMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenNotAUUIDWhenMatchesIsCalledThenTrueIsReturened() throws Exception {

        // Arrange
        String actual = "TEST";

        // Act
        final boolean matches = isUUIDMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("valid UUID format", isUUIDMatcher);
    }

}