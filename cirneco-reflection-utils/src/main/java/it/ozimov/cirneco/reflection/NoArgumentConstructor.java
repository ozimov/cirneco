package it.ozimov.cirneco.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for default constructors. A default constructor is a constructor with no
 * arguments.
 *
 * @since version 0.6.0 for JDK7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface NoArgumentConstructor {

}
