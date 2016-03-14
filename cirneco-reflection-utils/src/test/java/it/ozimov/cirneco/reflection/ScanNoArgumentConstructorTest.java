package it.ozimov.cirneco.reflection;

import com.google.common.base.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ScanNoArgumentConstructorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetNoArgumentConstructorForClass() throws Exception {
        //Act
        final Optional<Constructor> constructorOptional =
                ScanNoArgumentConstructor.getNoArgumentConstructor(testing.reflection.clazz.ok.TestClass.class);

        //Assert
        assertThat("Expected only one class", constructorOptional.get(), not(is(nullValue())));
        final Constructor constructor = constructorOptional.get();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testGetNoArgumentConstructorForClassFailsOnClassConstructorWithParams() throws Exception {
        //Arrange
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Constructor 'private testing.reflection.clazz.with.constructor.with.params.TestClass(java.lang.String)'" +
                " has input parameters, expected none");

        //Act
        ScanNoArgumentConstructor.getNoArgumentConstructor(testing.reflection.clazz.with.constructor.with.params.TestClass.class);

        //Assert
        fail("Expected IllegalStateException");
    }

    @Test
    public void testGetNoArgumentConstructorForClassEmptyOnClassWithoutAnnotations() throws Exception {
        //Act
        final Optional<Constructor> constructorOptional =
                ScanNoArgumentConstructor.getNoArgumentConstructor(testing.reflection.clazz.has.no.annotations.TestClass.class);

        //Assert
        assertFalse(constructorOptional.isPresent());
    }

    @Test
    public void testGetNoArgumentConstructorForClassFailsOnNullClass() throws Exception {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        ScanNoArgumentConstructor.getNoArgumentConstructor(null);

        //Assert
        fail("Expected NullPointerException");
    }

    @Test
    public void testGetAllNoArgumentConstructors() throws Exception {
        //Act
        final Set<Constructor> constructorSet =
                ScanNoArgumentConstructor.getAllNoArgumentConstructors("testing.reflection.clazz.ok");

        //Assert
        assertThat("Expected only one class", constructorSet, hasSize(1));

        for (final Constructor constructor : constructorSet) {
            constructor.setAccessible(true);
            constructor.newInstance();
        }
    }


    @Test
    public void testGetAllNoArgumentConstructorsWithNoAnnotations() throws Exception {
        //Act
        final Set<Constructor> constructorSet =
                ScanNoArgumentConstructor.getAllNoArgumentConstructors("testing.reflection.clazz.has.no.annotations");

        //Assert
        assertThat("Expected no classes matching", constructorSet, hasSize(0));
    }

    @Test
    public void testGetAllNoArgumentConstructorsFailsOnConstructorWithParams() throws Exception {
        //Arrange
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Constructor 'private testing.reflection.clazz.with.constructor.with.params.TestClass(java.lang.String)'" +
                " has input parameters, expected none");

        //Act
        ScanNoArgumentConstructor.getAllNoArgumentConstructors("testing.reflection.clazz.with.constructor.with.params");

        //Assert
        fail("Expected IllegalStateException");
    }

    @Test
    public void testGetAllNoArgumentConstructorsFailsOnBadPackage() throws Exception {
        //Arrange
        final String packageName = "private.package.volatile";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Given package '" + packageName + "' is not a valid java identifier");

        //Act
        ScanNoArgumentConstructor.getAllNoArgumentConstructors(packageName);

        //Assert
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testGetAllNoArgumentConstructorsFailsOnNullPackage() throws Exception {
        //Arrange
        thrown.expect(NullPointerException.class);

        //Act
        ScanNoArgumentConstructor.getAllNoArgumentConstructors(null);

        //Assert
        fail("Expected NullPointerException");
    }

}