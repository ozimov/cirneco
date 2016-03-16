package it.ozimov.cirneco.hamcrest.java7.collect;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import lombok.experimental.Delegate;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IsEmptyIterableTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<?>> isEmptyIterableMatcher;

    @Before
    public void setUp() {
        isEmptyIterableMatcher = IsEmptyIterable.empty();
    }

    @Test
    public void testGivenEmptyIterableWhenMatchesForEmptyIterableMatcherIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        final int expectedSize = 0;
        final Iterable<Object> emptyIterable = createIterableWithSize(expectedSize);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(emptyIterable);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonEmptyIterableWhenMatchesForEmptyIterableMatcherIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        final int expectedSize = 1;
        final Iterable<Object> nonEmptyIterable = createIterableWithSize(expectedSize);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(nonEmptyIterable);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testGivenEmptyIterableNotCollectionWhenMatchesForEmptyIterableMatcherIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        final IterableNotCollection iterableNotCollection = new IterableNotCollection(0);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(iterableNotCollection);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNonEmptyIterableNotCollectionWhenMatchesForEmptyIterableMatcherIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        final IterableNotCollection iterableNotCollection = new IterableNotCollection(1);

        // Act
        final boolean matches = isEmptyIterableMatcher.matches(iterableNotCollection);

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

    static class IterableNotCollection implements Iterable {

        @Delegate
        Iterable iterable;

        public IterableNotCollection(final int size) {
            final List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            iterable = list;
        }
    }

}
