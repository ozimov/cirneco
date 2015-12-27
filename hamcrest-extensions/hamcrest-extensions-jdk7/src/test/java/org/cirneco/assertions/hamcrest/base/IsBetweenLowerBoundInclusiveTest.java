package org.cirneco.assertions.hamcrest.base;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class IsBetweenLowerBoundInclusiveTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenLowerBoundInclusiveMatcher;

    @Before
    public void setUp() {
        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenLowerBoundInclusiveMatcher = IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive(from, to);
    }

    @Test
    public void testGivenThatFromIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeToWhenCreateInstanceThenIllegalArgumentExceptionIsThrown() {
        //Arrange
        thrown.expect(IllegalArgumentException.class);

        //Act
        IsBetweenLowerBoundInclusive.betweenLowerBoundInclusive("Z", "A");

        //Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<9> is not between <10> included and <12> excluded",
                isBetweenLowerBoundInclusiveMatcher, 9);
        assertHasMismatchDescription("<12> is not between <10> included and <12> excluded",
                isBetweenLowerBoundInclusiveMatcher, 12);
        assertHasMismatchDescription("<13> is not between <10> included and <12> excluded",
                isBetweenLowerBoundInclusiveMatcher, 13);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> included and <12> excluded", isBetweenLowerBoundInclusiveMatcher);
    }

    @Test
    public void testGivenNumberSmallerThanLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenLowerBoundInclusive = isBetweenLowerBoundInclusiveMatcher.matches(9);

        //Assert
        assertThat(isBetweenLowerBoundInclusive, is(false));
    }

    @Test
    public void testGivenNumberEqualsToLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenLowerBoundInclusive = isBetweenLowerBoundInclusiveMatcher.matches(10);

        //Assert
        assertThat(isBetweenLowerBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberBetweenWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenLowerBoundInclusive = isBetweenLowerBoundInclusiveMatcher.matches(11);

        //Assert
        assertThat(isBetweenLowerBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberEqualsToUpperBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenLowerBoundInclusive = isBetweenLowerBoundInclusiveMatcher.matches(12);

        //Assert
        assertThat(isBetweenLowerBoundInclusive, is(false));
    }

}