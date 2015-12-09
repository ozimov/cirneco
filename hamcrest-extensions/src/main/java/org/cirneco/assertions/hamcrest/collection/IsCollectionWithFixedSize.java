package org.cirneco.assertions.hamcrest.collection;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsCollectionWithSize;

import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Does the {@linkplain Collection} has a given size?
 */
public class IsCollectionWithFixedSize<E> extends IsCollectionWithSize<E> {

    public IsCollectionWithFixedSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the <code>size()</code> method returns
     * a value equal to the <code>1</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeOne())</pre>
     */
    public static <E> Matcher<Collection<? extends E>> hasSizeOne() {
        return withSize(1);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the <code>size()</code> method returns
     * a value equal to the <code>2</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeTwo())</pre>
     */
    public static <E> Matcher<Collection<? extends E>> hasSizeTwo() {
        return withSize(2);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the <code>size()</code> method returns
     * a value equal to the <code>3</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeThree())</pre>
     */
    public static <E> Matcher<Collection<? extends E>> hasSizeThree() {
        return withSize(3);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the <code>size()</code> method returns
     * a value equal to the <code>4</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFour())</pre>
     */
    public static <E> Matcher<Collection<? extends E>> hasSizeFour() {
        return withSize(4);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the <code>size()</code> method returns
     * a value equal to the <code>5</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("foo", "bar"), hasSizeFive())</pre>
     */
    public static <E> Matcher<Collection<? extends E>> hasSizeFive() {
        return withSize(5);
    }

    static <E> Matcher<Collection<? extends E>> withSize(final int size) {
        return new IsCollectionWithFixedSize(equalTo(size));
    }

}