package it.ozimov.cirneco.hamcrest.guava.collect;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.junit.Assert.fail;

import static org.junit.Assume.assumeThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;


public class IsMultimapWithKeySetTest extends BaseMatcherTest {

    public Multimap<String, Object> comparisonMultimap;
    public Set<String> comparisonKeySet;

    public Multimap<String, Object> multimap;

    public Matcher<Multimap<String, ?>> isMultimapWithKeySetByMultimapMatcher;
    public Matcher<Multimap<String, ?>> isMultimapWithKeySetBySetMatcher;

    @Before public void mapUp() {

        //Arrange
        multimap = HashMultimap.create();

        comparisonMultimap = HashMultimap.create();
        comparisonMultimap.put("A", "1");
        comparisonMultimap.put("B", "1");
        comparisonMultimap.put("B", "2");
        comparisonMultimap.put("C", "1");
        comparisonMultimap.put("C", "2");
        comparisonMultimap.put("C", "3");
        comparisonKeySet = comparisonMultimap.keySet();
        assumeThat(comparisonKeySet, is(comparisonMultimap.keySet()));

        isMultimapWithKeySetByMultimapMatcher = IsMultimapWithKeySet
            .hasSameKeySet(comparisonMultimap);
        isMultimapWithKeySetBySetMatcher = IsMultimapWithKeySet.hasSameKeySet(
                comparisonKeySet);
    }

    @Test public void testGivenThatMultimapIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown()
        throws Exception {

        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsMultimapWithKeySet.hasSameKeySet((Multimap) null);

        //Assert
        fail("NullPointerException expected");
    }

    @Test public void testGivenThatSetIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown()
        throws Exception {

        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        IsMultimapWithKeySet.hasSameKeySet((Set) null);

        //Assert
        fail("NullPointerException expected");
    }

    @Test public void testGivenAMultimapWithExpectedKeySetOfAnotherMultimapWhenMatchesIsCalledThenTrueIsReturned()
        throws Exception {

        //Arrange
        multimap.put("A", "");
        multimap.put("B", "");
        multimap.put("C", "");
        assumeThat(multimap.keySet(), is(comparisonMultimap.keySet()));

        //Act
        final boolean matches = isMultimapWithKeySetByMultimapMatcher.matches(
                multimap);

        //Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test public void testGivenAMultimapWithNonExpectedKeySetOfAnotherMultimapWhenMatchesIsCalledThenFalseIsReturned()
        throws Exception {

        //Arrange
        assumeThat(multimap.keySet(), not(is(comparisonMultimap.keySet())));

        //Act
        final boolean matches = isMultimapWithKeySetByMultimapMatcher.matches(
                multimap);

        //Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test public void testGivenAMultimapWithExpectedKeySetWhenMatchesIsCalledThenTrueIsReturned()
        throws Exception {

        //Arrange
        multimap.put("A", "");
        multimap.put("B", "");
        multimap.put("C", "");
        assumeThat(multimap.keySet(), is(comparisonKeySet));

        //Act
        final boolean matches = isMultimapWithKeySetByMultimapMatcher.matches(
                multimap);

        //Assert
        BaseMatcherTest.assertMatches(matches);
    }

    @Test public void testGivenAMultimapWithNonExpectedKeySetWhenMatchesIsCalledThenFalseIsReturned()
        throws Exception {

        //Arrange
        assumeThat(multimap.keySet(), not(is(comparisonKeySet)));

        //Act
        final boolean matches = isMultimapWithKeySetByMultimapMatcher.matches(
                multimap);

        //Assert
        BaseMatcherTest.assertDoesNotMatch(matches);
    }

    @Test public void testDescribeMismatchSafely() throws Exception {
        BaseMatcherTest.assertHasMismatchDescription(
            "Multimap key set was [] while expected key set was [\"A\", \"B\", \"C\"]",
            isMultimapWithKeySetByMultimapMatcher, multimap);
        BaseMatcherTest.assertHasMismatchDescription(
            "Multimap key set was [] while expected key set was [\"A\", \"B\", \"C\"]",
            isMultimapWithKeySetBySetMatcher, multimap);
    }

    @Test public void testDescribeTo() throws Exception {
        BaseMatcherTest.assertIsDescribedTo(
            "a Multimap containing same key set as [\"A\", \"B\", \"C\"]",
            isMultimapWithKeySetByMultimapMatcher);
        BaseMatcherTest.assertIsDescribedTo(
            "a Multimap containing same key set as [\"A\", \"B\", \"C\"]",
            isMultimapWithKeySetBySetMatcher);
    }

}
