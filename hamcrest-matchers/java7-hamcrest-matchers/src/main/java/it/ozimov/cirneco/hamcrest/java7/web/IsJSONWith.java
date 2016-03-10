package it.ozimov.cirneco.hamcrest.java7.web;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import static com.jayway.jsonpath.JsonPath.parse;

import javax.annotation.Nonnull;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.Predicate;

import net.minidev.json.JSONArray;

/**
 * Is the value of given {@linkplain String} a valid JSON?
 *
 * <p>Verify that the given text matches a given JSON pattern using the <a href="https://github.com/jayway/JsonPath">
 * JsonPath library</a>. Please, refer to the library at version 2.1.0 to see how to define a pattern.</p>
 *
 * @since  version 0.4.0 for JDK7
 */
public class IsJSONWith extends TypeSafeMatcher<String> {

    private final String jsonPath;

    private final Predicate[] predicates;

    private IsJSONWith(@Nonnull final String jsonPath) {
        this(jsonPath, new Predicate[] {});
    }

    private IsJSONWith(@Nonnull final String jsonPath, @Nonnull final Predicate... predicates) {
        checkNotNull(jsonPath);
        checkNotNull(predicates);

        this.jsonPath = jsonPath;
        this.predicates = predicates;
    }

    /**
     * Creates a matcher for {@code String} that matches when the value could be a JSON matching a given pattern.
     *
     * <p>The pattern is the same defined by <em>JayWay JsonPath</em>.</p>
     *
     * <p>
     * <p>For example: Given a Json like {@code Sgtring JSON = "{\"store\": {\"book\": [{\"title\": \"Effective Java\"}]}}";}
     * then the matcher can be used as follows
     *
     * <pre>assertThat(JSON, hasJsonPath($.store.book[0].title))</pre>
     *
     * or
     *
     * <pre>assertThat(JSON, hasJsonPath("$['store']['book'][0]['title']"")</pre>
     *
     * that will return true because the jsonPath matches the string.</p>
     *
     * @see  <a href="https://github.com/jayway/JsonPath">JsonPath project home</a> for more details.
     */
    public static Matcher<String> hasJsonPath(@Nonnull final String jsonPath) {
        return new IsJSONWith(jsonPath);
    }

    /**
     * Creates a matcher for {@code String} that matches when the value could be a JSON matching a given pattern
     * ans under the given list of predicates.
     *
     * <p>The pattern is the same defined by <em>JayWay JsonPath</em>.</p>
     *
     * <p>
     * <p>For example: Given a Json like {@code String JSON = "{\"store\": {\"book\": [{\"title\": \"Effective Java\",}]}}";}
     * then the matcher can be used as follows
     *
     * <pre>assertThat(JSON, hasJsonPath("$.store.book[0].title", filter(where("title").is("Scary Coding"))))</pre>
     *
     * or
     *
     * <pre>assertThat(JSON, hasJsonPath("$['store']['book'][0]['title']", filter(where("title").is("Scary Coding")))</pre>
     *
     * that will return true because the jsonPath matches the string.</p>
     *
     * @see  <a href="https://github.com/jayway/JsonPath">JsonPath project home</a> for more details.
     */
    public static Matcher<String> hasJsonPath(@Nonnull final String jsonPath, final Predicate... predicates) {
        return new IsJSONWith(jsonPath, predicates);
    }

    @Override
    protected boolean matchesSafely(@Nonnull final String jsonInString) {
        checkArgument(IsJSON.json(jsonInString),
            "Trying to validate a non-JSON String. Please adhere to RFC 4627 standard");

        final DocumentContext documentContext = parse(jsonInString);
        try {
            if (predicates.length == 0) {
                return documentContext.read(jsonPath) != null;
            } else {
                final Object readJson = documentContext.read(jsonPath, predicates);
                return !(readJson != null && readJson instanceof JSONArray && ((JSONArray) readJson).isEmpty());
            }
        } catch (PathNotFoundException e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final String item, final Description mismatchDescription) {
        mismatchDescription.appendText(String.format("<%s>", item.toString())).appendText(
            " is not JSON string with given path");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value that is a valid JSON with path ").appendText(jsonPath);
        if (predicates.length != 0) {
            description.appendText(" and predicate(s) ").appendValueList("[", ", ", "]", predicates);
        }
    }

}
