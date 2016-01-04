package it.ozimov.cirneco.hamcrest.iterable;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class IsEmptyIterableTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<?>> isEmptyIterableMatcher;

    @Before
    public void setUp() {
        isEmptyIterableMatcher = IsEmptyIterable.empty();
    }

    @Test
    public void testGivenEmptyIterableWhenMatchesForSortedMatcherIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        final int expectedSize = 0;
        final Iterable<Object> emptyIterable = createIterableWithSize(expectedSize);

        //Act
        final boolean matches = isEmptyIterableMatcher.matches(emptyIterable);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenNonEmptyIterableWhenMatchesForSortedMatcherIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final int expectedSize = 1;
        final Iterable<Object> nonEmptyIterable = createIterableWithSize(expectedSize);

        //Act
        final boolean matches = isEmptyIterableMatcher.matches(nonEmptyIterable);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("[<100>, <1>, <10>]",
                isEmptyIterableMatcher, createIterableWithElements(100, 1, 10));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("an empty iterable", isEmptyIterableMatcher);
    }


}