package it.ozimov.cirneco.hamcrest.java7.collect;

import com.google.common.collect.Iterators;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Does the {@linkplain Iterable} has the same size of another?
 * <p>
 *
 * @since version 1.1.0 for JDK7
 */
public class IsIterableWithSameSize<T> extends TypeSafeMatcher<Iterable<? super T>> {

    private final Iterable<? super T> comparisonIterable;

    public IsIterableWithSameSize(final Iterable<? super T> comparison) {
        this.comparisonIterable = comparison;
    }

    /**
     * Creates a matcher for {@link Iterable}s matching when the examined {@linkplain Iterable} has all
     * the elements in the comparison {@linkplain Iterable}, without repetitions.
     * For instance:
     * {@code
     * List<String> comparison = Arrays.asList("bar", "foo");
     * List<String> given = Arrays.asList("bar", "bar");
     * assertThat(given, sameSizeOf(comparison));
     * }
     * returns {@code true} while
     * {@code
     * List<String> comparison = Arrays.asList("bar", "foo");
     * List<String> given = Arrays.asList("bar");
     * assertThat(given, sameSizeOf(comparison));
     * }
     * returns {@code false}.
     */
    public static <T> Matcher<Iterable<T>> sameSizeOf(final Iterable<? super T> comparison) {
        return new IsIterableWithSameSize(comparison);
    }

    @Override
    public boolean matchesSafely(final Iterable<? super T> actual) {
        return sizeOf(actual) == sizeOf(comparisonIterable);
    }

    @Override
    public void describeMismatchSafely(final Iterable<? super T> iterable, final Description mismatchDescription) {
        mismatchDescription.appendText("iterable has size ").appendValue(sizeOf(iterable))
                .appendText(", while expected size was ").appendValue(sizeOf(comparisonIterable));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("iterable with size ").appendValue(sizeOf(comparisonIterable));
    }

    private int sizeOf(final Iterable<?> iterable) {
        return Iterators.size(iterable.iterator());
    }

}
