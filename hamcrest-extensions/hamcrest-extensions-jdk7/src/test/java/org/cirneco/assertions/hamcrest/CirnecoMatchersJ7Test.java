package org.cirneco.assertions.hamcrest;

import com.google.common.base.Equivalence;
import org.cirneco.assertions.hamcrest.base.IsEmptyGuavaOptional;
import org.cirneco.assertions.hamcrest.base.IsEquivalent;
import org.cirneco.assertions.hamcrest.collection.IsMapWithSameKeySet;
import org.cirneco.assertions.hamcrest.number.IsBetween;
import org.cirneco.assertions.hamcrest.number.IsBetweenInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenLowerBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsBetweenUpperBoundInclusive;
import org.cirneco.assertions.hamcrest.number.IsInfinity;
import org.cirneco.assertions.hamcrest.number.IsNegativeInfinity;
import org.cirneco.assertions.hamcrest.number.IsNotANumber;
import org.cirneco.assertions.hamcrest.number.IsPositiveInfinity;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.cirneco.assertions.hamcrest.collection.IsIterableWithSizeTest.createIterableWithSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CirnecoMatchersJ7Test {

    @Mock
    public Object object;

    @Mock
    public Map map;

    @Mock
    public Equivalence<Object> equivalence;


    @Test
    public void testEmptyGuavaOptional() throws Exception {
        assertThat(CirnecoMatchersJ7.emptyGuavaOptional(), instanceOf(IsEmptyGuavaOptional.class));
    }

    @Test
    public void testEquivalentTo() throws Exception {
        assertThat(CirnecoMatchersJ7.equivalentTo(object, equivalence), instanceOf(IsEquivalent.class));
    }

    @Test
    public void testHasSizeOne() throws Exception {
        //Arrange
        final int expectedSize = 1;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeOne();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeTwo() throws Exception {
        //Arrange
        final int expectedSize = 2;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeTwo();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeThree() throws Exception {
        //Arrange
        final int expectedSize = 3;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeThree();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFour() throws Exception {
        //Arrange
        final int expectedSize = 4;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeFour();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSizeFive() throws Exception {
        //Arrange
        final int expectedSize = 5;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSizeFive();
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSize() throws Exception {
        //Arrange
        final int expectedSize = 100;
        final Iterable<Object> iterableWithExpectedSize = createIterableWithSize(expectedSize);

        //Act
        final Matcher<Iterable<Object>> matcher = CirnecoMatchersJ7.hasSize(expectedSize);
        final boolean hasExpectedSize = matcher.matches(iterableWithExpectedSize);

        //Assert
        assertThat(matcher, instanceOf(IsIterableWithSize.class));
        assertThat(hasExpectedSize, is(true));
    }

    @Test
    public void testHasSameKeySet() throws Exception {
        assertThat(CirnecoMatchersJ7.hasSameKeySet(map), instanceOf(IsMapWithSameKeySet.class));
    }

    @Test
    public void testBetween() throws Exception {
        assertThat(CirnecoMatchersJ7.between(0, 100), instanceOf(IsBetween.class));
    }

    @Test
    public void testBetweenInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenInclusive(0, 100), instanceOf(IsBetweenInclusive.class));
    }

    @Test
    public void testBetweenLowerBoundInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenLowerBoundInclusive(0, 100), instanceOf(IsBetweenLowerBoundInclusive.class));
    }

    @Test
    public void testBetweenUpperBoundInclusive() throws Exception {
        assertThat(CirnecoMatchersJ7.betweenUpperBoundInclusive(0, 100), instanceOf(IsBetweenUpperBoundInclusive.class));
    }

    @Test
    public void testInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.infinity(), instanceOf(IsInfinity.class));
    }

    @Test
    public void testNegativeInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.negativeInfinity(), instanceOf(IsNegativeInfinity.class));
    }

    @Test
    public void testNotANumber() throws Exception {
        assertThat(CirnecoMatchersJ7.notANumber(), instanceOf(IsNotANumber.class));
    }

    @Test
    public void testPositiveInfinity() throws Exception {
        assertThat(CirnecoMatchersJ7.positiveInfinity(), instanceOf(IsPositiveInfinity.class));
    }
}