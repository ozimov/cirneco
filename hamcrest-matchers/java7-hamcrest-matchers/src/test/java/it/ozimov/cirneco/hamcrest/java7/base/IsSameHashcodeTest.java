package it.ozimov.cirneco.hamcrest.java7.base;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsSameHashcodeTest extends BaseMatcherTest {

    public Object actual;

    public Object anotherActual;

    public Object comparison;

    public Matcher sameHashcodeMatcher;

    @Before
    public void setUp() {

        // Arrange
        actual = new Integer(0);
        anotherActual = new Integer(999);
        comparison = new Integer(0);

        sameHashcodeMatcher = IsSameHashcode.sameHashcode(comparison);
    }

    @Test
    public void testGivenTwoInstancesOfSameObjectWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        assumeTrue("Assumed that actual and comparison have the same hashcode",
            actual.hashCode() == comparison.hashCode());

        // Act
        final boolean matches = sameHashcodeMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenTwoInstancesOfDifferentObjectsWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        assumeFalse("Assumed that actual and comparison have different haschode",
            anotherActual.hashCode() == comparison.hashCode());

        // Act
        final boolean matches = sameHashcodeMatcher.matches(anotherActual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenANullComparisonWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        thrown.expect(NullPointerException.class);

        // Act
        final boolean matches = IsSameHashcode.sameHashcode(null).matches(actual);

        // Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format("same hashcode of <%s>", comparison), sameHashcodeMatcher);
    }

}
