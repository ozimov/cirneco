package it.ozimov.cirneco.hamcrest.iterable;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

public class IsMultisetElementWithCountTest extends BaseMatcherTest {

    public String comparison;

    public Multiset<String> multiset;
    public int expectedCountGeneralMatcher;

    public Matcher<Multiset<String>> isMultisetElementWithCountMatcher;

    @Before
    public void setUp() {
        //Arrange
        multiset = HashMultiset.create();
        comparison = "ComparisonObject";

        expectedCountGeneralMatcher = 10;
        isMultisetElementWithCountMatcher = IsMultisetElementWithCount.elementWithCount(comparison, expectedCountGeneralMatcher);
    }

    @Test
    public void testGivenThatSizeIsNegativeWhenCreateInstanceThenNullPointerExceptionIsThrown() throws Exception {
        //Arrange
        thrown.expect(IllegalArgumentException.class);
        final int negativeSize = -1;
        assumeThat(negativeSize, lessThan(0));

        //Act
        IsMultisetElementWithCount.elementWithCount(comparison, -1);

        //Assert
        fail("IllegalArgumentException expected");
    }

    @Test
    public void testGivenAMultisetWithElementWithCountTenWhenMatchesForCountTenIsCalledThenTrueIsReturned() throws Exception {
        //Arrange
        multiset.add(comparison);
        multiset.setCount(comparison, expectedCountGeneralMatcher);

        //Act
        final boolean matches = isMultisetElementWithCountMatcher.matches(multiset);

        //Assert
        assertMatches(matches);
    }

    @Test
    public void testGivenAMultisetWithElementWithCountOneWhenMatchesForCountTenIsCalledThenFalseIsReturned() throws Exception {
        //Arrange
        multiset.add(comparison);

        //Act
        final boolean matches = isMultisetElementWithCountMatcher.matches(multiset);

        //Assert
        assertDoesNotMatch(matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(String.format("Multiset had element \"%s\" with count <0>", comparison),
                isMultisetElementWithCountMatcher, multiset);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format("a Multiset with element \"%s\" with count <%d>", comparison, expectedCountGeneralMatcher),
                isMultisetElementWithCountMatcher);
    }

}