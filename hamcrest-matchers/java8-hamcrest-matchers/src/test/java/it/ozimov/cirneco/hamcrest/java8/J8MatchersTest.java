package it.ozimov.cirneco.hamcrest.java8;

import it.ozimov.cirneco.hamcrest.java8.base.IsEmptyOptional;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class J8MatchersTest {

    @Test
    public void testEmptyOptional() throws Exception {
        assertThat(J8Matchers.emptyOptional(), instanceOf(IsEmptyOptional.class));
    }

}
