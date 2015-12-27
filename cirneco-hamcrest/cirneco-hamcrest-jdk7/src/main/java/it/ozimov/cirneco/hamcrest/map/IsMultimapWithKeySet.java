package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.Multimap;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;


public class IsMultimapWithKeySet<K> extends TypeSafeMatcher<Multimap<K, ?>> {

    private final Set<K> comparisonSet;

    public IsMultimapWithKeySet(final Set<K> comparisonSet) {
        checkNotNull(comparisonSet);

        this.comparisonSet = comparisonSet;
    }

    /**
     * Creates a matcher for {@linkplain  Multimap} matching when the examined object has exactly
     * the same key set of the given comparison {@code Multimap}.
     */
    public static <K> Matcher<Multimap<K, ?>> hasSameKeySet(final Multimap<K, ?> comparison) {
        return hasSameKeySet(comparison.keySet());
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has a key set exactly
     * equals to the given {@linkplain Set}.
     */
    public static <K> Matcher<Multimap<K, ?>> hasSameKeySet(final Set<K> comparison) {
        return new IsMultimapWithKeySet(comparison);
    }

    @Override
    public boolean matchesSafely(final Multimap<K, ?> actual) {
        return actual.keySet().equals(comparisonSet);
    }

    @Override
    public void describeMismatchSafely(final Multimap<K, ?> multimap, final Description mismatchDescription) {
        mismatchDescription.appendText("Multimap key set was ")
                .appendValueList("[", ", ", "]", multimap.keySet())
                .appendText(" while expected key set was ")
                .appendValueList("[", ", ", "]", comparisonSet);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("Multimap containing same key set as ")
                .appendValueList("[", ", ", "]", comparisonSet);
    }


}
