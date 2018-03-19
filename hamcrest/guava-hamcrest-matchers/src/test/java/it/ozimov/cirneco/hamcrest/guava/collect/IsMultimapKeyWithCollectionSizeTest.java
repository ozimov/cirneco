package it.ozimov.cirneco.hamcrest.guava.collect;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

public class IsMultimapKeyWithCollectionSizeTest extends BaseMatcherTest {

    public String comparison;
    public String missingComparison;

    public Multimap<String, Object> multimap;
    public int expectedSizeCollectionForKeyInGeneralMatcher;

    public Matcher<Multimap<String, ?>> isMultimapElementWithCollectionSizeMatcher;
    public Matcher<Multimap<String, ?>> isMultimapMissingElementWithCollectionSizeMatcher;

    @Before
    public void mapUp() {

        // Arrange
        multimap = HashMultimap.create();
        comparison = "ComparisonKey";
        missingComparison = "MissingComparisonKey";

        expectedSizeCollectionForKeyInGeneralMatcher = 10;
        isMultimapElementWithCollectionSizeMatcher = IsMultimapKeyWithCollectionSize.keyWithSize(comparison,
                expectedSizeCollectionForKeyInGeneralMatcher);
        isMultimapMissingElementWithCollectionSizeMatcher = IsMultimapKeyWithCollectionSize.keyWithSize(
                missingComparison, expectedSizeCollectionForKeyInGeneralMatcher);
    }

    @Test
    public void testGivenThatSizeIsNegativeWhenCreateInstanceThenNullPointerExceptionIsThrown() throws Exception {

        // Arrange
        thrown.expect(IllegalArgumentException.class);

        final int negativeSize = -1;
        assumeThat(negativeSize, lessThan(0));

        // Act
        IsMultimapKeyWithCollectionSize.keyWithSize(comparison, -1);

        // Assert
        fail("IllegalArgumentException comparison");
    }

    @Test
    public void tesGivenAMultimapWithElementWithCollectionSizeTenWhenMatchesForSizeTenIsCalledThenTrueIsReturned()
            throws Exception {

        // Arrange
        addObjectsToKey(expectedSizeCollectionForKeyInGeneralMatcher);

        // Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void tesGivenAMultimapWithElementWithCollectionSizeOneWhenMatchesForSizeTenIsCalledThenFalseIsReturned()
            throws Exception {

        // Arrange
        final int sizeCollectionForKeyInActualMap = 1;
        addObjectsToKey(sizeCollectionForKeyInActualMap);
        assumeThat(sizeCollectionForKeyInActualMap, not(is(expectedSizeCollectionForKeyInGeneralMatcher)));

        // Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {

        // Arrange
        assumeThat(multimap.keySet(), hasSize(0));
        multimap.put(comparison, "");

        // Assert
        BaseMatcherTest.assertHasMismatchDescription(String.format("Multimap was not containing element \"%s\"",
                missingComparison), isMultimapMissingElementWithCollectionSizeMatcher, multimap);

        BaseMatcherTest.assertHasMismatchDescription(String.format(
                "Multimap had element \"%s\" with collection size <1>", comparison),
                isMultimapElementWithCollectionSizeMatcher, multimap);
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo(String.format("a Multimap with element \"%s\" with collection size <%d>",
                comparison, expectedSizeCollectionForKeyInGeneralMatcher), isMultimapElementWithCollectionSizeMatcher);
    }

    private void addObjectsToKey(final int expectedCountGeneralMatcher) {

        for (int i = 0; i < expectedCountGeneralMatcher; i++) {
            multimap.put(comparison, new Object());
        }
    }

}
