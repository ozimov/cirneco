package it.ozimov.cirneco.hamcrest.java7.collect;

import com.google.common.collect.ImmutableList;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assume.assumeThat;

public class IsSubsetOfIterableTest {

    public Matcher<Iterable<String>> isSubsetOfIterableMatcher;
    public List<String> contained = ImmutableList.of("A", "A", "B");
    public List<String> notContained = ImmutableList.of("A", "A", "B", "Z");
    public List<String> comparison = ImmutableList.of("A", "B", "C");

    @Before
    public void setUp() {
        isSubsetOfIterableMatcher = IsSubsetOfIterable.subsetOf(comparison);

        assumeThat(isSubsetOfIterableMatcher.matches(comparison), equalTo(true));
    }

    @Test
    public void testGivenContainedIterableWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        // Act
        final boolean matches = isSubsetOfIterableMatcher.matches(contained);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNotContainedIterableWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Act
        final boolean matches = isSubsetOfIterableMatcher.matches(notContained);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(
                "iterable was [<1>, <1>, <3>], but the following elements [<3>] are not in the superset",
                IsSubsetOfIterable.subsetOf(ImmutableList.of(1, 2)),
                ImmutableList.of(1, 1, 3));
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("iterable is subset", isSubsetOfIterableMatcher);
    }

}