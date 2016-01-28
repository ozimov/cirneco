package it.ozimov.cirneco.hamcrest.java7.web;

import static java.lang.String.format;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assume.assumeTrue;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import com.jayway.jsonpath.Predicate;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsJSONWithTest extends BaseMatcherTest {

    private static final String MATCHING_JSON =
        "{\"store\": {\"book\": [{\"category\": \"Informatics\", \"title\": \"Effective Java\"}]}}";
    private static final String NON_MATCHING_JSON = "{\"store\": {\"paper\": [{\"author\": \"Einstein\"}]}}";
    private static final String NON_MATCHING_PREDICATE_JSON =
        "{\"store\": {\"book\": [{\"category\": \"Science Fiction\", \"title\": \"I, robot\"}]}}";

    private static final String JSON_PATH = "$['store']['book'][0]";

    private static final String JSON_PATH_FOR_PREDICATE = "$['store']['book'][0][?]";
    private static final Predicate PREDICATE = filter(where("category").is("Informatics"));

    private Matcher<String> withPathMatcher;
    private Matcher<String> withPathAndPredicateMatcher;

    @Before
    public void setUp() {

        // Arrange
        withPathMatcher = IsJSONWith.hasJsonPath(JSON_PATH);
        withPathAndPredicateMatcher = IsJSONWith.hasJsonPath(JSON_PATH_FOR_PREDICATE, PREDICATE);
    }

    @Test
    public void testValidJson() throws Exception {

        // Arrange
        boolean isWithPathMatcher = withPathMatcher.matches(MATCHING_JSON);
        boolean isWithPathAndPredicateMatcher = withPathAndPredicateMatcher.matches(MATCHING_JSON);

        // Assert
        assertTrue(isWithPathMatcher);
        assertTrue(isWithPathAndPredicateMatcher);
    }

    @Test
    public void testInvalidJson() throws Exception {

        assumeTrue(withPathMatcher.matches(NON_MATCHING_PREDICATE_JSON));

        // Arrange
        boolean isWithPathMatcher = withPathMatcher.matches(NON_MATCHING_JSON);
        boolean isWithPathAndPredicateMatcher = withPathAndPredicateMatcher.matches(NON_MATCHING_PREDICATE_JSON);

        // Assert
        assertFalse(isWithPathMatcher);
        assertFalse(isWithPathAndPredicateMatcher);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(format("<%s> is not JSON string with given path", NON_MATCHING_JSON),
            withPathMatcher, NON_MATCHING_JSON);
        assertHasMismatchDescription(format("<%s> is not JSON string with given path", NON_MATCHING_PREDICATE_JSON),
            withPathAndPredicateMatcher, NON_MATCHING_PREDICATE_JSON);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value that is a valid JSON with path " + JSON_PATH, withPathMatcher);
        assertIsDescribedTo(format("a value that is a valid JSON with path %s and predicate(s) [<%s>]",
                JSON_PATH_FOR_PREDICATE, PREDICATE), withPathAndPredicateMatcher);
    }

}
