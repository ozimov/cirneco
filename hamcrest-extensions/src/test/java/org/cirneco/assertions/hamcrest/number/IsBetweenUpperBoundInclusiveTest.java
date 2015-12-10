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

public class IsBetweenUpperBoundInclusiveTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenMatcher;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public String getMatcherSimpleName() {
        return IsBetweenUpperBoundInclusive.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenMatcher = IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(from, to);
    }

    @Test
    public void testGivenThatFromIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatToIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetweenUpperBoundInclusive.betweenUpperBoundInclusive("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatFromNotBeforeTo_WhenCreateInstance_ThenIllegalArgumentExceptionIsThrown() {
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
                isBetweenMatcher, 9);
        assertHasMismatchDescription("<10> is not between <10> excluded and <12> included",
                isBetweenMatcher, 10);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> excluded and <12> included", isBetweenMatcher);
    }



    @Test
    public void testGivenNumberEqualsToLowerBound_WhenBetween_ThenReturnFalse() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenMatcher.matches(10);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(false));
    }

    @Test
    public void testGivenNumberBetween_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenMatcher.matches(11);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberEqualsToUpperBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenMatcher.matches(12);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(true));
    }

    @Test
    public void testGivenNumberBiggerThanLowerBound_WhenBetween_ThenReturnTrue() throws Exception {
        //Act
        final boolean isBetweenUpperBoundInclusive = isBetweenMatcher.matches(13);

        //Assert
        assertThat(isBetweenUpperBoundInclusive, is(false));
    }

}