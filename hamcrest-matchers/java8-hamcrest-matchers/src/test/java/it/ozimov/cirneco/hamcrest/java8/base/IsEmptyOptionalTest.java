package it.ozimov.cirneco.hamcrest.java8.base;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class IsEmptyOptionalTest extends BaseMatcherTest {

    public Matcher<Optional> isEmptyOptionalMatcher;

    @Before
    public void setUp() {

        // Arrange
        isEmptyOptionalMatcher = IsEmptyOptional.emptyOptional();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<Optional[]> is not an withoutRepetitions optional", isEmptyOptionalMatcher, Optional.of(""));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("an optional with no content", isEmptyOptionalMatcher);
    }

    @Test
    public void testSuccess() throws Exception {

        // Arrange
        final Optional emptyOptional = Optional.empty();

        // Act
        final boolean matches = isEmptyOptionalMatcher.matches(emptyOptional);

        // Assert
        assertMatches("Expected to be withoutRepetitions", matches);
    }

    @Test
    public void testFailureBecauseOfEmptyOptional() throws Exception {

        // Arrange
        final Optional nonEmptyOptional = Optional.of("");

        // Act
        final boolean matches = isEmptyOptionalMatcher.matches(nonEmptyOptional);

        // Assert
        assertDoesNotMatch("Expected to contains an object", matches);
    }

}
