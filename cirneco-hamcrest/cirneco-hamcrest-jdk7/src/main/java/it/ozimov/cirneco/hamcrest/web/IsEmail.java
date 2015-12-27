package it.ozimov.cirneco.hamcrest.web;

import org.apache.commons.validator.routines.EmailValidator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Is the value of {@linkplain T} an email address?
 * <p>
 * The {@code toString()} method of the given object has to return a valid email address
 * according with RFC 5322 standards. However, this implementation is not guaranteed to catch
 * all possible errors in an email address and intentionally marks as non valid some valid email addresses (e.g.
 * <code>email@123.123.123.123</code> and <code>user@[IPv6:2001:db8::1]</code> would be invalid, while they are
 * absolutely valid). Please, refer to Apache Commons documentation for <code>EmailValidator</code> for more details.
 *
 * @since 0.1
 */
public class IsEmail<T> extends TypeSafeMatcher<T> {


    /**
     * Creates a matcher for {@code T}s that matches when the {@code toString()} method of
     * the given object returns a valid email address.
     * <p>
     * For example:
     * <pre>assertThat("john.doe@test.test", email())</pre>
     */
    public static <T> Matcher<T> email() {
        return new IsEmail<>();
    }

    @Override
    protected boolean matchesSafely(final T object) {
        return EmailValidator.getInstance().isValid(object.toString());
    }

    @Override
    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendText(String.format("<%s>", item.toString()))
                .appendText(" is not a valid email address");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to a valid email address");
    }

}