package it.ozimov.cirneco.hamcrest.java7.collect;

import static org.junit.Assume.assumeTrue;

import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.isEmpty;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

public class IsSortedIterableTest extends BaseMatcherTest {

    public Iterable<Integer> emptyIterable;
    public Iterable<Integer> unsortedIterable;
    public Iterable<Integer> sortedIterable;
    public Iterable<Integer> sortedReversedIterable;

    public Matcher<Iterable<Integer>> isSortedIterableMatcher;
    public Matcher<Iterable<Integer>> isReversedSortedIterableMatcher;

    @Before
    public void setUp() {

        // Arrange
        emptyIterable = ImmutableList.of();
        unsortedIterable = ImmutableList.of(100, 1, 10);
        sortedIterable = ImmutableList.of(1, 10, 100);
        sortedReversedIterable = ImmutableList.of(100, 10, 1);

        isSortedIterableMatcher = IsSortedIterable.sorted();
        isReversedSortedIterableMatcher = IsSortedIterable.sortedReversed();
    }

    @Test
    public void testGivenAnEmptyIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        assumeTrue(isEmpty(emptyIterable));

        // Act
        final boolean matches = isSortedIterableMatcher.matches(emptyIterable);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenAnEmptyIterableWhenMatchesForSortedReversedMatcherIsCalledThenTrueIsReturned()
        throws Exception {

        // Arrange
        assumeTrue(isEmpty(emptyIterable));

        // Act
        final boolean matches = isReversedSortedIterableMatcher.matches(emptyIterable);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenASortedIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean matches = isSortedIterableMatcher.matches(sortedIterable);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenANonSortedIterableWhenMatchesForSortedMatcherIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean matches = isSortedIterableMatcher.matches(unsortedIterable);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenAReversedSortedIterableWhenMatchesForSortedReversedMatcherIsCalledThenTrueIsReturned()
        throws Exception {

        // Act
        final boolean matches = isReversedSortedIterableMatcher.matches(sortedReversedIterable);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenANonReversedSortedIterableWhenMatchesForSortedReversedMatcherIsCalledThenFalseIsReturned()
        throws Exception {

        // Act
        final boolean matches = isReversedSortedIterableMatcher.matches(unsortedIterable);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("iterable was [<100>, <1>, <10>], while expected ordering was [<1>, <10>, <100>]",
            isSortedIterableMatcher, unsortedIterable);
        assertHasMismatchDescription("iterable was [<100>, <1>, <10>], while expected ordering was [<100>, <10>, <1>]",
            isReversedSortedIterableMatcher, unsortedIterable);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("iterable sorted according to natural ordering", isSortedIterableMatcher);
        assertIsDescribedTo("iterable sorted according to natural ordering in reverse order",
            isReversedSortedIterableMatcher);
    }
}
