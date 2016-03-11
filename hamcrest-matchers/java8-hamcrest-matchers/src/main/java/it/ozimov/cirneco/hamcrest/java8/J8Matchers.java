package it.ozimov.cirneco.hamcrest.java8;

import it.ozimov.cirneco.hamcrest.java7.J7Matchers;
import it.ozimov.cirneco.hamcrest.java8.base.IsEmptyOptional;
import org.hamcrest.Matcher;

import java.util.Optional;

/**
 * The {@code J8Matchers} class groups all the matchers from {J7Matchers} plus the new ones introduced by Cirneco's
 * Hamcrest extension for Java 8. Suggested use would be to import all the static methods of this class in a unit tes.
 *
 * @since version 0.1 for JDK8
 */
public class J8Matchers extends J7Matchers {

    // BASE

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional} contains no object.
     */
    public static Matcher<Optional> emptyOptional() {
        return IsEmptyOptional.emptyOptional();
    }

}
