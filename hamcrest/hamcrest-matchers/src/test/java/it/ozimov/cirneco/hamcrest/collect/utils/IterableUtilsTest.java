package it.ozimov.cirneco.hamcrest.collect.utils;

import com.google.common.collect.ImmutableList;
import lombok.experimental.Delegate;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static it.ozimov.cirneco.hamcrest.clazz.IsValidNoArgumentConstructor.hasNoArgumentConstructor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.assumeTrue;

public class IterableUtilsTest {

    public List<Integer> backedList;
    public Iterable<Integer> iterable;

    public Comparator<Integer> comparator;

    @Before
    public void setUp() {

        // Arrange
        backedList = ImmutableList.of(1_000, 1, 100);
        iterable = backedList;

        // Reverse natural ordering
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(final Integer o1, final Integer o2) {
                return -1 * Integer.compare(o1, o2);
            }
        };
    }

    @Test
    public void testConstructor() throws Exception {
        // Arrange
        MatcherAssert.assertThat(IterableUtils.class, hasNoArgumentConstructor());
    }

    @Test
    public void testWhenEmptyIterableIsGivenThenWhenIsEmptyIsCalledThenTrueIsReturned() throws Exception {

        // Arrange
        final Iterable<Object> emptyIterable = ImmutableList.of();

        // Act
        final boolean isEmpty = IterableUtils.isEmpty(emptyIterable);

        // Assert
        assertThat(isEmpty, is(true));
    }

    @Test
    public void testWhenNonEmptyIterableIsGivenThenWhenIsEmptyIsCalledThenFalseIsReturned() throws Exception {

        // Arrange
        final Iterable<Object> nonEmptyIterable = ImmutableList.of(new Object());

        // Act
        final boolean isEmpty = IterableUtils.isEmpty(nonEmptyIterable);

        // Assert
        assertThat(isEmpty, is(false));
    }

    @Test
    public void testListCopy() throws Exception {

        // Arrange
        assumeTrue("Expected that Iterable and List are same instance", iterable == backedList);

        // Act
        final List<Integer> copy = IterableUtils.listCopy(iterable);

        // Assert
        assertThat(copy, hasSize(backedList.size()));
        assertThat(copy, contains(backedList.get(0), backedList.get(1), backedList.get(2)));
    }

    @Test
    public void testSortedListCopy() throws Exception {

        // Arrange
        assumeTrue("Expected that Iterable and List are same instance", iterable == backedList);

        // Act
        final List<Integer> copy = IterableUtils.sortedListCopy(iterable);

        // Assert
        assertThat(copy, hasSize(backedList.size()));
        assertThat(copy, contains(backedList.get(1), backedList.get(2), backedList.get(0)));
    }

    @Test
    public void testSortedReversedListCopy() throws Exception {

        // Arrange
        assumeTrue("Expected that Iterable and List are same instance", iterable == backedList);

        // Act
        final List<Integer> copy = IterableUtils.sortedReversedListCopy(iterable);

        // Assert
        assertThat(copy, hasSize(backedList.size()));
        assertThat(copy, contains(backedList.get(0), backedList.get(2), backedList.get(1)));
    }

    @Test
    public void testSortedListCopyWithComparator() throws Exception {

        // Arrange
        assumeTrue("Expected that Iterable and List are same instance", iterable == backedList);

        // Act
        final List<Integer> copy = IterableUtils.sortedListCopy(iterable, comparator);

        // Assert
        assertThat(copy, hasSize(backedList.size()));
        assertThat(copy, contains(backedList.get(0), backedList.get(2), backedList.get(1)));
    }

    @Test
    public void testSortedReversedListCopyWithComparator() throws Exception {

        // Arrange
        assumeTrue("Expected that Iterable and List are same instance", iterable == backedList);

        // Act
        final List<Integer> copy = IterableUtils.sortedReversedListCopy(iterable, comparator);

        // Assert
        assertThat(copy, hasSize(backedList.size()));
        assertThat(copy, contains(backedList.get(1), backedList.get(2), backedList.get(0)));
    }

    @Test
    public void testSize() throws Exception {
        // Arrange

        // Act
        final int size = IterableUtils.size(iterable);

        // Assert
        assertThat(size, is(3));
    }

    @Test
    public void testSizeFromCollection() throws Exception {
        // Arrange

        // Act
        final int size = IterableUtils.size(newArrayList(iterable));

        // Assert
        assertThat(size, is(3));
    }


    @Test
    public void testSizeFromIterableNotCollection() throws Exception {
        // Arrange

        // Act
        final int size = IterableUtils.size(new IterableNotCollection());

        // Assert
        assertThat(size, is(3));
    }


    static class IterableNotCollection implements Iterable {

        @Delegate
        Iterable iterable;

        public IterableNotCollection() {
            iterable = ImmutableList.of("A", "B", "C");
        }
    }

}
