package it.ozimov.cirneco.hamcrest.java7.base;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.fail;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsBetweenInclusiveTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenInclusiveMatcher;

    @Before
    public void setUp() {

        // Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenInclusiveMatcher = IsBetweenInclusive.betweenInclusive(from, to);
    }

    @Test
    public void testGivenThatFromIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {

        // Arrange
        thrown.expect(NullPointerException.class);

        // Act
        IsBetweenInclusive.betweenInclusive(null, "");

        // Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {

        // Arrange
        thrown.expect(NullPointerException.class);

        // Act
        IsBetweenInclusive.betweenInclusive("", null);

        // Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeToWhenCreateInstanceThenIllegalArgumentExceptionIsThrown() {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        IsBetweenInclusive.betweenInclusive("Z", "A");

        // Assert
        fail("IllegalArgumentException comparison but not thrown");
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<9> is not between <10> and <12>, both included", isBetweenInclusiveMatcher, 9);
        assertHasMismatchDescription("<13> is not between <10> and <12>, both included", isBetweenInclusiveMatcher, 13);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> and <12>, both included", isBetweenInclusiveMatcher);
    }

    @Test
    public void testGivenNumberSmallerThanLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean isBetweenInclusive = isBetweenInclusiveMatcher.matches(9);

        // Assert
        assertThat(isBetweenInclusive, is(false));
    }

    @Test
    public void testGivenNumberEqualsToLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean isBetweenInclusive = isBetweenInclusiveMatcher.matches(10);

        // Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberBetweenWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean isBetweenInclusive = isBetweenInclusiveMatcher.matches(11);

        // Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberEqualsToUpperBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean isBetweenInclusive = isBetweenInclusiveMatcher.matches(12);

        // Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberBiggerThanLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean isBetweenInclusive = isBetweenInclusiveMatcher.matches(13);

        // Assert
        assertThat(isBetweenInclusive, is(false));
    }

}
