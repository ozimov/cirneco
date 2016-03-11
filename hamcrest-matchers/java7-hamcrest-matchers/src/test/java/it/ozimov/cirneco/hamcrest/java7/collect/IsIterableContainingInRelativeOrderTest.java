package it.ozimov.cirneco.hamcrest.java7.collect;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class IsIterableContainingInRelativeOrderTest extends BaseIterableMatcherTest {

    public Matcher<Iterable<? extends Integer>> isIterableContainingInRelativeOrderMatcher;

    @Before
    public void setUp() {
        final Iterable<Integer> expected = Arrays.asList(1, 4);
        isIterableContainingInRelativeOrderMatcher = IsIterableContainingInRelativeOrder.containsInRelativeOrder(
                expected);
    }

    @Test
    public void testContainsInRelativeOrderSuccess() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(1, 2, 3, 4);

        // Act
        final boolean matches = isIterableContainingInRelativeOrderMatcher.matches(actual);

        // Assert
        assertMatches(matches);
    }

    @Test
    public void testContainsInRelativeOrderFailWhenOrderIsDifferent() throws Exception {

        // Arrange
        final Iterable<Integer> actual = Arrays.asList(4, 2, 3, 1);

        // Act
        final boolean matches = isIterableContainingInRelativeOrderMatcher.matches(actual);

        // Assert
        assertDoesNotMatch(matches);
    }

}
