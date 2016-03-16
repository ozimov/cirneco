package it.ozimov.cirneco.hamcrest.java7;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Fluent approach to use Hamcrest matchers.
 * <p>
 * <p>The following example fits with JUnit 4.x and shows how to define an expression in a fluent way.
 * <p>
 * <code>
 * import static it.ozimov.cirneco.hamcrest.java7.Given.given;
 * import static it.ozimov.cirneco.hamcrest.java7.J7Matchers.not;
 * <p>
 * import org.junit.Test;
 * <p>
 * public class UnitTest{
 *
 * @Test public void testSomething(){
 * String actual = "Test"
 * <p>
 * given(actual)
 * .withReason("Actual object "\Test\" must not be equal to \"Something\"")
 * .assertIs(not("Something"));
 * }
 * <p>
 * }
 * </code>
 * <p>
 * The equivalent code using Hamcrest is:
 * <p>
 * <code>
 * @Test public void testSomething(){
 * String actual = "Test"
 * <p>
 * assertThat("Actual object "\Test\" must not be equal to \"Something\"")
 * actual, not("Something"));
 * }
 * </code>
 * </p>
 * <p>
 * <p>When using no "actual" object, but just an expression, it can be used the {@linkplain #withReason(String)}, e.g.
 * <p>
 * <code>
 * @Test public void testSomething(){
 * boolean expression = true;
 * <p>
 * withReason("Expected the expression to be true")
 * .assertIs(expression);
 * }
 * </code>
 * </p>
 * <p>
 * <p>Another option is to check a boolean expression directly like follows
 * <p>
 * <code>
 * @Test public void testSomething(){
 * boolean expression = true;
 * <p>
 * assertIs(expression);
 * }
 * </code>
 * </p>
 */
public class AssertFluently {

    private AssertFluently() {

    }

    public static <T> AssertionBuilder<T> given(@Nonnull final T actual) {
        return new AssertionBuilder<>(actual);
    }

    public static AssertionBuilder withReason(@Nonnull final String reason) {
        checkNotNull(reason);

        return new AssertionBuilder().withReason(reason);
    }

    public static void assumeThat(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static void assumeIs(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static void assertThat(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static void assertIs(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static class AssertionBuilder<T> {

        private String reason;

        private T actual;

        private AssertionBuilder() {
        }

        private AssertionBuilder(@Nonnull final T actual) {
            this();
            this.actual = actual;
        }

        public AssertionBuilder withReason(@Nonnull final String reason) {
            checkNotNull(reason);

            this.reason = reason;
            return this;
        }

        public void assumeThat(final boolean expression) {
            matches(expression);
        }

        public void assumeIs(final boolean expression) {
            matches(expression);
        }

        public void assertThat(final boolean expression) {
            matches(expression);
        }

        public void assertIs(final boolean expression) {
            matches(expression);
        }

        public <T> void assumeThat(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        public <T> void assumeIs(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        public <T> void assertThat(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        public <T> void assertIs(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        private <T> void matches(final Matcher<? super T> matcher) {
            checkNotNull(matcher);

            if (!matcher.matches(actual)) {
                final Description description = describeError(matcher);

                throw new AssertionError(description.toString());
            }
        }

        private <T> void matches(final boolean assertion) {
            if (!assertion) {
                if (reason != null) {
                    throw new AssertionError(reason);
                } else {
                    throw new AssertionError(assertion);
                }
            }
        }

        private <T> Description describeError(final Matcher<? super T> matcher) {
            final Description description = new StringDescription();
            if (reason != null) {
                description.appendText(reason);
            }

            description.appendText("\nExpected: ");
            description.appendDescriptionOf(matcher);
            description.appendText("\n\tgot: ");
            description.appendValue(actual);
            description.appendText("\n");
            return description;
        }

    }

}
