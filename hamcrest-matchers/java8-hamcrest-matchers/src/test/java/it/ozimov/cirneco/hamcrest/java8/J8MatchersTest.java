package it.ozimov.cirneco.hamcrest.java8;

import it.ozimov.cirneco.hamcrest.java8.base.IsEmptyOptional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static it.ozimov.cirneco.hamcrest.java8.J8Matchers.presentAnd;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(MockitoJUnitRunner.class)
public class J8MatchersTest {

    @Mock
    public Object object;

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

    @Test
    public void testPresentAnd() throws Exception {
        assertThat(J8Matchers.presentAnd(is(object)), instanceOf(IsEmptyOptional.class));
    }

}
