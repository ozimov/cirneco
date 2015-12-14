package org.cirneco.assertions.hamcrest.collection;

import com.google.common.collect.ImmutableMap;
import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IsMapWithSameKeySetTest extends BaseMatcherTest {

    public Matcher<Map<? extends Integer, ?>> isMapWithSameKeySetMatcher;

    public ImmutableMap<Integer, String> comparisonMap;

    public String getMatcherSimpleName() {
        return IsMapWithSameKeySet.class.getSimpleName();
    }

    @Before
    public void setup() {
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
        boolean hasSameKeyset = isMapWithSameKeySetMatcher.matches(assertedMap);

        //Assert
        assertThat("Expected to have same key set", hasSameKeyset, is(true));
    }

    @Test
    public void testGivenTwoDifferentKeySetWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        final Map<Integer, String> assertedMap = new HashMap<>();

        //Act
        boolean hasSameKeyset = isMapWithSameKeySetMatcher.matches(assertedMap);

        //Assert
        assertThat("Expected to have different key set", hasSameKeyset, is(false));
    }
}