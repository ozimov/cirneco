package it.ozimov.cirneco.hamcrest.iterable;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class IsIterableWithDistinctElementsTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<?>> isIterableWithDistinctElementsMatcher;

    @Before
    public void setUp() {
        isIterableWithDistinctElementsMatcher = IsIterableWithDistinctElements.hasDistinctElements();
    }

    @Test
    public void testGivenIterableWithOnlyDistinctElementsWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        final Iterable<Integer> iterable = createIterableWithElements(1, 2, 3);

        //Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        //Assert
        assertMatches(matches);
    }


    @Test
    public void testGivenIterableWithSomeNonDistinctElementsWhenMatchesForSortedMatcherIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final Iterable<Integer> iterable = createIterableWithElements(1, 2, 3, 3);

        //Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("non distinct elements are [<1>, <3>]",
                isIterableWithDistinctElementsMatcher, createIterableWithElements(1, 1, 2, 3, 3));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("an iterable with all distinct elements", isIterableWithDistinctElementsMatcher);
    }

}