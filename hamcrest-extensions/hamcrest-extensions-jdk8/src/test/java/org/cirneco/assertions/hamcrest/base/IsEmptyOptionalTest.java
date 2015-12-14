package org.cirneco.assertions.hamcrest.base;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IsEmptyOptionalTest extends BaseMatcherTest {

    public Matcher<Optional> isEmptyOptionalMatcher;

    public String getMatcherSimpleName() {
        return IsEmptyOptional.class.getSimpleName();
    }

    @Before
    public void setUp() {
        //Arrange
        isEmptyOptionalMatcher = IsEmptyOptional.emptyOptional();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<Optional[]> is not an empty optional",
                isEmptyOptionalMatcher, Optional.of(""));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("an optional with no content", isEmptyOptionalMatcher);
    }

    @Test
    public void testGivenAnEmptyOptionalWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        final Optional emptyOptional = Optional.empty();

        //Act
        boolean isEmpty = isEmptyOptionalMatcher.matches(emptyOptional);

        //Assert
        assertThat("Expected to be empty", isEmpty, is(true));
    }

    @Test
    public void testGivenANonEmptyOptionalWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final Optional nonEmptyOptional = Optional.of("");

        //Act
        boolean isEmpty = isEmptyOptionalMatcher.matches(nonEmptyOptional);

        //Assert
        assertThat("Expected to contains an object", isEmpty, is(false));
    }

}