package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

public class IsMultimapKeyWithCollectionSizeTest extends BaseMatcherTest {

    public String comparison;

    public Multimap<String, Object> multimap;
    public int expectedSizeCollectionForKeyInGeneralMatcher;

    public Matcher<Multimap<String, ?>> isMultimapElementWithCollectionSizeMatcher;

    @Before
    public void mapUp() {
        //Arrange
        multimap = HashMultimap.create();
        comparison = "ComparisonKey";

        expectedSizeCollectionForKeyInGeneralMatcher = 10;
        isMultimapElementWithCollectionSizeMatcher = IsMultimapKeyWithCollectionSize.keyWithSize(comparison, expectedSizeCollectionForKeyInGeneralMatcher);
    }

    @Test
    public void testGivenThatSizeIsNegativeWhenCreateInstanceThenNullPointerExceptionIsThrown() throws Exception {
        //Arrange
        thrown.expect(IllegalArgumentException.class);
        final int negativeSize = -1;
        assumeThat(negativeSize, lessThan(0));

        //Act
        IsMultimapKeyWithCollectionSize.keyWithSize(comparison, -1);

        //Assert
        fail("IllegalArgumentException expected");
    }

    @Test
    public void tesGivenAMultimapWithElementWithCollectionSizeTenWhenMatchesForSizeTenIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        addObjectsToKey(expectedSizeCollectionForKeyInGeneralMatcher);

        //Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void tesGivenAMultimapWithElementWithCollectionSizeOneWhenMatchesForSizeTenIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final int sizeCollectionForKeyInActualMap = 1;
        addObjectsToKey(sizeCollectionForKeyInActualMap);
        assumeThat(sizeCollectionForKeyInActualMap, not(is(expectedSizeCollectionForKeyInGeneralMatcher)));

        //Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(String.format("Multimap had element \"%s\" with collection size <0>", comparison),
                isMultimapElementWithCollectionSizeMatcher, multimap);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format("a Multimap with element \"%s\" with collection size <%d>", comparison, expectedSizeCollectionForKeyInGeneralMatcher),
                isMultimapElementWithCollectionSizeMatcher);
    }

    private void addObjectsToKey(int expectedCountGeneralMatcher) {
        for (int i = 0; i < expectedCountGeneralMatcher; i++) {
            multimap.put(comparison, new Object());
        }
    }

}