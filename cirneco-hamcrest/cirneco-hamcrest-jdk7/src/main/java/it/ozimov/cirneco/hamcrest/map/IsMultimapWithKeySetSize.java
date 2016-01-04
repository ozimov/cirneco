package it.ozimov.cirneco.hamcrest.map;

import com.google.common.collect.Multimap;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static com.google.common.base.Preconditions.checkArgument;


public class IsMultimapWithKeySetSize<K> extends TypeSafeMatcher<Multimap<K, ?>> {

    private final int size;

    public IsMultimapWithKeySetSize(final int size) {
        checkArgument(size >= 0, "size cannot be negative");

        this.size = size;
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has an empty key set.
     */
    public static <K> Matcher<Multimap<K, ?>> emptyKeySet() {
        return new IsMultimapWithKeySetSize(0);
    }

    /**
     * Creates a matcher for {@linkplain Multimap} matching when the examined object has a key set with
     * size equals to <code>size</code>.
     */
    public static <K> Matcher<Multimap<K, ?>> keySetWithSize(final int size) {
        return new IsMultimapWithKeySetSize(size);
    }

    @Override
    public boolean matchesSafely(final Multimap<K, ?> actual) {
        return actual.keySet().size() == size;
    }

    @Override
    public void describeMismatchSafely(final Multimap<K, ?> multimap, final Description mismatchDescription) {
        mismatchDescription
                .appendText(" Multimap had key set with ")
                .appendValue(multimap.keySet().size())
                .appendText(" elements");
    }

    @Override
    public void describeTo(final Description description) {
        if (size == 0) {
            description
                    .appendText("a Multimap with no elements");
        } else {
            description
                    .appendText("a Multimap with ").appendValue(size).appendText(" elements");

        }
    }


}
