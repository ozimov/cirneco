package it.ozimov.cirneco.hamcrest.java7.throwable;

import com.google.common.base.Preconditions;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matches an {@link Throwable} and its message. The message must be the same or contained in the error message.
 * If a subtype of the {@code Throwable} is provided, then
 * the matcher may return {@code true} if the message is the same of it is contained in the throwable message.
 * <p>
 * This matcher is primarily meant to be used with JUnit {@code ExpectedException}.
 * </p>
 * <p>
 * Specifically, the use case is when for the cause that thrown the {@link Throwable} one
 * want to check both the type and the message of the cause.
 * E.g.
 * </p>
 * <code>
 *
 * @Rule public ExpectedException thrown = ExpectedException.none();
 * @Test public void myTest() {
 * //Arrange
 * thrown.expectCause(UnsupportedOperationException.class, "bla");
 * <p>
 * throw new UnsupportedOperationException("Exception caused by bla");
 * }
 * </code>
 */
public class ExpectedException extends TypeSafeMatcher<Throwable> {

    private final Class<? extends Throwable> type;
    private final String expectedContainedMessage;

    /**
     * Creates a matcher for a {@link Throwable} that matches when the provided {@code Throwable} instance is the same or a subtype
     * of the given class and has the same message in the error message or the message is contained in the error message
     */
    public static Matcher<Throwable> exceptionWithMessage(Class<? extends Throwable> type, String expectedMessage) {
        return new ExpectedException(type, expectedMessage);
    }

    public ExpectedException(final Class<? extends Throwable> type, final String expectedContainedMessage) {
        Preconditions.checkNotNull(type, "Cannot provide a null Class<? extends Throwable> as Throwable's type.");
        this.type = type;
        this.expectedContainedMessage = expectedContainedMessage;
    }

    @Override
    protected boolean matchesSafely(Throwable throwable) {
        return type.isAssignableFrom(throwable.getClass())
                &&
                (
                    (expectedContainedMessage == null && throwable.getMessage() == null)
                        ||
                    (throwable.getMessage() != null && throwable.getMessage().contains(expectedContainedMessage))
                );
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expects Throwable of type ")
                .appendValue(type)
                .appendText(" and a message containing ")
                .appendValue(expectedContainedMessage);
    }

}

