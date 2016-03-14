package it.ozimov.cirneco.hamcrest.java7.clazz;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;

public class IsValidNoArgumentConstructorTest extends BaseMatcherTest {

    public Matcher<Class> isValidNoArgumentConstructorMatcher;
    private Class validClass = testing.reflection.clazz.ok.TestClass.class;
    private Class nonValidClass = testing.reflection.clazz.with.constructor.with.params.TestClass.class;

    @Before
    public void setUp() throws Exception {
        // Arrange
        isValidNoArgumentConstructorMatcher = IsValidNoArgumentConstructor.hasNoArgumentConstructor();
    }

    @Test
    public void testGivenValidAnnotatedClassWhenMatchesIsCalledThenReturnTrue() throws Exception {
        // Act
        final boolean matches = isValidNoArgumentConstructorMatcher.matches(validClass);

        // Arrange
        assertMatches("Not given a valid annotated class", matches);
    }

    @Test
    public void testGivenNonValidYamlStringWhenMatchesIsCalledThenReturnFalse() throws Exception {
        // Act
        final boolean matches = isValidNoArgumentConstructorMatcher.matches(nonValidClass);

        // Arrange
        assertDoesNotMatch("Given a valid annotated class", matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(format("<%s> has not valid no-argument constructor",
                nonValidClass), isValidNoArgumentConstructorMatcher, nonValidClass);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a Class with valid no-argument constructor", isValidNoArgumentConstructorMatcher);

    }

}