package it.ozimov.cirneco.hamcrest.java7;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.not;

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
 * .because("Actual object "\Test\" must not be equal to \"Something\"")
 * .assertIsNot("Something");
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
 * <p>When using no "actual" object, but just an expression, it can be used the {@linkplain #because(String)}, e.g.
 * <p>
 * <code>
 * @Test public void testSomething(){
 * boolean expression = true;
 * <p>
 * because("Expected the expression to be true")
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

    public static AssertionBuilder because(@Nonnull final String reason) {
        checkNotNull(reason);

        return new AssertionBuilder().because(reason);
    }

    public static AssertionBuilder because(@Nonnull final String reason, @Nullable Object ... reasonArgs) {
        checkNotNull(reason);

        return new AssertionBuilder().because(String.format(reason, reasonArgs));
    }

    public static <T> AssertionBuilder<T> given(@Nonnull final T actual) {
        return new AssertionBuilder<>(actual);
    }

    public static void fail(){
        assertIs(false);
    }

    public static void success(){
        assertIs(true);
    }

    public static void assumeThat(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static void assumeThatNot(final boolean expression) {
        new AssertionBuilder<>().matches(not(expression));
    }

    public static void assumeIs(final boolean expression) {
        assumeThat(expression);
    }

    public static void assumeIsNot(final boolean expression) {
        assumeThatNot(expression);
    }

    public static void assertThat(final boolean expression) {
        new AssertionBuilder<>().matches(expression);
    }

    public static void assertThatNot(final boolean expression) {
        new AssertionBuilder<>().matches(not(expression));
    }

    public static void assertIs(final boolean expression) {
        assertThat(expression);
    }

    public static void assertIsNot(final boolean expression) {
        assumeThatNot(expression);
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

        public AssertionBuilder given(@Nonnull final T actual) {
            this.actual = actual;
            return this;
        }

        public AssertionBuilder because(@Nonnull final String reason) {
            checkNotNull(reason);

            this.reason = reason;
            return this;
        }

        public void assumeThat(final boolean expression) {
            matches(expression);
        }

        public void assumeThatNot(final boolean expression) {
            matches(not(expression));
        }

        public void assumeIs(final boolean expression) {
            assumeThat(expression);
        }

        public void assumeIsNot(final boolean expression) {
            assumeThatNot(expression);
        }

        public void assertThat(final boolean expression) {
            matches(expression);
        }

        public void assertThatNot(final boolean expression) {
            matches(not(expression));
        }

        public void assertIs(final boolean expression) {
            assertThat(expression);
        }

        public void assertIsNot(final boolean expression) {
            assumeThatNot(expression);
        }

        public <T> void assumeThat(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        public <T> void assumeThatNot(@Nonnull final Matcher<? super T> matcher) {
            matches(not(matcher));
        }

        public <T> void assumeIs(@Nonnull final Matcher<? super T> matcher) {
            assumeThat(matcher);
        }

        public <T> void assumeIsNot(@Nonnull final Matcher<? super T> matcher) {
            assumeThatNot(matcher);
        }

        public <T> void assertThat(@Nonnull final Matcher<? super T> matcher) {
            matches(matcher);
        }

        public <T> void assertThatNot(@Nonnull final Matcher<? super T> matcher) {
            matches(not(matcher));
        }

        public <T> void assertIs(@Nonnull final Matcher<? super T> matcher) {
            assertThat(matcher);
        }

        public <T> void assertIsNot(@Nonnull final Matcher<? super T> matcher) {
            assertThatNot(matcher);
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