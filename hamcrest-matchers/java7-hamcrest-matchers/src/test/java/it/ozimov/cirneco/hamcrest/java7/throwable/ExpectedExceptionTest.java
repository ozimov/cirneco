package it.ozimov.cirneco.hamcrest.java7.throwable;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ExpectedExceptionTest extends BaseMatcherTest {

    public Matcher<Throwable> expectedCauseMatcher;

    public static final String MESSAGE = "message";
    public static final String SUPERSET_MESSAGE = "a message"; //use contains
    public static final String ANOTHER_MESSAGE = "another value which doesn't even consider contains";
    public final Class<? extends Throwable> THROWABLE_TYPE = IllegalArgumentException.class;

    public Throwable exceptionOfGivenTypeAndMessage;
    public Throwable exceptionOfGivenTypeAndSupersetMessage;
    public Throwable exceptionOfGivenTypeButAnotherMessage;
    public Throwable anotherException;
    public Throwable supertypeExceptionWithMessage;
    public Throwable childtypeExceptionWithMessage;

    @Before
    public void setUp() {
        assertThat(ANOTHER_MESSAGE, not(containsString(MESSAGE)));
        assertThat(ANOTHER_MESSAGE, not(containsString(SUPERSET_MESSAGE)));

        // Arrange
        expectedCauseMatcher = ExpectedException.exceptionWithMessage(THROWABLE_TYPE, MESSAGE);
        
        exceptionOfGivenTypeAndMessage = new IllegalArgumentException(MESSAGE);
        exceptionOfGivenTypeAndSupersetMessage = new IllegalArgumentException(SUPERSET_MESSAGE);
        exceptionOfGivenTypeButAnotherMessage = new IllegalArgumentException(ANOTHER_MESSAGE);
        anotherException = new NullPointerException(MESSAGE);
        supertypeExceptionWithMessage = new RuntimeException(MESSAGE);
        childtypeExceptionWithMessage = new MyIllegalArgumentException(MESSAGE);
    }

    @Test
    public void testGivenThatTypeIsNullWhenCreateInstanceThenNullPointerExceptionIsThrown() {

        // Arrange
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot provide a null Class<? extends Throwable> as Throwable's type.");

        // Act
        ExpectedException.exceptionWithMessage(null, "");

        // Assert
        fail("NullPointerException expected but not thrown");
    }

    @Test
    public void testGivenThatMessageIsNullWhenCreateInstanceThenNothingHappens() {

        // Arrange

        // Act
        final Matcher<? extends Throwable> expectedExceptionMatcher = ExpectedException.exceptionWithMessage(THROWABLE_TYPE, null);

        // Assert
        assertThat(expectedExceptionMatcher, is(not(nullValue())));
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("was <java.lang.IllegalArgumentException: another value which doesn't even consider contains>",
                expectedCauseMatcher, exceptionOfGivenTypeButAnotherMessage);
        assertHasMismatchDescription("was <java.lang.NullPointerException: message>",
                expectedCauseMatcher, anotherException);
        assertHasMismatchDescription("was <java.lang.RuntimeException: message>",
                expectedCauseMatcher, supertypeExceptionWithMessage);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("expects Throwable of type <class java.lang.IllegalArgumentException> and a message containing \"message\"", expectedCauseMatcher);
    }

    @Test
    public void testGivenSameCauseAndMessageWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(exceptionOfGivenTypeAndMessage);

        // Assert
        assertThat(hasCauseAndMessage, is(true));
    }

    @Test
    public void testGivenSameCauseAndSubsetMessageWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(exceptionOfGivenTypeAndSupersetMessage);

        // Assert
        assertThat(hasCauseAndMessage, is(true));
    }


    @Test
    public void testGivenSameCauseAndDifferentMessageWhenMatchesIsCalledThenReturnFalse() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(exceptionOfGivenTypeButAnotherMessage);

        // Assert
        assertThat(hasCauseAndMessage, is(false));
    }

    @Test
    public void testGivenAnotherCauseWhenMatchesIsCalledThenReturnFalse() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(anotherException);

        // Assert
        assertThat(hasCauseAndMessage, is(false));
    }

    @Test
    public void testGivenSupertypeWithSameMessageWhenMatchesIsCalledThenReturnFalse() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(supertypeExceptionWithMessage);

        // Assert
        assertThat(hasCauseAndMessage, is(false));
    }

    @Test
    public void testGivenChildtypeWithSameMessageWhenMatchesIsCalledThenReturnTrue() throws Exception {

        // Act
        final boolean hasCauseAndMessage = expectedCauseMatcher.matches(childtypeExceptionWithMessage);

        // Assert
        assertThat(hasCauseAndMessage, is(true));
    }



    private class MyIllegalArgumentException extends IllegalArgumentException {

        public MyIllegalArgumentException(String s) {
            super(s);
        }
    }
}
