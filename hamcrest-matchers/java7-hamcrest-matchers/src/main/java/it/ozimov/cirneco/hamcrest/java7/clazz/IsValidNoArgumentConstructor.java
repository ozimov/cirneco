package it.ozimov.cirneco.hamcrest.java7.clazz;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Constructor;

/**
 * Is {@linkplain Class} in a given week day?
 *
 * @since version 0.6.0 for JDK7
 */
public class IsValidNoArgumentConstructor extends TypeSafeMatcher<Class> {

    private IsValidNoArgumentConstructor() {
    }

    /**
     * Creates a matcher that matches when the examined {@linkplain Class} has a valid default constructor with no
     * parameters.
     */
    public static Matcher<Class> hasNoArgumentConstructor() {
        return new IsValidNoArgumentConstructor();
    }

    @Override
    protected boolean matchesSafely(final Class aClass) {
        try {
            final Constructor constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final Class aClass, final Description mismatchDescription) {
        mismatchDescription.appendValue(aClass).appendText(" has not valid no-argument constructor");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a Class with valid no-argument constructor");
    }

}
