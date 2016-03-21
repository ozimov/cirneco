package it.ozimov.cirneco.hamcrest.java7.clazz;

import lombok.Data;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Is {@linkplain Class} having the given annotation?
 * <p>Observe that the annotation must be available at runtime.
 *
 * @since version 0.9.0 for JDK7
 */
public class IsClassWithAnnotation extends TypeSafeMatcher<Class> {

    private final Class<? extends Annotation> annotation;

    private IsClassWithAnnotation(final Class<? extends Annotation> annotation) {
        checkNotNull(annotation);
        checkArgument(annotation.isAnnotationPresent(Retention.class), "Class retention is not supported");
        final Retention retentionAnnotation = annotation.getAnnotation(Retention.class);
        checkArgument(retentionAnnotation.value() == RetentionPolicy.RUNTIME,
                "RetentionPolicy for given annotation is not RUNTIME");

        this.annotation = annotation;
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Class} has the given runtime available annotation
     */
    public static Matcher<Class> hasRuntimeAnnotation(final Class<? extends Annotation> annotation) {
        return new IsClassWithAnnotation(annotation);
    }

    @Override
    protected boolean matchesSafely(final Class aClass) {
        try {
            return aClass.isAnnotationPresent(annotation);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final Class aClass, final Description mismatchDescription) {
        mismatchDescription.appendValue(aClass).appendText(" has not given annotation ").appendValue(annotation.toString());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Class with annotation ").appendValue(annotation.toString());
    }

}
