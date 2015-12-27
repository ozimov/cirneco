package it.ozimov.cirneco.hamcrest;

import it.ozimov.cirneco.hamcrest.base.IsEmptyOptional;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class CirnecoMatchersJ8Test {

    @Test
    public void testEmptyOptional() throws Exception {
        assertThat(CirnecoMatchersJ8.emptyOptional(), instanceOf(IsEmptyOptional.class));
    }

}