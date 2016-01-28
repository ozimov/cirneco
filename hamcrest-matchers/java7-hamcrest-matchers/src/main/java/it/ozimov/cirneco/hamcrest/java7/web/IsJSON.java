package it.ozimov.cirneco.hamcrest.java7.web;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Is the value of given {@linkplain String} a valid JSON?
 *
 * <p>A JSON string must follows the <em>RFC 4627</em> standard</p>
 *
 * @since  version 0.4.0 for JDK7
 */
public class IsJSON extends TypeSafeMatcher<String> {

    /**
     * Creates a matcher for {@code String} that matches when the value could be a JSON.
     *
     * <p>For example:
     *
     * <pre>assertThat("{\"key\": 1}", validJSON())</pre>
     * </p>
     */
    public static Matcher<String> validJSON() {
        return new IsJSON();
    }

    @Override
    protected boolean matchesSafely(@Nonnull final String jsonInString) {
        return json(jsonInString);
    }

    /**
     * Matches if the given {@linkplain String} is a valid JSON.
     */
    public static boolean json(@Nonnull final String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final String item, final Description mismatchDescription) {
        mismatchDescription.appendText(String.format("<%s>", item.toString())).appendText(
            " is not a valid JSON string");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value that is a valid JSON");
    }

}
