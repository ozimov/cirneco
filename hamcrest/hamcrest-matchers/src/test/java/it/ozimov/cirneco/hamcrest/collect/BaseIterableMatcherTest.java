package it.ozimov.cirneco.hamcrest.collect;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class BaseIterableMatcherTest extends BaseMatcherTest {

    public static Iterable<Object> createIterableWithSize(final int expectedSize) {
        final Collection<Object> iterable = new ArrayList<>();

        for (int i = 0; i < expectedSize; i++) {
            iterable.add(new Object());
        }

        return iterable;
    }

    public static <E> Iterable<E> createIterableWithElements(final E... elements) {
        return Arrays.asList(elements);
    }
}
