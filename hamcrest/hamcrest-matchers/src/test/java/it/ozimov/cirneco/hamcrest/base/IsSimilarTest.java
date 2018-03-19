package it.ozimov.cirneco.hamcrest.base;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import it.ozimov.cirneco.hamcrest.base.utils.LevenshteinDistance;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.junit.Assume.*;

public class IsSimilarTest extends BaseMatcherTest {

    public CharSequence actual;

    public CharSequence anotherActual;

    public CharSequence comparison;

    public int distanceWithActual;
    public int distanceWithAnotherActual;

    @Before
    public void setUp() {
        // Arrange
        actual = "TEST";
        anotherActual = actual+"123";
        comparison = ""+actual;

        distanceWithActual = 0;
        assumeThat(distanceWithActual, is(LevenshteinDistance.distance(actual, comparison)));
        distanceWithAnotherActual = 3;
        assumeThat(distanceWithAnotherActual, is(LevenshteinDistance.distance(anotherActual, comparison)));
    }

    @Test
    public void testGivenTwoIdenticalStringsWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        // Arrange
        assumeTrue("Assumed that actual and comparison are equals",
                actual.equals(comparison));
        final Matcher isSimilarMatcher = IsSimilar.similar(comparison, distanceWithActual);

        // Act
        final boolean matches = isSimilarMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenTwoInstancesOfDifferentStringsButWithinTheDesiredDistanceWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        // Arrange
        assumeFalse("Assumed that actual and comparison are not equals",
                anotherActual.equals(comparison));
        final Matcher isSimilarMatcher = IsSimilar.similar(comparison, distanceWithAnotherActual);

        // Act
        final boolean matches = isSimilarMatcher.matches(anotherActual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenTwoInstancesOfDifferentStringsAndMaxDistanceWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        // Arrange
        assumeFalse("Assumed that actual and comparison are not equals",
                anotherActual.equals(comparison));
        final Matcher isSimilarMatcher = IsSimilar.similar(comparison, Integer.MAX_VALUE);

        // Act
        final boolean matches = isSimilarMatcher.matches(anotherActual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenTwoInstancesOfDifferentStringsAndZeroDistanceWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Arrange
        assumeFalse("Assumed that actual and comparison are not equals",
                anotherActual.equals(comparison));
        final Matcher isSimilarMatcher = IsSimilar.similar(comparison, 0);

        // Act
        final boolean matches = isSimilarMatcher.matches(anotherActual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenANullComparisonWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Arrange
        thrown.expect(NullPointerException.class);

        // Act
        IsSimilar.similar(null, 0).matches(actual);

        // Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenANegativeDistanceWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        IsSimilar.similar("", -1).matches(actual);

        // Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testGivenANonCharSequenceWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        new IsSimilar("", -1).matches(new BigDecimal("100"));

        // Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testDescribeTo() throws Exception {
        final int distance = 0;
        assertIsDescribedTo(String.format("has max Levenshtein distance <%d> from \"%s\"", distance, comparison),
                IsSimilar.similar(comparison, distance));
    }

}
