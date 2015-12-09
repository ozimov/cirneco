package org.cirneco.assertions.hamcrest.collection;

import com.google.common.base.Equivalence;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Map;

import org.hamcrest.Description;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.IsAnything.anything;

/**
 * Does the map has the same key set of another? {@linkplain com.google.common.base.Equivalence} can be used in the
 * key set comparison.
 */
public class IsMapWithSameKeySet<K,V> extends TypeSafeMatcher<Map<? extends K, ? extends V>> {

    private final Map<? extends K, ?> comparisonMap;

    private final Equivalence<K> equivalence;

    public IsMapWithSameKeySet(final Map<? extends K, ?> comparisonMap) {
        this.comparisonMap = comparisonMap;
        this.equivalence = null;
    }

    public IsMapWithSameKeySet(final Map<? extends K, ?> comparisonMap,
                                   final Equivalence<K> equivalence) {
        this.comparisonMap = comparisonMap;
        this.equivalence = equivalence;
    }

    @Override
    public boolean matchesSafely(final Map<? extends K, ? extends V> map) {
        final Set<? extends  K> mapKeySet = map.keySet();
        final Set<? extends  K> comparisonMapKeySet = new TreeSet<>(comparisonMap.keySet());
        if(equivalence != null){
            if(map == comparisonMap ){
                return true;
            }
            else if(map.size() == comparisonMap.keySet().size()){
                for(final K key : mapKeySet){
                    boolean found = false;
                    for(final K keyComp : comparisonMapKeySet){
                        if(equivalence.equivalent(key, keyComp)){
                            comparisonMapKeySet.remove(keyComp);
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        else{
            return mapKeySet.equals(comparisonMapKeySet);
        }
    }

    @Override
    public void describeMismatchSafely(final Map<? extends K, ? extends V> map, final Description mismatchDescription) {
        mismatchDescription.appendText("map was ").appendValueList("[", ", ", "]", map.entrySet());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("map containing same key set as ")
                .appendValueList("[", ", ", "]", comparisonMap.entrySet());
        if(equivalence!=null){
            description.appendText(" using custom equivalence");
        }
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined {@link java.util.Map} has exactly
     * the same key set of the given map.
     * For example:
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     *
     */
    public static <K,V> Matcher<Map<? extends K,? extends V>> hasSameKeySet(final Map<? extends K, ?> comparisonMap) {
        return new IsMapWithSameKeySet<>(comparisonMap);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined {@link java.util.Map} has exactly
     * the same key set of the given map using the given {@linkplain Equivalence} object.
     * For example:
     * <pre>assertThat(myMap, hasSameKeySet(anotherMap))</pre>
     *
     */
    public static <K,V> Matcher<Map<? extends K,? extends V>> hasSameKeySet(final Map<? extends K, ?> comparisonMap,
                                                                            final Equivalence<K> equivalence) {
        return new IsMapWithSameKeySet<>(comparisonMap, equivalence);
    }

}