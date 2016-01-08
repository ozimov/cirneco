package it.ozimov.cirneco.hamcrest.java7.collect;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.Matcher;

import org.junit.Test;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

public class IsIterableWithSizeTest extends BaseIterableMatcherTest {

    @Test
    public void testGivenANegativeSizeWhenHasSizeIsCalledThenThrowsIllegalArgumentException() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        IsIterableWithSize.hasSize(-1);

        // Assert
        fail("Exception expected");
    }

    @Test
    public void testHasSizeOne() throws Exception {

        // Arrange
        final int expectedSize = 1;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSizeOne();

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testHasSizeTwo() throws Exception {

        // Arrange
        final int expectedSize = 2;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSizeTwo();

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testHasSizeThree() throws Exception {

        // Arrange
        final int expectedSize = 3;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSizeThree();

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testHasSizeFour() throws Exception {

        // Arrange
        final int expectedSize = 4;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSizeFour();

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testHasSizeFive() throws Exception {

        // Arrange
        final int expectedSize = 5;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSizeFive();

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testHasSize() throws Exception {

        // Arrange
        final int expectedSize = 100;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final Iterable<Object> iterableWithDifferentSize = createIterableWithSize(expectedSize + 1);
        final Matcher<Iterable<Object>> isIterableWithSizeMatcher = IsIterableWithSize.hasSize(expectedSize);

        // Act
        final boolean matchesOnSameSize = isIterableWithSizeMatcher.matches(iterableWithExpectedSize);
        final boolean matchesOnDifferentSize = isIterableWithSizeMatcher.matches(iterableWithDifferentSize);

        // Assert
        BaseMatcherTest.assertMatches(matchesOnSameSize);
        BaseMatcherTest.assertDoesNotMatch(matchesOnDifferentSize);
    }

    @Test
    public void testFeatureValueOf() throws Exception {

        // Arrange
        final int expectedSize = 100;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);
        final IsIterableWithSize<Object> isIterableWithSizeMatcher = new IsIterableWithSize(expectedSize);

        // Act
        final int calculatedSize = isIterableWithSizeMatcher.featureValueOf(iterableWithExpectedSize);

        // Assert
        assertThat(calculatedSize, is(expectedSize));
    }

}
