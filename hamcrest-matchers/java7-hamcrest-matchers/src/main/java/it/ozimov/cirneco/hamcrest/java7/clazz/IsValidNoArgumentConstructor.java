package it.ozimov.cirneco.hamcrest.java7.clazz;

import it.ozimov.cirneco.reflection.ScanNoArgumentConstructor;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Is {@linkplain Class} in a given week day?
 *
 * @since version 0.6.0 for JDK7
 */
public class IsValidNoArgumentConstructor extends TypeSafeMatcher<Class> {

    public IsValidNoArgumentConstructor() {
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
            return ScanNoArgumentConstructor.getNoArgumentConstructor(aClass).isPresent();
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
