package org.cirneco.assertions.hamcrest.base;

import com.google.common.base.Equivalence;
import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class IsEquivalentTest extends BaseMatcherTest {

    public final static String EQUIVALENCE_TO_STRING = "EQUIVALENCE_TO_STRING";
    public final static String ASSERTED_OBJECT_TO_STRING_VAL = "ASSERTED_OBJECT_TO_STRING";
    public final static String COMPARISON_OBJECT_TO_STRING_VAL = "COMPARISON_OBJECT_TO_STRING";
    public Matcher<Object> isEquivalentMatcher;
    public Matcher<Object> isEquivalentMatcherForNullValue;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    public Object assertedObject;
    @Mock
    public Object comparisonObject;
    @Mock
    public Equivalence<Object> equivalence;

    public String getMatcherSimpleName() {
        return IsEquivalent.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        isEquivalentMatcher = IsEquivalent.equivalentTo(comparisonObject, equivalence);
        isEquivalentMatcherForNullValue = IsEquivalent.equivalentTo(null, equivalence);
        when(equivalence.toString()).thenReturn(EQUIVALENCE_TO_STRING);
        when(assertedObject.toString()).thenReturn(ASSERTED_OBJECT_TO_STRING_VAL);
        when(comparisonObject.toString()).thenReturn(COMPARISON_OBJECT_TO_STRING_VAL);
    }

    @Test
    public void testGivenThatEquivalenceIsNull_WhenCreateInstance_ThenNullPointerExceptionIsThrown() {
        //Arrange
        thrown.expect(NullPointerException.class);

        //ActIsEquivalent.equivalentTo(null, equivalence)
        IsEquivalent.equivalentTo("", null);
        IsEquivalent.equivalentTo(null, equivalence);

        //Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatComparisonIsNull_WhenCreateInstance_NothingIsThrown() {
        //Act
        IsEquivalent.equivalentTo(null, equivalence);
    }

    @Test
    public void testGivenThatActualIsNull_WhenCreateInstance_NothingIsThrown() {
        //Act
        isEquivalentMatcher.matches(null);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(String.format("<%s> is not equivalent to null value according with Equivalence <%s>",
                ASSERTED_OBJECT_TO_STRING_VAL, EQUIVALENCE_TO_STRING),
                isEquivalentMatcherForNullValue, assertedObject);

        assertHasMismatchDescription(String.format("<%s> is not equivalent to <%s> according with Equivalence <%s>",
                ASSERTED_OBJECT_TO_STRING_VAL, COMPARISON_OBJECT_TO_STRING_VAL, EQUIVALENCE_TO_STRING),
                isEquivalentMatcher, assertedObject);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format(
                "a value equivalent to <%s> according with Equivalence <%s>",
                COMPARISON_OBJECT_TO_STRING_VAL, EQUIVALENCE_TO_STRING), isEquivalentMatcher);
    }

    @Test
    public void testGivenTwoEquivalentObjects_WhenEquivalentToIsCalled_ThenTrueIsReturned() throws Exception {
        //Arrange
        when(equivalence.equivalent(assertedObject, comparisonObject)).thenReturn(true);

        //Act
        boolean isEquivalent = isEquivalentMatcher.matches(assertedObject);

        //Assert
        assertThat("Expected to be equivalent", isEquivalent, is(true));
    }

    @Test
    public void testGivenTwoNonEquivalentObjects_WhenEquivalentToIsCalled_ThenFalseIsReturned() throws Exception {
        //Arrange
        when(equivalence.equivalent(assertedObject, comparisonObject)).thenReturn(false);

        //Act
        boolean isEquivalent = isEquivalentMatcher.matches(assertedObject);

        //Assert
        assertThat("Expected to be equivalent", isEquivalent, is(false));
    }

}
