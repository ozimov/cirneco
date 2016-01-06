package it.ozimov.cirneco.hamcrest.java7.base;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class IsBetweenTest extends BaseMatcherTest {

    public Matcher<Integer> isBetweenMatcher;

    @Before public void setUp() {

        //Arrange
        final Integer from = 10;
        final Integer to = 12;
        isBetweenMatcher = IsBetween.between(from, to);
    }

    @Test public void testGivenThatFromIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {

        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetween.between(null, "");

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test public void testGivenThatToIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {

        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsBetween.between("", null);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test public void testGivenThatFromNotBeforeToWhenCreateInstanceThenIllegalArgumentExceptionIsThrown() {

        //Arrange
        thrown.expect(IllegalArgumentException.class);

        //Act
        IsBetween.between("Z", "A");

        //Assert
        fail("IllegalArgumentException expected but not thrown");
    }

    @Test public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(
            "<9> is not between <10> and <12>, both excluded", isBetweenMatcher,
            9);
        assertHasMismatchDescription(
            "<10> is not between <10> and <12>, both excluded",
            isBetweenMatcher, 10);
        assertHasMismatchDescription(
            "<12> is not between <10> and <12>, both excluded",
            isBetweenMatcher, 12);
        assertHasMismatchDescription(
            "<13> is not between <10> and <12>, both excluded",
            isBetweenMatcher, 13);
    }

    @Test public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value between <10> and <12>, both excluded",
            isBetweenMatcher);
    }

    @Test public void testGivenNumberEqualsToLowerBoundWhenMatchesIsCalledThenReturnFalse()
        throws Exception {

        //Act
        final boolean isBetween = isBetweenMatcher.matches(10);

        //Assert
        assertThat(isBetween, is(false));
    }

    @Test public void testGivenNumberBetweenWhenMatchesIsCalledThenReturnTrue()
        throws Exception {

        //Act
        final boolean isBetween = isBetweenMatcher.matches(11);

        //Assert
        assertThat(isBetween, is(true));
    }

    @Test public void testGivenNumberEqualsToUpperBoundWhenMatchesIsCalledThenReturnFalse()
        throws Exception {

        //Act
        final boolean isBetween = isBetweenMatcher.matches(12);

        //Assert
        assertThat(isBetween, is(false));
    }

}
