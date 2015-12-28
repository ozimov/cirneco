package it.ozimov.cirneco.hamcrest.iterable;

import com.google.common.collect.Multiset;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static com.google.common.base.Preconditions.checkArgument;


public class IsMultisetElementWithCount<E> extends TypeSafeMatcher<Multiset<E>> {

    private final E comparison;
    private final int size;

    public IsMultisetElementWithCount(final E comparison, final int size) {
        checkArgument(size >= 0, "size cannot be negative");

        this.comparison = comparison;
        this.size = size;
    }

    /**
     * Creates a matcher for {@linkplain Multiset} matching when the examined object <code>E</code>
     * has <code>size</code> occurrences.
     */
    public static <E> Matcher<Multiset<E>> elementWithCount(final E element, final int size) {
        return new IsMultisetElementWithCount(element, size);
    }

    @Override
    public boolean matchesSafely(final Multiset<E> actual) {
        return actual.contains(comparison) && actual.count(comparison) == size;
    }

    @Override
    public void describeMismatchSafely(final Multiset<E> multiset, final Description mismatchDescription) {
        if (multiset.contains(comparison)) {
            mismatchDescription
                    .appendText("Multiset was not containing element").appendValue(comparison);
        } else {
            mismatchDescription
                    .appendText("Multiset had element ")
                    .appendValue((Object) comparison)
                    .appendText(" with count ")
                    .appendValue(multiset.count(comparison));
        }
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Multiset with element ")
                .appendValue(comparison)
                .appendText(" with count ")
                .appendValue(size);
    }


}
