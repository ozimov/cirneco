package it.ozimov.cirneco.hamcrest.base;

import com.google.common.base.Optional;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class IsEmptyGuavaOptionalTest extends BaseMatcherTest {

    public Matcher<Optional> isEmptyGuavaOptionalMatcher;

    @Before
    public void setUp() {
        //Arrange
        isEmptyGuavaOptionalMatcher = IsEmptyGuavaOptional.emptyOptional();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<Optional.of()> is not an empty optional",
                isEmptyGuavaOptionalMatcher, Optional.of(""));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("an optional with no content", isEmptyGuavaOptionalMatcher);
    }

    @Test
    public void testGivenAnEmptyOptionalWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        final Optional emptyOptional = Optional.absent();

        //Act
        final boolean matches = isEmptyGuavaOptionalMatcher.matches(emptyOptional);

        //Assert
        assertMatches("Expected to be empty", matches);
    }

    @Test
    public void testGivenANonEmptyOptionalWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final Optional nonEmptyOptional = Optional.of("");

        //Act
        final boolean matches = isEmptyGuavaOptionalMatcher.matches(nonEmptyOptional);

        //Assert
        assertDoesNotMatch("Expected to contains an object", matches);
    }

}