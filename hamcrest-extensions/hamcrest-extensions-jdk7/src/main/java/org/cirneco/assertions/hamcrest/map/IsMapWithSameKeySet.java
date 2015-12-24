package org.cirneco.assertions.hamcrest.map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Does the map has the same key set of another? {@linkplain com.google.common.base.Equivalence} can be used in the
 * key set comparison.
 *
 * @since 0.1
 */
public class IsMapWithSameKeySet<K> extends TypeSafeMatcher<Map<? extends K, ?>> {

    private final Map<? extends K, ?> comparisonMap;

    /**
     * Creates an instance of the class using the default equality comparison for the given keys.
     */
    public IsMapWithSameKeySet(final Map<? extends K, ?> comparisonMap) {
        this.comparisonMap = comparisonMap;
    }

    /**
     * Creates a matcher for {@link Map}s matching when the examined {@link Map} has exactly
     * the same key set of the given map.
     * For example:
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     */
    public static <K> Matcher<Map<? extends K, ?>> hasSameKeySet(final Map<? extends K, ?> comparisonMap) {
        return new IsMapWithSameKeySet<>(comparisonMap);
    }

    @Override
    public boolean matchesSafely(final Map<? extends K, ?> map) {
        final Set<? extends K> mapKeySet = map.keySet();
        final Set<? extends K> comparisonMapKeySet = new TreeSet<>(comparisonMap.keySet());
        return mapKeySet.equals(comparisonMapKeySet);
    }

    @Override
    public void describeMismatchSafely(final Map<? extends K, ?> map, final Description mismatchDescription) {
        mismatchDescription.appendText("map key set was ")
                .appendValueList("[", ", ", "]", map.entrySet())
                .appendText(" while expected key set was ")
                .appendValueList("[", ", ", "]", comparisonMap.entrySet());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("map containing same key set as ")
                .appendValueList("[", ", ", "]", comparisonMap.entrySet());
    }

}