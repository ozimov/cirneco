package org.cirneco.assertions.hamcrest.hamcrest;

import org.cirneco.assertions.hamcrest.CirnecoMatchersJ8;
import org.cirneco.assertions.hamcrest.base.IsEmptyOptional;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class CirnecoMatchersJ8Test {

    @Test
    public void testEmptyOptional() throws Exception {
        assertThat(CirnecoMatchersJ8.emptyOptional(), instanceOf(IsEmptyOptional.class));
    }

}