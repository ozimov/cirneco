package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

public class IsMultimapWithKeySetSizeTest extends BaseMatcherTest {

    public String comparison;

    public Multimap<String, Object> multimap;
    public int expectedCountGeneralMatcher;

    public Matcher<Multimap<String, ?>> isMultimapElementWithCollectionEmptyMatcher;
    public Matcher<Multimap<String, ?>> isMultimapElementWithCollectionSizeMatcher;

    @Before
    public void mapUp() {
        //Arrange
        multimap = HashMultimap.create();

        isMultimapElementWithCollectionEmptyMatcher = IsMultimapWithKeySetSize.emptyKeySet();
        expectedCountGeneralMatcher = 10;
        isMultimapElementWithCollectionSizeMatcher = IsMultimapWithKeySetSize.keySetWithSize(expectedCountGeneralMatcher);
    }

    @Test
    public void testGivenThatSizeIsNegativeWhenCreateInstanceThenNullPointerExceptionIsThrown() throws Exception {
        //Arrange
        thrown.expect(IllegalArgumentException.class);
        final int negativeSize = -1;
        assumeThat(negativeSize, lessThan(0));

        //Act
        IsMultimapWithKeySetSize.keySetWithSize(-1);

        //Assert
        fail("IllegalArgumentException expected");
    }

    @Test
    public void testGivenAMultimapWithKeySetSizeZeroWhenMatchesForEmptyIsCalledThenTrueIsReturned() throws Exception {
        //Arrange

        //Act
        final boolean matches = isMultimapElementWithCollectionEmptyMatcher.matches(multimap);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenAMultimapWithKeySetSizeOneWhenMatchesForEmptyIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        addKeysToMultimap(1);

        //Act
        final boolean matches = isMultimapElementWithCollectionEmptyMatcher.matches(multimap);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void tesGivenAMultimapWithKeySetSizeTenWhenMatchesForSizeTenIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        addKeysToMultimap(expectedCountGeneralMatcher);

        //Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void tesGivenAMultimapWithKeySetSizeOneWhenMatchesForSizeTenIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final int numKeysActualMap = 1;
        addKeysToMultimap(numKeysActualMap);
        assumeThat(numKeysActualMap, not(is(expectedCountGeneralMatcher)));

        //Act
        final boolean matches = isMultimapElementWithCollectionSizeMatcher.matches(multimap);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        //Arrange
        multimap.put("A", "");
        multimap.put("B", "");
        assumeThat(multimap.keySet(), hasSize(2));

        //Act + Assert
        assertHasMismatchDescription("Multimap had key set with <2> elements",
                isMultimapElementWithCollectionEmptyMatcher, multimap);
        assertHasMismatchDescription("Multimap had key set with <2> elements",
                isMultimapElementWithCollectionSizeMatcher, multimap);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a Multimap with no elements",
                isMultimapElementWithCollectionEmptyMatcher);
        assertIsDescribedTo(String.format("a Multimap with <%d> elements", expectedCountGeneralMatcher),
                isMultimapElementWithCollectionSizeMatcher);
    }

    private void addKeysToMultimap(int expectedNumberOfKeys) {
        for (int i = 0; i < expectedNumberOfKeys; i++) {
            multimap.put(UUID.randomUUID().toString(), new Object());
        }
    }

}