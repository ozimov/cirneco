package it.ozimov.cirneco.hamcrest.collect;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class IsIterableContainingInOrderTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<? extends Integer>> isIterableContainingInOrderMatcher;

    @Before
    public void setUp() {
        final Iterable<Integer> expected = Arrays.asList(1, 2, 3, 4);
        isIterableContainingInOrderMatcher = IsIterableContainingInOrder.containsInOrder(expected);
    }

    @Test
    public void testContainsInOrderSuccess() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3, 4);

        // Act
        final boolean matches = isIterableContainingInOrderMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testContainsInOrderFailWhenElementsAreInDifferentOrder() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 4, 3);

        // Act
        final boolean matches = isIterableContainingInOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testContainsInOrderFailWhenElementsAreLess() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3);

        // Act
        final boolean matches = isIterableContainingInOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testContainsInOrderFailWhenElementsAreMore() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3, 4, 5);

        // Act
        final boolean matches = isIterableContainingInOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

}
