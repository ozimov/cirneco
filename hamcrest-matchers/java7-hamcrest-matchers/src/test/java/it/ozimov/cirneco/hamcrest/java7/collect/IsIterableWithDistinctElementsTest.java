package it.ozimov.cirneco.hamcrest.java7.collect;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class IsIterableWithDistinctElementsTest extends BaseIterableMatcherTest {
    /**
     * Any quadratic algorithm should take "forever" for this amount of elements.
     * e.g. 100000 elements takes 13 seconds to scan for duplicates naively (check frequencies of all items).
     *
     * @see #HUGE_ITERABLE_QUADRATIC_TIMEOUT
     */
    private static final int HUGE_ITERABLE = 100000;
    /** Leeway for {@link #HUGE_ITERABLE} handling. */
    private static final int HUGE_ITERABLE_QUADRATIC_TIMEOUT = 500;

    public Matcher<Iterable<?>> isIterableWithDistinctElementsMatcher;

    @Before
    public void setUp() {
        isIterableWithDistinctElementsMatcher = IsIterableWithDistinctElements.hasDistinctElements();
    }

    @Test
    public void testGivenIterableWithNoElementsWhenMatchesForDistinctMatcherIsCalledThenTrueIsReturned()
            throws Exception {

        // Arrange
        final Iterable<Integer> iterable = createIterableWithElements(/* none */);

        // Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenIterableWithOnlyDistinctElementsWhenMatchesForDistinctMatcherIsCalledThenTrueIsReturned()
            throws Exception {

        // Arrange
        final Iterable<Integer> iterable = createIterableWithElements(1, 2, 3);

        // Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenIterableWithSomeNonDistinctElementsWhenMatchesForDistinctMatcherIsCalledThenFalseIsReturned()
            throws Exception {

        // Arrange
        final Iterable<Integer> iterable = createIterableWithElements(1, 2, 3, 3);

        // Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test(timeout = HUGE_ITERABLE_QUADRATIC_TIMEOUT)
    public void testGivenHugeIterableWithDistinctElementsWhenMatchesForDistinctMatcherIsCalledThenTrueIsReturnedInTime()
            throws Exception {

        // Arrange
        final Iterable<?> iterable = createIterableWithSize(HUGE_ITERABLE);

        // Act
        final boolean matches = isIterableWithDistinctElementsMatcher.matches(iterable);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenIterableWithSomeNonDistinctElementsWhenDescribedThenReturnsCorrectElements() 
            throws Exception {

        // Arrange
        final Iterable<Integer> iterable = createIterableWithElements(1, 1, 2, 3, 3);

        // Act & Assert
        BaseMatcherTest.assertHasMismatchDescription("non distinct elements are [<1>, <3>]",
                isIterableWithDistinctElementsMatcher, iterable);
    }

    @Test(timeout = HUGE_ITERABLE_QUADRATIC_TIMEOUT)
    public void testGivenHugeIterableWithADuplicateElementWhenDescribedThenReturnsInTime()
            throws Exception {

        // Arrange
        Integer[] data = new Integer[HUGE_ITERABLE];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        data[data.length - 1] = data[0]; // make a duplicate
        
        // Act & Assert
        BaseMatcherTest.assertHasMismatchDescription("non distinct elements are [<0>]",
                isIterableWithDistinctElementsMatcher, createIterableWithElements(data));
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("an iterable with all distinct elements",
                isIterableWithDistinctElementsMatcher);
    }

}
