package org.cirneco.assertions.hamcrest;

import org.cirneco.assertions.hamcrest.base.IsEmptyOptional;
import org.hamcrest.Matcher;

import java.util.Optional;

/**
 * The {@code CirnecoMatchersJ8} class groups all the matchers from
 * {CirnecoMatchersJ7} plus the new ones introduced by Cirneco's Hamcrest extension for Java 8.
 * Suggested use would be to import all the static methods of this class in a unit tes.
 */
public class CirnecoMatchersJ8 extends CirnecoMatchersJ7 {

    //BASE

    /**
     * Creates a matcher that matches when the examined {@linkplain Optional}
     * contains no object.
     */
    public static Matcher<Optional> emptyOptional() {
        return IsEmptyOptional.emptyOptional();
    }


}
