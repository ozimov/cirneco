package it.ozimov.cirneco.hamcrest;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertTrue;

import static org.junit.Assume.assumeThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public abstract class SameCallerMatchersTest {

    private final ClassPool cp = ClassPool.getDefault();
    private String sourceClass;
    private CtClass ctClass;

    @Before
    public void setSourceClass() throws NotFoundException {
        sourceClass = setFullyQualifiedSourceClass();

        ctClass = cp.get(sourceClass);
    }

    /**
     * Returns the fully qualified class name of the class under test.
     */
    public abstract String setFullyQualifiedSourceClass();

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final String methodName)
        throws CannotCompileException, NotFoundException {
        testMethodCallsRightMethod(expectedCalledClass, methodName, methodName, null);
    }

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final String expectedCalledMethodName,
            final String methodName) throws CannotCompileException, NotFoundException {
        testMethodCallsRightMethod(expectedCalledClass, expectedCalledMethodName, methodName, null);
    }

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final String methodName,
            final Class<?>... params) throws CannotCompileException, NotFoundException {
        testMethodCallsRightMethod(expectedCalledClass, methodName, methodName, params);
    }

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final String expectedCalledMethodName,
            final String methodName, final Class<?>... params) throws CannotCompileException, NotFoundException {
        final CtMethod method = getDeclaredMethod(methodName, params);
        testMethodCallsRightMethod(expectedCalledClass, expectedCalledMethodName, method);
    }

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final CtMethod method)
        throws CannotCompileException, NotFoundException {
        testMethodCallsRightMethod(expectedCalledClass, null, method);
    }

    /**
     * Tests that the given method only calls another method with the same signature from the given class.
     */
    public void testMethodCallsRightMethod(final Class<?> expectedCalledClass, final String expectedCalledMethodName,
            final CtMethod method) throws CannotCompileException, NotFoundException {

        // Arrange
        final String methodName = method.getName();
        final String methodSignature = method.getSignature();
        final String methodGenericSignature = method.getGenericSignature();
        final String calledInnerMethodName = (expectedCalledMethodName == null ? methodName : expectedCalledMethodName);
        final SameMethodChecker checker = new SameMethodChecker(methodSignature, methodGenericSignature,
                expectedCalledClass.getCanonicalName(), calledInnerMethodName);

        assumeThat("Expected that the method to be tested belongs to class " + method.getDeclaringClass().getName(),
            method.getDeclaringClass().getName(), is(sourceClass));

        // Act
        method.instrument(checker);

        // Assert
        assertTrue(String.format(
                "Expected that method \"%s\" with signature \"%s\" only calls method \"%s\" from class \"%s\"",
                methodName, methodSignature, calledInnerMethodName, expectedCalledClass), checker.isSame());
    }

    public CtMethod[] getAllDeclaredMethods() {
        return ctClass.getDeclaredMethods();
    }

    public CtMethod getDeclaredMethod(final String name, final Class<?>... params) throws NotFoundException {
        return params == null || params.length == 0 ? ctClass.getDeclaredMethod(name)
                                                    : ctClass.getDeclaredMethod(name, toCtClass(params));
    }

    private CtClass[] toCtClass(final Class<?>... params) throws NotFoundException {
        final List<CtClass> ctClasses = new ArrayList<>();
        for (final Class<?> aClass : params) {
            ctClasses.add(cp.get(aClass.getCanonicalName()));
        }

        return ctClasses.toArray(new CtClass[params.length]);
    }

    /**
     * Attention, if two methods are essentially the same but refer to different generic, e.g.
     *
     * <p>
     * <pre>
       public <T> getFromValue(T object);

       public <N> getFromValue(N object);
     * </pre>
     *
     * <p>the comparison will be <code>false</code>.
     */
    private class SameMethodChecker extends ExprEditor {

        private final String parentMethodSignature;
        private final String parentMethodGenericSignature;
        private final String expectedCalledClass;
        private final String expectedMethodCalledName;

        private boolean isSameMethodName;
        private boolean isSameSignature;
        private boolean isSameGenericSignature;
        private boolean isExpectedCalledClass;

        public SameMethodChecker(final String parentMethodSignature, final String parentMethodGenericSignature,
                final String expectedCalledClass, final String expectedMethodCalledName) {
            this.parentMethodSignature = parentMethodSignature;
            this.parentMethodGenericSignature = parentMethodGenericSignature;
            this.expectedCalledClass = expectedCalledClass;
            this.expectedMethodCalledName = expectedMethodCalledName;
        }

        public boolean isSame() {
            return isSameMethodName && isSameSignature && isSameGenericSignature && isExpectedCalledClass;
        }

        public void edit(final MethodCall methodCall) throws CannotCompileException {
            isSameMethodName = methodCall.getMethodName().equals(expectedMethodCalledName);
            isSameSignature = methodCall.getSignature().equals(parentMethodSignature);
            try {
                isSameGenericSignature = methodCall.getMethod().getGenericSignature() != null
                    ? methodCall.getMethod().getGenericSignature().equals(parentMethodGenericSignature) : true;
                isExpectedCalledClass = methodCall.getMethod().getDeclaringClass().getName().equals(
                        expectedCalledClass);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
