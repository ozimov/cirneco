package it.ozimov.cirneco.hamcrest.java7.collect;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

public class IsEmptyIterableTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<?>> isEmptyIterableMatcher;

    @Before
    public void setUp() {
        isEmptyIterableMatcher = IsEmptyIterable.empty();
    }

    @Test
    public void testGivenEmptyIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        final int expectedSize = 0;
        final Iterable<Object> emptyIterable = createIterableWithSize(expectedSize);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(emptyIterable);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonEmptyIterableWhenMatchesForSortedMatcherIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        final int expectedSize = 1;
        final Iterable<Object> nonEmptyIterable = createIterableWithSize(expectedSize);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(nonEmptyIterable);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription("[<100>, <1>, <10>]", isEmptyIterableMatcher,
            createIterableWithElements(100, 1, 10));
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("an empty iterable", isEmptyIterableMatcher);
    }

}
