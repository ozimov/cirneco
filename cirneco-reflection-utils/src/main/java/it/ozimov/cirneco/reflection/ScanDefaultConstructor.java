package it.ozimov.cirneco.reflection;


import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.Character.isJavaIdentifierPart;
import static java.lang.Character.isJavaIdentifierStart;

public class ScanDefaultConstructor {

    private static final Set<String> RESERVED_KEYWORDS = initReservedKeywords();

    @DefaultConstructor
    private ScanDefaultConstructor() {

    }

    public static Optional<Constructor> getDefaultConstructor(@Nonnull final Class clazz){
        checkNotNull(clazz);

        final List<ClassLoader> classLoadersList = ImmutableList.of(
                ClasspathHelper.contextClassLoader(),
                ClasspathHelper.staticClassLoader());

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new ResourcesScanner(),
                        new MethodAnnotationsScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().includePackage(clazz)));


        final Set<Constructor> constructors = reflections.getConstructorsAnnotatedWith(DefaultConstructor.class);
        checkState(constructors.size() <= 1, "The class cannot have multiple default constructors, while %d found",
                constructors.size());

        if(constructors.isEmpty()){
            return Optional.absent();
        }
        else{
            final Constructor constructor = constructors.iterator().next();
            final Class[] parameterTypes = constructor.getParameterTypes();
            checkState(parameterTypes.length==0, "Constructor '%s' has input parameters, expected none", constructor.toGenericString());
            return Optional.of(constructor);
        }
    }

    public static Set<Constructor> getAllDefaultConstructors(@Nonnull final String aPackage) {
        checkNotNull(aPackage);
        checkArgument(isValidClassIdentifier(aPackage),
                "Given package '%s' is not a valid java identifier", aPackage);


        final List<ClassLoader> classLoadersList = ImmutableList.of(
                ClasspathHelper.contextClassLoader(),
                ClasspathHelper.staticClassLoader());

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner(),
                        new MethodAnnotationsScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(aPackage))));

        final Set<Constructor> constructors = reflections.getConstructorsAnnotatedWith(DefaultConstructor.class);
        for(final Constructor constructor : constructors){
            final Class[] parameterTypes = constructor.getParameterTypes();
            checkState(parameterTypes.length==0, "Constructor '%s' has input parameters, expected none", constructor.toGenericString());
        }
        return constructors;
    }


    private static boolean isValidClassIdentifier(final String classIdentifier) {
        for (String part : classIdentifier.split("\\.")) {
            if (part.isEmpty()) {
                return false;
            }
            if (RESERVED_KEYWORDS.contains(part)){
                return false;
            }
            if (!isJavaIdentifierStart(part.codePointAt(0))) {
                return false;
            }
            final int length = part.length();
            for (int i = 1; i < length; i++) {
                if (!isJavaIdentifierPart(part.codePointAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Set<String> initReservedKeywords() {
        return ImmutableSet.of("abstract", "assert", "boolean", "break", "byte",
                "case", "catch", "char", "class", "const", "continue", "default", "do",
                "double", "else", "enum", "extends", "false", "final", "finally",
                "float", "for", "if", "goto", "implements", "import", "instanceof",
                "int", "interface", "long", "native", "new", "null", "package",
                "private", "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this", "throw",
                "throws", "transient", "true", "try", "void", "volatile", "while");
    }

}
