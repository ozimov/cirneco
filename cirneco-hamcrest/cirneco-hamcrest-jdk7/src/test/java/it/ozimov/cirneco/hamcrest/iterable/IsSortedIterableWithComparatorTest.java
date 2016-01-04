package it.ozimov.cirneco.hamcrest.iterable;

import com.google.common.collect.ImmutableList;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static it.ozimov.cirneco.hamcrest.iterable.utils.IterableUtils.isEmpty;
import static org.junit.Assume.assumeTrue;

public class IsSortedIterableWithComparatorTest extends BaseMatcherTest {

    public Iterable<Integer> emptyIterable;
    public Iterable<Integer> unsortedIterable;
    public Iterable<Integer> sortedIterable;
    public Iterable<Integer> sortedReversedIterable;

    public Comparator<Integer> comparator;

    public Matcher<Iterable<Integer>> isSortedIterableMatcher;
    public Matcher<Iterable<Integer>> isReversedSortedIterableMatcher;

    @Before
    public void setUp() {
        //Arrange
        emptyIterable = ImmutableList.of();
        unsortedIterable = ImmutableList.of(100, 1, 10);
        sortedIterable = ImmutableList.of(100, 10, 1);
        sortedReversedIterable = ImmutableList.of(1, 10, 100);

        //Reverse natural ordering
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * Integer.compare(o1, o2);
            }
        };

        isSortedIterableMatcher = IsSortedIterableWithComparator.sorted(comparator);
        isReversedSortedIterableMatcher = IsSortedIterableWithComparator.sortedReversed(comparator);
    }

    @Test
    public void testGivenAnEmptyIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        assumeTrue(isEmpty(emptyIterable));

        //Act
        final boolean matches = isSortedIterableMatcher.matches(emptyIterable);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenAnEmptyIterableWhenMatchesForSortedReversedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        assumeTrue(isEmpty(emptyIterable));

        //Act
        final boolean matches = isReversedSortedIterableMatcher.matches(emptyIterable);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenASortedIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Act
        final boolean matches = isSortedIterableMatcher.matches(sortedIterable);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenANonSortedIterableWhenMatchesForSortedMatcherIsCalledThenFalseIsReturned() throws Exception {
        //Act
        final boolean matches = isSortedIterableMatcher.matches(unsortedIterable);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenAReversedSortedIterableWhenMatchesForSortedReversedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Act
        final boolean matches = isReversedSortedIterableMatcher.matches(sortedReversedIterable);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenANonReversedSortedIterableWhenMatchesForSortedReversedMatcherIsCalledThenFalseIsReturned() throws Exception {
        //Act
        final boolean matches = isReversedSortedIterableMatcher.matches(unsortedIterable);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("iterable was [<100>, <1>, <10>], while expected ordering [<100>, <10>, <1>]",
                isSortedIterableMatcher, unsortedIterable);
        assertHasMismatchDescription("iterable was [<100>, <1>, <10>], while expected ordering [<1>, <10>, <100>]",
                isReversedSortedIterableMatcher, unsortedIterable);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("iterable sorted according to given comparator", isSortedIterableMatcher);
        assertIsDescribedTo("iterable sorted according to given comparator in reverse order", isReversedSortedIterableMatcher);
    }
}