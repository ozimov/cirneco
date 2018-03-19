package it.ozimov.cirneco.hamcrest.base;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assume.assumeThat;

public class IsPresentOptionalAndTest extends BaseMatcherTest {

    public String rightValue = "FOO";
    public String wrongValue = "BAR";
    public Matcher<String> isEqualMatcher;
    public Matcher<Optional<String>> isPresentOptionalAndMatcher;

    @Before
    public void setUp() {
        // Arrange
        assumeThat(rightValue, not(is(wrongValue)));

        isEqualMatcher = Matchers.equalTo(rightValue);
        isPresentOptionalAndMatcher = IsPresentOptionalAnd.presentAnd(isEqualMatcher);
    }

    @Test
    public void testDescribeMismatchSafelyForNotPresent() throws Exception {
        assertHasMismatchDescription("is not present", isPresentOptionalAndMatcher, Optional.empty());
    }

    @Test
    public void testDescribeMismatchSafelyForPresentButNotMatchingMatcher() throws Exception {
        //Arrange
        final Description description = new StringDescription();
        isEqualMatcher.describeMismatch(wrongValue, description);
        final String andMatcherMismatch = description.toString().trim();

        //Assert
        assertHasMismatchDescription("is present, but " + andMatcherMismatch,
                isPresentOptionalAndMatcher, Optional.of(wrongValue));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("is present and " + isEqualMatcher.toString(), isPresentOptionalAndMatcher);
    }

    @Test
    public void testFailureBecauseOfEmptyOptional() throws Exception {
        // Arrange
        final Optional emptyOptional = Optional.empty();

        // Act
        final boolean matches = isPresentOptionalAndMatcher.matches(emptyOptional);

        // Assert
        assertDoesNotMatch("Expected to be withoutRepetitions", matches);
    }

    @Test
    public void testFailureBecauseOfWrongContent() throws Exception {
        // Arrange
        final Optional nonEmptyOptional = Optional.of(wrongValue);

        // Act
        final boolean matches = isPresentOptionalAndMatcher.matches(nonEmptyOptional);

        // Assert
        assertDoesNotMatch("Expected to contains a wrong object", matches);
    }


    @Test
    public void testSuccess() throws Exception {
        // Arrange
        final Optional nonEmptyOptional = Optional.of(rightValue);

        // Act
        final boolean matches = isPresentOptionalAndMatcher.matches(nonEmptyOptional);

        // Assert
        assertMatches("Expected to contains the right object", matches);
    }


}