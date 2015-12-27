package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.ImmutableMap;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IsMapWithSameKeySetTest extends BaseMatcherTest {

    public Matcher<Map<? extends Integer, ?>> isMapWithSameKeySetMatcher;

    public ImmutableMap<Integer, String> comparisonMap;

    @Before
    public void setUp() {
        //Arrange
        comparisonMap = ImmutableMap.of(
                1, "1",
                2, "2",
                3, "3",
                4, "4");

        isMapWithSameKeySetMatcher = IsMapWithSameKeySet.hasSameKeySet(comparisonMap);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("was null", isMapWithSameKeySetMatcher, null);
        assertHasMismatchDescription("map key set was [] while expected key set was [<1=1>, <2=2>, <3=3>, <4=4>]",
                isMapWithSameKeySetMatcher, ImmutableMap.<Integer, Object>of());
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("map containing same key set as [<1=1>, <2=2>, <3=3>, <4=4>]", isMapWithSameKeySetMatcher);
    }

    @Test
    public void testGivenTwoEqualsKeySetWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        final Map<Integer, String> assertedMap = new HashMap<>();
        for (final Integer key : comparisonMap.keySet()) {
            assertedMap.put(key, UUID.randomUUID().toString());
        }

        //Act
        boolean matches = isMapWithSameKeySetMatcher.matches(assertedMap);

        //Assert
        assertMatches("Expected to have same key set", matches);
    }

    @Test
    public void testGivenTwoDifferentKeySetWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final Map<Integer, String> assertedMap = new HashMap<>();

        //Act
        boolean matches = isMapWithSameKeySetMatcher.matches(assertedMap);

        //Assert
        assertDoesNotMatch("Expected to have different key set", matches);
    }
}