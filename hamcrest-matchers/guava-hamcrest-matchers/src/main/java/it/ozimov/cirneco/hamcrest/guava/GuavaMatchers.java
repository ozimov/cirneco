package it.ozimov.cirneco.hamcrest.guava;

import java.util.Collection;
import java.util.Set;

import org.hamcrest.Matcher;

import com.google.common.base.Equivalence;
import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import it.ozimov.cirneco.hamcrest.guava.base.IsEmptyGuavaOptional;
import it.ozimov.cirneco.hamcrest.guava.base.IsEquivalent;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapKeyWithCollectionSize;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySet;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySetSize;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultisetElementWithCount;

/**
 * The {@code GuavaMatchers} class groups all the matchers introduced by Cirneco's Hamcrest extension for <a
 * href="https://github.com/google/guava">Google Guava library</a>. Suggested use would be to import all the static
 * methods of this class in a unit test.
 *
 * @since  version 0.1 for JDK7
 */
public class GuavaMatchers {

    // BASE

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> emptyGuavaOptional() {
        return IsEmptyGuavaOptional.emptyGuavaOptional();
    }

    /**
     * Creates a matcher that matches when the examined object of type <code>T</code> is equivalent to the specified
     * <code>comparison</code> object according to the provided {@linkplain Equivalence}.
     *
     * <p>
     * <p>
     * <p>
     * <p>
     * <p/>
     * <p>Observe that the {@linkplain Equivalence} can deal with nulls.</p>
     */
    public static <T> Matcher<T> equivalentTo(final T expected, final Equivalence<T> equivalence) {
        return IsEquivalent.equivalentTo(expected, equivalence);
    }

    // COLLECT

    /**
     * Creates a matcher for {@linkplain Multiset} matching when the examined object <code>E</code> has <code>
     * size</code> occurrences.
     */
    public static <E> Matcher<Multiset<E>> elementWithCount(final E element, final int size) {
        return IsMultisetElementWithCount.elementWithCount(element, size);
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object <code>K</code> in the key set has
     * <code>size</code> elements in the retained {@linkplain Collection}.
     */
    public static <K> Matcher<Multimap<K, ?>> keyWithSize(final K element, final int size) {
        return IsMultimapKeyWithCollectionSize.keyWithSize(element, size);
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has exactly the same key set of the
     * given comparison {@code Multimap}.
     */
    public static <K> Matcher<Multimap<K, ?>> hasSameKeySet(final Multimap<K, ?> comparison) {
        return IsMultimapWithKeySet.hasSameKeySet(comparison.keySet());
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has a key set exactly equals to the
     * given {@linkplain Set}.
     */
    public static <K> Matcher<Multimap<K, ?>> hasSameKeySet(final Set<K> comparison) {
        return IsMultimapWithKeySet.hasSameKeySet(comparison);
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has an empty key set.
     */
    public static <K> Matcher<Multimap<K, ?>> emptyKeySet() {
        return IsMultimapWithKeySetSize.emptyKeySet();
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has a key set with size equals to
     * <code>size</code>.
     */
    public static <K> Matcher<Multimap<K, ?>> keySetWithSize(final int size) {
        return IsMultimapWithKeySetSize.keySetWithSize(size);
    }

}
