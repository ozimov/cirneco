package it.ozimov.cirneco.hamcrest.base;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assume.*;

public class IsSameTest extends BaseMatcherTest {

    public Integer comparison;
    public Object actual;
    public Matcher sameInstanceMatcher;

    @Before
    public void setUp() {

        // Arrange
        comparison = 10;
        actual = comparison;
        sameInstanceMatcher = IsSame.sameInstance(comparison);
    }

    @Test
    public void testGivenTwoInstancesOfSameObjectWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        assumeTrue("Assumed that actual and comparison are the same object", actual == comparison);

        // Act
        final boolean matches = sameInstanceMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenTwoInstancesOfDifferentObjectsWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        final Object actual = new Object();
        assumeFalse("Assumed that actual and comparison are not the same object", actual == comparison);

        // Act
        final boolean matches = sameInstanceMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenANullComparisonWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        assumeNotNull("Assumed that actual is not null", actual);

        // Act
        final boolean matches = IsSame.sameInstance(null).matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format("same instance of <%s>", comparison), sameInstanceMatcher);
    }

}
