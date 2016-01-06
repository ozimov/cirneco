package it.ozimov.cirneco.hamcrest.guava.collect;

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.collect.Multimap;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;


public class IsMultimapKeyWithCollectionSize<K>
    extends TypeSafeMatcher<Multimap<K, ?>> {

    private final K comparison;
    private final int size;

    public IsMultimapKeyWithCollectionSize(final K comparison, final int size) {
        checkArgument(size >= 0, "size cannot be negative");

        this.comparison = comparison;
        this.size = size;
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object <code>K</code> in the key set
     * has <code>size</code> elements in the retained {@linkplain Collection}.
     */
    public static <K> Matcher<Multimap<K, ?>> keyWithSize(final K element,
        final int size) {
        return new IsMultimapKeyWithCollectionSize(element, size);
    }

    @Override public boolean matchesSafely(final Multimap<K, ?> actual) {
        return actual.containsKey(comparison) &&
            (actual.get(comparison).size() == size);
    }

    @Override public void describeMismatchSafely(final Multimap<K, ?> multimap,
        final Description mismatchDescription) {

        if (multimap.containsKey(comparison)) {
            mismatchDescription.appendText(
                "Multimap was not containing element").appendValue(comparison);
        } else {
            mismatchDescription.appendText("Multimap had element ").appendValue(
                comparison).appendText(" with collection size ").appendValue(
                multimap.get(comparison).size());
        }
    }

    @Override public void describeTo(final Description description) {
        description.appendText("a Multimap with element ").appendValue(
            comparison).appendText(" with collection size ").appendValue(size);
    }


}
