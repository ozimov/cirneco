package it.ozimov.cirneco.hamcrest.java8;

import it.ozimov.cirneco.hamcrest.java8.base.IsEmptyOptional;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class J8MatchersTest {

    @Test
    public void testEmptyOptional() throws Exception {
        assertThat(J8Matchers.emptyOptional(), instanceOf(IsEmptyOptional.class));
    }

    @Test
    public void testNotPresent() throws Exception {
        //Arrange
        final Optional emptyOptional = Optional.empty();

        //Assert
        assertThat(emptyOptional, J8Matchers.notPresent());
    }

    @Test
    public void testPresent() throws Exception {
        //Arrange
        final Optional<Object> emptyOptional = Optional.of(new Object());

        //Assert
        assertThat(emptyOptional, J8Matchers.present());
    }
}
