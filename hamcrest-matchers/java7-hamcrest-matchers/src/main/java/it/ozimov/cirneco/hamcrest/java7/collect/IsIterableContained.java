package it.ozimov.cirneco.hamcrest.java7.collect;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.listCopy;
import static it.ozimov.cirneco.hamcrest.java7.collect.utils.IterableUtils.sortedListCopy;

/**
 * Is the {@linkplain Iterable} sorted according to natural ordering?
 *
 * @since version 1.1.0 for JDK7
 */
public class IsIterableContained<K> extends TypeSafeMatcher<Iterable<? super K>> {

    private final Iterable<? super K> comparisonIterable;

    public IsIterableContained(final Iterable<? super K> comparison) {
        this.comparisonIterable = comparison;
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has all
     * the elements in the comparison {@linkplain Iterable}, without repetitions.
     * For instance:
     * {@code
     * List<String> comparison = Arrays.asList("bar", "bar", "foo");
     * List<String> given = Arrays.asList("bar", "bar", "bar");
     * assertThat(given, is(containedIn(comparison));
     * }
     * returns {@code false} because element {@code bar} in {@code given} is not counted 3 times in {@code comparison}.
     * While:
     * {@code
     * List<String> comparison = Arrays.asList("bar", "bar", "bar", "foo");
     * List<String> given = Arrays.asList("bar", "bar", "bar");
     * assertThat(given, is(containedIn(comparison));
     * }
     * returns {@code true} because all the elements in {@code given} are found in {@code comparison} with the same
     * frequency.
     */
    public static <K> Matcher<Iterable<K>> containedIn(final Iterable<? super K> comparison) {
        return new IsIterableContained(comparison);
    }

    @Override
    public boolean matchesSafely(final Iterable<? super K> actual) {
        final Multiset<?> comparisonMultiSet = HashMultiset.create(comparisonIterable);
        final Multiset<?> actualMultiSet = HashMultiset.create(actual);

        for(final Object key : actualMultiSet.elementSet()) {
            if(!comparisonMultiSet.contains(key) || comparisonMultiSet.count(key) < actualMultiSet.count(key)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeMismatchSafely(final Iterable<? super K> iterable, final Description mismatchDescription) {
        mismatchDescription.appendText("iterable was ").appendValueList("[", ", ", "]", listCopy(iterable))
                .appendText(", but the following elements ").appendValueList("[", ", ", "]",
                notRepeated(iterable, comparisonIterable))
                .appendText(" are not present or repeated with less frequency in the comparison iterable");
    }

    private Iterable<?> notRepeated(Iterable<? super K> actual, Iterable<? super K> comparisonIterable) {
        final Multiset<?> comparisonMultiSet = HashMultiset.create(comparisonIterable);
        final Multiset<?> actualMultiSet = HashMultiset.create(actual);

        final Set<Object> notRepeated = newHashSet();
        for(final Object key : actualMultiSet.elementSet()) {
            if(!comparisonMultiSet.contains(key) || comparisonMultiSet.count(key) < actualMultiSet.count(key)){
                notRepeated.add(key);
            }
        }
        return notRepeated;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("iterable contained with repetitions");
    }

}