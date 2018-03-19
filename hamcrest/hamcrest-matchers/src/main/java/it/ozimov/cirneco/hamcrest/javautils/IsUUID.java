package it.ozimov.cirneco.hamcrest.javautils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.UUID;

/**
 * Is the object the same instance as another?
 * <p>
 * With respect to the class {@linkplain org.hamcrest.core.IsSame}, this versions can compare objects of two
 * different classes. So, you can assert as follows: <code>Integer integer = 10; Number number = integer;
 * <p>
 * <p>assertThat(number, sameInstance(integer));</code>
 * <p>
 * <p>This is in general very useful with any interface and one of its actual implementations.
 *
 * @since version 1.2.0 for JDK7
 */
public class IsUUID extends BaseMatcher<CharSequence> {

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} has a to String value that is a valid
     * {@linkplain UUID}.
     */
    public static Matcher UUID() {
        return new IsUUID();
    }

    @Override
    public boolean matches(final Object arg) {
        try {
            UUID.fromString(arg.toString());
        }catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("valid UUID format");
    }

}