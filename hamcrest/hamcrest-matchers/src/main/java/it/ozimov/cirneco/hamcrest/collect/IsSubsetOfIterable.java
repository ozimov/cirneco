package it.ozimov.cirneco.hamcrest.collect;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static it.ozimov.cirneco.hamcrest.collect.utils.IterableUtils.listCopy;

/**
 * Is the {@linkplain Iterable} sorted according to natural ordering?
 *
 * @since version 1.1.0 for JDK7
 */
public class IsSubsetOfIterable<K> extends TypeSafeMatcher<Iterable<? super K>> {

    private final Iterable<? super K> comparisonIterable;

    public IsSubsetOfIterable(final Iterable<? super K> comparison) {
        this.comparisonIterable = comparison;
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has all
     * the elements in the comparison {@linkplain Iterable}, without repetitions (i.e. it is a subset).
     * For instance:
     * {@code
     * List<String> comparison = Arrays.asList("bar", "bar", "foo");
     * List<String> given = Arrays.asList("bar", "bar", "bar");
     * assertThat(given, is(subsetOf(comparison));
     * }
     * returns {@code true} because each element of {@code given} is included in {@code comparison}.
     * Essentially, if the set view of the given iterables guarantee that {@code given} is a subset of {@code comparison},
     * then the matcher returns true
     */
    public static <K> Matcher<Iterable<K>> subsetOf(final Iterable<? super K> comparison) {
        return new IsSubsetOfIterable(comparison);
    }

    @Override
    public boolean matchesSafely(final Iterable<? super K> actual) {
        checkNotNull(actual);
        final Set<?> comparisonSet = ImmutableSet.copyOf(comparisonIterable);
        final Set<?> givenSet = newHashSet(actual);
        return comparisonSet.containsAll(givenSet);
    }

    @Override
    public void describeMismatchSafely(final Iterable<? super K> iterable, final Description mismatchDescription) {
        mismatchDescription.appendText("iterable was ").appendValueList("[", ", ", "]", listCopy(iterable))
                .appendText(", but the following elements ").appendValueList("[", ", ", "]",
                    notContained(iterable, comparisonIterable))
                .appendText(" are not in the superset");
    }

    private Iterable<?> notContained(Iterable<? super K> actual, Iterable<? super K> comparisonIterable) {
        final Set<?> comparisonSet = ImmutableSet.copyOf(comparisonIterable);
        final Set<?> givenSet = newHashSet(actual);
        givenSet.removeAll(comparisonSet);
        return givenSet;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("iterable is subset");
    }

}
