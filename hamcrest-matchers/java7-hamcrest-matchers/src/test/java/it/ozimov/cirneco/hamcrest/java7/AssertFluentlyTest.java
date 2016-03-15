package it.ozimov.cirneco.hamcrest.java7;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class AssertFluentlyTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGivenAcceptsNull() throws Exception {

        // Arrange
        final String actual = null;

        // Act+Assert
        AssertFluently.given(actual).assertThat(is(nullValue()));

    }

    @Test
    public void testGivenWithReasonAssertThatSuccess() throws Exception {

        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).withReason("Unit test").assertThat(is(expected));

    }

    @Test
    public void testGivenWithReasonAssertThatFailure() throws Exception {

        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        final String actual = "Test";
        final String expected = "Not Test";

        // Act+Assert
        AssertFluently.given(actual).withReason("Unit test").assertThat(is(expected));

        fail("Should throw an exception");
    }

    @Test
    public void testGivenWithReasonAssertIsSuccess() throws Exception {

        // Arrange
        final String actual = "Test";
        final String expected = "Test";

        // Act+Assert
        AssertFluently.given(actual).withReason("Unit test").assertIs(equalTo(expected));

    }

    @Test
    public void testGivenWithReasonAssertIsFailure() throws Exception {

        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        final String actual = "Test";
        final String expected = "Not Test";

        // Act+Assert
        AssertFluently.given(actual).withReason("Unit test").assertIs(equalTo(expected));

        fail("Should throw an exception");
    }

    @Test
    public void testWithReasonAssertThatSuccess() throws Exception {

        // Act+Assert
        AssertFluently.withReason("Unit test").assertThat(true);
    }

    @Test
    public void testWithReasonAssertThatFailure() throws Exception {

        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        // Act+Assert
        AssertFluently.withReason("Unit test").assertThat(false);

        fail("Should throw an exception");
    }

    @Test
    public void testWithReasonAssertIsSuccess() throws Exception {

        // Act+Assert
        AssertFluently.withReason("Unit test").assertIs(true);

    }

    @Test
    public void testWithReasonAssertIsFailure() throws Exception {

        // Arrange
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Unit test");

        // Act+Assert
        AssertFluently.withReason("Unit test").assertIs(false);

        fail("Should throw an exception");
    }

    @Test
    public void testAssertThatSuccess() throws Exception {

        // Act+Assert
        AssertFluently.assertThat(true);
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
    public void testAssertIsFailure() throws Exception {

        // Arrange
        expectedException.expect(AssertionError.class);

        // Act+Assert
        AssertFluently.assertIs(false);

        fail("Should throw an exception");
    }

}
