package it.ozimov.cirneco.hamcrest.base;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

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
 * @since version 0.1 for JDK7
 */
public class IsSame extends BaseMatcher<Object> {

    private final Object object;

    public IsSame(final Object object) {
        this.object = object;
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain Object} is the same instance as the provided
     * <code>target</code> {@linkplain Object}.
     */
    public static Matcher sameInstance(final Object target) {
        return new IsSame(target);
    }

    @Override
    public boolean matches(final Object arg) {
        return arg == object;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("same instance of ").appendValue(object);
    }

}
