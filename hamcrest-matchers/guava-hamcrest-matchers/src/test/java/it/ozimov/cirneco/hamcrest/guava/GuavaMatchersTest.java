package it.ozimov.cirneco.hamcrest.guava;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Equivalence;
import com.google.common.collect.Multimap;

import it.ozimov.cirneco.hamcrest.guava.base.IsEmptyGuavaOptional;
import it.ozimov.cirneco.hamcrest.guava.base.IsEquivalent;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapKeyWithCollectionSize;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySet;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySetSize;
import it.ozimov.cirneco.hamcrest.guava.collect.IsMultisetElementWithCount;

@RunWith(MockitoJUnitRunner.class)
public class GuavaMatchersTest {

    @Mock
    public Object object;

    @Mock
    public Set set;

    @Mock
    public Multimap multimap;

    @Mock
    public Equivalence<Object> equivalence;

    @Test
    public void testEmptyGuavaOptional() throws Exception {
        assertThat(GuavaMatchers.emptyGuavaOptional(), instanceOf(IsEmptyGuavaOptional.class));
    }

    @Test
    public void testEquivalentTo() throws Exception {
        assertThat(GuavaMatchers.equivalentTo(object, equivalence), instanceOf(IsEquivalent.class));
    }

    @Test
    public void testIsElementWithCount() throws Exception {
        assertThat(GuavaMatchers.elementWithCount(object, 10), instanceOf(IsMultisetElementWithCount.class));
    }

    @Test
    public void testKeyWithSize() throws Exception {
        assertThat(GuavaMatchers.keyWithSize(object, 1), instanceOf(IsMultimapKeyWithCollectionSize.class));
    }

    @Test
    public void testHasSameKeySetForMultimap() throws Exception {
        assertThat(GuavaMatchers.hasSameKeySet(multimap), instanceOf(IsMultimapWithKeySet.class));
    }

    @Test
    public void testHasSameKeySetForMultimapWithSet() throws Exception {
        assertThat(GuavaMatchers.hasSameKeySet(set), instanceOf(IsMultimapWithKeySet.class));
    }

    @Test
    public void testEmptyKeySet() throws Exception {
        assertThat(GuavaMatchers.emptyKeySet(), instanceOf(IsMultimapWithKeySetSize.class));
    }

    @Test
    public void testKeySetWithSize() throws Exception {
        assertThat(GuavaMatchers.keySetWithSize(1), instanceOf(IsMultimapWithKeySetSize.class));
    }

}
