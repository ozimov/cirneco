package it.ozimov.cirneco.hamcrest.collect;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class IsIterableContainingInAnyOrderTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<? extends Integer>> isIterableContainingInAnyOrderMatcher;

    @Before
    public void setUp() {
        final Iterable<Integer> expected = Arrays.asList(4, 1, 3, 2);
        isIterableContainingInAnyOrderMatcher = IsIterableContainingInAnyOrder.containsInAnyOrder(expected);
    }

    @Test
    public void testContainsInAnyOrderSuccess() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3, 4);

        // Act
        final boolean matches = isIterableContainingInAnyOrderMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testContainsInAnyOrderFailWhenElementsAreDifferent() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(5, 1, 3, 2);

        // Act
        final boolean matches = isIterableContainingInAnyOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testContainsInAnyOrderFailWhenElementsAreLess() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3);

        // Act
        final boolean matches = isIterableContainingInAnyOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testContainsInAnyOrderFailWhenElementsAreMore() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3, 4, 5);

        // Act
        final boolean matches = isIterableContainingInAnyOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

}
