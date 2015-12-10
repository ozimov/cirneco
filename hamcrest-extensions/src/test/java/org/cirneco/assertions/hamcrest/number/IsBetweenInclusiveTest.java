package org.cirneco.assertions.hamcrest.number;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class IsBetweenInclusiveTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenMatcher;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public String getMatcherSimpleName() {
        return IsBetweenInclusive.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenMatcher = IsBetweenInclusive.betweenInclusive(from, to);
    }

    @Test
    public void testGivenThatFromIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenInclusive.betweenInclusive(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenInclusive.betweenInclusive("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeTo_WhenCreateInstance_ThenIllegalArgumentExceptionIsThrown() {
        //Arrange
        thrown.expect(IllegalArgumentException.class);

        //Act
        IsBetweenInclusive.betweenInclusive("Z", "A");

        //Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<9> is not between <10> and <12>, both included",
                isBetweenMatcher, 9);
        assertHasMismatchDescription("<13> is not between <10> and <12>, both included",
                isBetweenMatcher, 13);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> and <12>, both included", isBetweenMatcher);
    }

    @Test
    public void testGivenNumberSmallerThanLowerBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenInclusive = isBetweenMatcher.matches(9);

        //Assert
        assertThat(isBetweenInclusive, is(false));
    }

    @Test
    public void testGivenNumberEqualsToLowerBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenInclusive = isBetweenMatcher.matches(10);

        //Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberBetween_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenInclusive = isBetweenMatcher.matches(11);

        //Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberEqualsToUpperBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenInclusive = isBetweenMatcher.matches(12);

        //Assert
        assertThat(isBetweenInclusive, is(true));
    }

    @Test
    public void testGivenNumberBiggerThanLowerBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenInclusive = isBetweenMatcher.matches(13);

        //Assert
        assertThat(isBetweenInclusive, is(false));
    }

}
