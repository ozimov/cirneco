package it.ozimov.cirneco.hamcrest.clazz;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import lombok.Data;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;

import static it.ozimov.cirneco.hamcrest.AssertFluently.assumeThat;
import static java.lang.String.format;
import static org.junit.Assert.fail;

public class IsClassWithAnnotationTest extends BaseMatcherTest {

    private Class<? extends Annotation> runtimeAnnotation;
    private Class<? extends Annotation> sourceCodeAnnotation;
    private Matcher<Class> isClassWithAnnotationMatcher;
    private Class validClass = TestClassWithNonnullAnnotation.class;
    private Class nonValidClass = TestClassWithNoNonnullAnnotation.class;

    @Before
    public void setUp() throws Exception {
        // Arrange
        runtimeAnnotation = Nonnull.class;
        assumeThat(runtimeAnnotation.isAnnotation());
        sourceCodeAnnotation = Data.class;
        assumeThat(sourceCodeAnnotation.isAnnotation());

        isClassWithAnnotationMatcher = IsClassWithAnnotation.hasRuntimeAnnotation(runtimeAnnotation);
    }

    @Test
    public void testGivenValidAnnotatedClassWhenMatchesIsCalledThenReturnTrue() throws Exception {
        // Act
        final boolean matches = isClassWithAnnotationMatcher.matches(validClass);

        // Arrange
        assertMatches("Not given a valid class", matches);
    }

    @Test
    public void testGivenNonAnnotatedClassWhenMatchesIsCalledThenReturnFalse() throws Exception {
        // Act
        final boolean matches = isClassWithAnnotationMatcher.matches(nonValidClass);

        // Arrange
        assertDoesNotMatch("Given a valid class", matches);
    }

    @Test
    public void testGivenNullAnnotationThenIllegalArgumentExceptionIsThrown() throws Exception {
        //Arrange
        thrown.expect(NullPointerException.class);

        // Act
        IsClassWithAnnotation.hasRuntimeAnnotation(null);

        // Arrange
        fail("NullPointerException expected");
    }

    @Test
    public void testGivenNonRuntimeAvailableAnnotationThenIllegalArgumentExceptionIsThrown() throws Exception {
        //Arrange
        thrown.expect(IllegalArgumentException.class);

        // Act
        IsClassWithAnnotation.hasRuntimeAnnotation(sourceCodeAnnotation);

        // Arrange
        fail("IllegalArgumentException expected");
    }


    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(format("<%s> has not given annotation \"%s\"",
                nonValidClass, runtimeAnnotation.toString()), isClassWithAnnotationMatcher, nonValidClass);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo(String.format("a Class with annotation \"%s\"", runtimeAnnotation.toString()), isClassWithAnnotationMatcher);
    }

}