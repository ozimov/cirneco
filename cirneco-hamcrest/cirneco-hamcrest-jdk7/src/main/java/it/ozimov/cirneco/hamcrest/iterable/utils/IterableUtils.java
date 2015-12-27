package it.ozimov.cirneco.hamcrest.iterable.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class IterableUtils {

    private IterableUtils() {
    }

    public static <K> boolean isEmpty(final Iterable<? super K> iterable) {
        checkNotNull(iterable);

        return !iterable.iterator().hasNext();
    }

    public static <K> List<K> listCopy(final Iterable<? extends K> iterable) {
        checkNotNull(iterable);

        final List<K> list = new ArrayList<>();
        for (final K k : iterable) {
            list.add(k);
        }
        return list;
    }

    public static <K extends Comparable> List<K> sortedListCopy(final Iterable<? extends K> iterable) {
        checkNotNull(iterable);

        final List<K> list = listCopy(iterable);
        Collections.sort(list);
        return list;
    }

    public static <K extends Comparable> List<K> sortedReversedListCopy(final Iterable<? extends K> iterable) {
        checkNotNull(iterable);

        final List<K> list = sortedListCopy(iterable);
        Collections.reverse(list);
        return list;
    }

    public static <K> List<K> sortedListCopy(final Iterable<? extends K> iterable, final Comparator<? super K> comparator) {
        checkNotNull(iterable);
        checkNotNull(comparator);

        final List<K> list = listCopy(iterable);
        Collections.sort(list, comparator);
        return list;
    }

    public static <K> List<K> sortedReversedListCopy(final Iterable<? extends K> iterable, final Comparator<? super K> comparator) {
        checkNotNull(iterable);
        checkNotNull(comparator);

        final List<K> list = sortedListCopy(iterable, comparator);
        Collections.reverse(list);
        return list;
    }

}
