package it.ozimov.cirneco.hamcrest.base;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Has the object the hashcode of another?
 *
 * @since version 0.4.0 for JDK7
 */
public class IsSameHashcode extends BaseMatcher<Object> {

    private final Object object;

    public IsSameHashcode(final Object object) {
        checkNotNull(object);
        this.object = object;
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} is the same instance as the provided
     * <code>target</code> {@linkplain Object}.
     */
    public static Matcher sameHashcode(final Object target) {
        return new IsSameHashcode(target);
    }

    @Override
    public boolean matches(final Object arg) {
        checkNotNull(arg);
        return arg.hashCode() == object.hashCode();
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("same hashcode of ").appendValue(object);
    }

}
