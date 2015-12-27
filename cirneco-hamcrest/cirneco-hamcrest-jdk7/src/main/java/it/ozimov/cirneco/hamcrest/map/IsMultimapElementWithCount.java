package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.Multimap;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public class IsMultimapElementWithCount<K> extends TypeSafeMatcher<Multimap<K, ?>> {

    private final K comparison;
    private final int size;

    public IsMultimapElementWithCount(final K comparison, final int size) {
        checkNotNull(comparison);
        checkArgument(size >= 0, "size cannot be negative");

        this.comparison = comparison;
        this.size = size;
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object <code>K</code> in the key set
     * has one element in the retained {@linkplain Collection}.
     */
    public static <K> Matcher<Multimap<K, ?>> elementCollectionWithSizeOne(final K element) {
        return new IsMultimapElementWithCount(element, 1);
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object <code>K</code> in the key set
     * has <code>size</code> elements in the retained {@linkplain Collection}.
     */
    public static <K> Matcher<Multimap<K, ?>> elementCollectionWithSize(final K element, final int size) {
        return new IsMultimapElementWithCount(element, size);
    }

    @Override
    public boolean matchesSafely(final Multimap<K, ?> actual) {
        return actual.containsKey(comparison) && actual.get(comparison).size() == size;
    }

    @Override
    public void describeMismatchSafely(final Multimap<K, ?> multimap, final Description mismatchDescription) {
        if (multimap.containsKey(comparison)) {
            mismatchDescription
                    .appendText("Multimap was not containing element").appendValue(comparison);
        } else {
            mismatchDescription
                    .appendText("Multimap had element ")
                    .appendValue(comparison)
                    .appendText(" with count ")
                    .appendValue(multimap.get(comparison).size());
        }
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Multimap with element ")
                .appendValue(comparison)
                .appendText(" with count ")
                .appendValue(size);
    }


}
