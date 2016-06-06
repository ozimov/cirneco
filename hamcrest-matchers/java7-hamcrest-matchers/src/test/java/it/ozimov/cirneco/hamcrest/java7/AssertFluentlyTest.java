package it.ozimov.cirneco.hamcrest.java7;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static it.ozimov.cirneco.hamcrest.java7.clazz.IsValidNoArgumentConstructor.hasNoArgumentConstructor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class AssertFluentlyTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testConstructors() throws Exception {
        // Arrange
        assertThat(AssertFluently.class, hasNoArgumentConstructor());
        assertThat(AssertFluently.AssertionBuilder.class, hasNoArgumentConstructor());
    }

    @Test
    public void testGivenAcceptsNull() throws Exception {
        // Arrange
        final String actual = null;

        // Act+Assert
        AssertFluently.given(actual).assertThat(is(nullValue()));
    }

    @Test
    public void testAssumeThat() throws Exception {
        // Act+Assert
        AssertFluently.assumeThat(true);
    }

    @Test
    public void testAssumeThatNot() throws Exception {
        // Act+Assert
        AssertFluently.assumeThatNot(false);
    }

    @Test
    public void testGivenAssumeThat() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeThat(true);
    }

    @Test
    public void testGivenAssumeThatNot() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeThatNot(false);
    }

    @Test
    public void testGivenAssumeThatWithMatcher() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeThat(equalTo(true));
    }

    @Test
    public void testGivenAssumeThatNotWithMatcher() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeThatNot(equalTo(false));
    }

    @Test
    public void testAssumeIs() throws Exception {
        // Act+Assert
        AssertFluently.assumeIs(true);
    }

    @Test
    public void testAssumeIsNot() throws Exception {
        // Act+Assert
        AssertFluently.assumeIsNot(false);
    }

    @Test
    public void testGivenAssumeIs() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeIs(true);
    }

    @Test
    public void testGivenAssumeIsNot() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeIsNot(false);
    }

    @Test
    public void testGivenAssumeIsWithMatcher() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeIs(equalTo(true));
    }

    @Test
    public void testGivenAssumeIsNotWithMatcher() throws Exception {
        // Act+Assert
        AssertFluently.given(true).assumeIsNot(equalTo(false));
    }

    @Test
    public void testBecauseGivenAssumeIs() throws Exception {
        // Act+Assert
        AssertFluently.because("Unit test").given(true).assumeIs(true);
    }

    @Test
    public void testBecauseGivenAssumeThat() throws Exception {
        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.because("Unit test").given(actual).assumeThat(is(expected));
    }

    @Test
    public void testBecauseFailsOnNullMessage() throws Exception {
        //Arrange
        expectedException.expect(NullPointerException.class);

        //Act
        AssertFluently.because(null).assertIs(true);

        //Assert
        fail("NullPointerException expected");
    }

    @Test
    public void testBecauseFailsOnNullMessageEvenWithParams() throws Exception {
        //Arrange
        expectedException.expect(NullPointerException.class);

        //Act
        AssertFluently.because(null, "param").assertIs(true);

        //Assert
        fail("NullPointerException expected");
    }

    @Test
    public void testBecauseSuccessOnFormattingWithParams() throws Exception {
        //Arrange
        final Object[] params = {"Param 1", new Integer(10)};

        //Act
        AssertFluently.because("%s has value %d", params).assertIs(true);
    }

    @Test
    public void testGivenBecauseAssertThatSuccess() throws Exception {
        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertThat(is(expected));

    }

    @Test
    public void testGivenBecauseAssertThatNotSuccess() throws Exception {
        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertThatNot(is(not(expected)));
    }

    @Test
    public void testGivenBecauseAssertThatFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        final String actual = "Test";
        final String expected = "Not Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertThat(is(expected));

        fail("Should throw an exception");
    }

    @Test
    public void testGivenBecauseAssertIsSuccess() throws Exception {
        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertIs(equalTo(expected));

    }

    @Test
    public void testGivenBecauseAssertIsNotSuccess() throws Exception {
        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertIsNot(not(equalTo(expected)));

    }

    @Test
    public void testGivenBecauseAssertIsFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        final String actual = "Test";
        final String expected = "Not Test";

        // Act+Assert
        AssertFluently.given(actual).because("Unit test").assertIs(equalTo(expected));

        fail("Should throw an exception");
    }

    @Test
    public void testBecauseAssertThatSuccess() throws Exception {
        // Act+Assert
        AssertFluently.because("Unit test").assertThat(true);
    }

    @Test
    public void testBecauseAssertThatNotSuccess() throws Exception {
        // Act+Assert
        AssertFluently.because("Unit test").assertThatNot(false);
    }

    @Test
    public void testBecauseAssertThatFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        // Act+Assert
        AssertFluently.because("Unit test").assertThat(false);

        fail("Should throw an exception");
    }

    @Test
    public void testBecauseAssertIsSuccess() throws Exception {
        // Act+Assert
        AssertFluently.because("Unit test").assertIs(true);
    }

    @Test
    public void testBecauseAssertIsNotSuccess() throws Exception {
        // Act+Assert
        AssertFluently.because("Unit test").assertIsNot(false);
    }

    @Test
    public void testBecauseAssertIsFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        // Act+Assert
        AssertFluently.because("Unit test").assertIs(false);

        fail("Should throw an exception");
    }

    @Test
    public void testAssertThatSuccess() throws Exception {
        // Act+Assert
        AssertFluently.assertThat(true);
    }

    @Test
    public void testAssertThatNotSuccess() throws Exception {
        // Act+Assert
        AssertFluently.assertThatNot(false);
    }

    @Test
    public void testAssertThatFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);

        // Act+Assert
        AssertFluently.assertThat(false);

        fail("Should throw an exception");
    }

    @Test
    public void testAssertIsSuccess() throws Exception {
        // Act+Assert
        AssertFluently.assertIs(true);
    }

    @Test
    public void testAssertIsNotSuccess() throws Exception {
        // Act+Assert
        AssertFluently.assertIsNot(false);
    }

    @Test
    public void testAssertIsFailure() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);

        // Act+Assert
        AssertFluently.assertIs(false);

        fail("Should throw an AssertionError exception");
    }

    @Test
    public void testFailMustThrowAssertionError() throws Exception {
        // Arrange
        expectedException.expect(AssertionError.class);

        // Act+Assert
        AssertFluently.fail();

        fail("Should throw an AssertionError exception");
    }

    @Test
    public void testSuccessNotFails() throws Exception {
        // Act+Assert
        AssertFluently.success();
    }

}
