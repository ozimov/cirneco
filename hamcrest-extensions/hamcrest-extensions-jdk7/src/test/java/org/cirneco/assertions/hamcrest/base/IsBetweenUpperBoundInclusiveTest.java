package org.cirneco.assertions.hamcrest.base;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.cirneco.assertions.hamcrest.base.IsBetweenUpperBoundInclusive;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class IsBetweenUpperBoundInclusiveTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenUpperBoundInclusiveMatcher;

    @Before
    public void setUp() {
        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenUpperBoundInclusiveMatcher = IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(from, to);
    }

    @Test
    public void testGivenThatFromIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeToWhenCreateInstanceThenIllegalArgumentExceptionIsThrown() {
        //Arrange
        thrown.expect(IllegalArgumentException.class);

        //Act
        IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive("Z", "A");

        //Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<9> is not between <10> excluded and <12> included",
                isBetweenUpperBoundInclusiveMatcher, 9);
        assertHasMismatchDescription("<10> is not between <10> excluded and <12> included",
                isBetweenUpperBoundInclusiveMatcher, 10);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> excluded and <12> included", isBetweenUpperBoundInclusiveMatcher);
    }

    @Test
    public void testGivenNumberEqualsToLowerBoundWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenUpperBoundInclusiveMatcher.matches(10);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(false));
    }

    @Test
    public void testGivenNumberBetweenWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenUpperBoundInclusiveMatcher.matches(11);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberEqualsToUpperBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenUpperBoundInclusiveMatcher.matches(12);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberBiggerThanLowerBoundWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenUpperBoundInclusiveMatcher.matches(13);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(false));
    }

}