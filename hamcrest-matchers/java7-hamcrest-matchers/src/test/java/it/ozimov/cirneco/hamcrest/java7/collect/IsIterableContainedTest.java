package it.ozimov.cirneco.hamcrest.java7.collect;

import com.google.common.collect.ImmutableList;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assume.assumeThat;

public class IsIterableContainedTest {

    public Matcher<Iterable<String>> isContainedMatcher;
    public List<String> contained = ImmutableList.of("A", "A", "B");
    public List<String> notContained = ImmutableList.of("A", "A", "B", "B"); //one "B" is missing
    public List<String> comparison = ImmutableList.of("A", "A", "B", "C");

    @Before
    public void setUp() {
        isContainedMatcher = IsIterableContained.containedIn(comparison);

        assumeThat(isContainedMatcher.matches(comparison), equalTo(true));
    }

    @Test
    public void testGivenContainedIterableWhenMatchesIsCalledThenTrueIsReturned() throws Exception {
        // Act
        final boolean matches = isContainedMatcher.matches(contained);

        // Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test
    public void testGivenNotContainedIterableWhenMatchesIsCalledThenFalseIsReturned() throws Exception {
        // Act
        final boolean matches = isContainedMatcher.matches(notContained);

        // Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(
                "iterable was [<1>, <1>, <2>], but the following elements [<1>] are not present or repeated with less frequency in the comparison iterable",
                IsIterableContained.containedIn(ImmutableList.of(1, 2)),
                ImmutableList.of(1, 1, 2));
    }

    @Test
    public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo("iterable contained with repetitions", isContainedMatcher);
    }

}