package it.ozimov.cirneco.assertj;

import com.google.common.collect.*;
import com.google.common.io.ByteSource;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.util.CheckReturnValue;
import org.assertj.guava.api.*;

/**
 * This class entirely copies the content of the {@code org.assertj.guava.api.Assertions} class
 * to make testing easier.
 * All rights to AssertJ-Guava project:
 * <p>
 * See <a href="https://github.com/joel-costigliola/assertj-guava/blob/master/src/main/java/org/assertj/guava/api/Assertions.java">https://github.com/joel-costigliola/assertj-guava/blob/master/src/main/java/org/assertj/guava/api/Assertions.java</a>
 */
public class JUnitGuavaEnhancedSoftAssertions extends JUnitSoftAssertions {

    @CheckReturnValue
    public ByteSourceAssert assertThat(final ByteSource actual) {
        return super.proxy(ByteSourceAssert.class, ByteSource.class, actual);
    }

    @CheckReturnValue
    public <K, V> MultimapAssert<K, V> assertThat(final Multimap<K, V> actual) {
        return (MultimapAssert<K, V>) super.proxy(MultimapAssert.class, Multimap.class, actual);
    }

    @CheckReturnValue
    public <T extends Comparable<T>> RangeAssert<T> assertThat(final Range<T> actual) {
        return (RangeAssert<T>) super.proxy(RangeAssert.class, Range.class, actual);
    }

    @CheckReturnValue
    public <K extends Comparable<K>, V> RangeMapAssert<K, V> assertThat(final RangeMap<K, V> actual) {
        return (RangeMapAssert<K, V>) super.proxy(RangeMapAssert.class, RangeMap.class, actual);
    }

    @CheckReturnValue
    public <R, C, V> TableAssert<R, C, V> assertThat(Table<R, C, V> actual) {
        return (TableAssert<R, C, V>) super.proxy(TableAssert.class, Table.class, actual);
    }

    @CheckReturnValue
    public <T> MultisetAssert<T> assertThat(final Multiset<T> actual) {
        return (MultisetAssert<T>) super.proxy(MultisetAssert.class, Multiset.class, actual);
    }


}
