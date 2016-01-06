package it.ozimov.cirneco.hamcrest.java7.number;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class IsNegativeTest extends BaseMatcherTest {

    public Matcher<Number> isNegativeMatcher;

    @Before public void setUp() {

        //Arrange
        isNegativeMatcher = IsNegative.negative();
    }

    @Test public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned()
        throws Exception {

        //Act
        final boolean isFloatPositiveInfinityAPositiveValue =
            isNegativeMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoublePositiveInfinityAPositiveValue =
            isNegativeMatcher.matches(Double.NEGATIVE_INFINITY);

        //Assert
        assertMatches(isFloatPositiveInfinityAPositiveValue);
        assertMatches(isDoublePositiveInfinityAPositiveValue);
    }

    @Test public void testGivenNegativeNumberWhenMatchesIsCalledThenTrueIsReturned()
        throws Exception {

        //Act
        final boolean isByteAPositiveValue = isNegativeMatcher.matches((byte)
                -100);
        final boolean isShortAPositiveValue = isNegativeMatcher.matches((short)
                -100);
        final boolean isIntegerAPositiveValue = isNegativeMatcher.matches(-100);
        final boolean isLongAPositiveValue = isNegativeMatcher.matches(-100L);
        final boolean isAtomicIntegerAPositiveValue = isNegativeMatcher.matches(
                new AtomicInteger(-100));
        final boolean isAtomicLongAPositiveValue = isNegativeMatcher.matches(
                new AtomicLong(-100L));
        final boolean isBigIntegerAPositiveValue = isNegativeMatcher.matches(
                new BigInteger("-100"));

        final boolean isFloatAPositiveValue = isNegativeMatcher.matches(-100f);
        final boolean isDoubleAPositiveValue = isNegativeMatcher.matches(-100D);
        final boolean isBigDecimalAPositiveValue = isNegativeMatcher.matches(
                new BigDecimal("-100"));

        //Assert
        assertMatches(isFloatAPositiveValue);
        assertMatches(isDoubleAPositiveValue);
        assertMatches(isByteAPositiveValue);
        assertMatches(isShortAPositiveValue);
        assertMatches(isIntegerAPositiveValue);
        assertMatches(isLongAPositiveValue);
        assertMatches(isBigIntegerAPositiveValue);

        assertMatches(isAtomicIntegerAPositiveValue);
        assertMatches(isAtomicLongAPositiveValue);
        assertMatches(isBigDecimalAPositiveValue);
    }

    @Test public void testGivenPositiveNumberWhenMatchesIsCalledThenFalseIsReturnedIsReturned()
        throws Exception {

        //Act
        final boolean isByteAPositiveValue = isNegativeMatcher.matches((byte)
                100);
        final boolean isShortAPositiveValue = isNegativeMatcher.matches((short)
                100);
        final boolean isIntegerAPositiveValue = isNegativeMatcher.matches(100);
        final boolean isLongAPositiveValue = isNegativeMatcher.matches(100L);
        final boolean isAtomicIntegerAPositiveValue = isNegativeMatcher.matches(
                new AtomicInteger(100));
        final boolean isAtomicLongAPositiveValue = isNegativeMatcher.matches(
                new AtomicLong(100L));
        final boolean isBigIntegerAPositiveValue = isNegativeMatcher.matches(
                new BigInteger("100"));

        final boolean isFloatAPositiveValue = isNegativeMatcher.matches(100.0f);
        final boolean isDoubleAPositiveValue = isNegativeMatcher.matches(
                100.0D);
        final boolean isBigDecimalAPositiveValue = isNegativeMatcher.matches(
                new BigDecimal("100.0"));

        //Assert
        assertDoesNotMatch(isFloatAPositiveValue);
        assertDoesNotMatch(isDoubleAPositiveValue);
        assertDoesNotMatch(isByteAPositiveValue);
        assertDoesNotMatch(isShortAPositiveValue);
        assertDoesNotMatch(isIntegerAPositiveValue);
        assertDoesNotMatch(isLongAPositiveValue);
        assertDoesNotMatch(isBigIntegerAPositiveValue);

        assertDoesNotMatch(isAtomicIntegerAPositiveValue);
        assertDoesNotMatch(isAtomicLongAPositiveValue);
        assertDoesNotMatch(isBigDecimalAPositiveValue);
    }

    @Test public void testGivenZeroNumberWhenMatchesIsCalledThenFalseIsReturned()
        throws Exception {

        //Act
        final boolean isByteAPositiveValue = isNegativeMatcher.matches((byte)
                0);
        final boolean isShortAPositiveValue = isNegativeMatcher.matches((short)
                0);
        final boolean isIntegerAPositiveValue = isNegativeMatcher.matches(0);
        final boolean isLongAPositiveValue = isNegativeMatcher.matches(0L);
        final boolean isAtomicIntegerAPositiveValue = isNegativeMatcher.matches(
                new AtomicInteger(0));
        final boolean isAtomicLongAPositiveValue = isNegativeMatcher.matches(
                new AtomicLong(0L));
        final boolean isBigIntegerAPositiveValue = isNegativeMatcher.matches(
                new BigInteger("0"));

        final boolean isFloatAPositiveValue = isNegativeMatcher.matches(.0f);
        final boolean isDoubleAPositiveValue = isNegativeMatcher.matches(.0D);
        final boolean isBigDecimalAPositiveValue = isNegativeMatcher.matches(
                new BigDecimal(".0"));

        //Assert
        assertDoesNotMatch(isFloatAPositiveValue);
        assertDoesNotMatch(isDoubleAPositiveValue);
        assertDoesNotMatch(isByteAPositiveValue);
        assertDoesNotMatch(isShortAPositiveValue);
        assertDoesNotMatch(isIntegerAPositiveValue);
        assertDoesNotMatch(isLongAPositiveValue);
        assertDoesNotMatch(isBigIntegerAPositiveValue);

        assertDoesNotMatch(isAtomicIntegerAPositiveValue);
        assertDoesNotMatch(isAtomicLongAPositiveValue);
        assertDoesNotMatch(isBigDecimalAPositiveValue);
    }

    @Test public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not negative", isNegativeMatcher,
            (byte) 0);
        assertHasMismatchDescription("<0s> is not negative", isNegativeMatcher,
            (short) 0);
        assertHasMismatchDescription("<0> is not negative", isNegativeMatcher,
            0);
        assertHasMismatchDescription("<0L> is not negative", isNegativeMatcher,
            0L);
        assertHasMismatchDescription("<0> is not negative", isNegativeMatcher,
            new BigInteger("0"));
        assertHasMismatchDescription("<0> is not negative", isNegativeMatcher,
            new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not negative", isNegativeMatcher,
            new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not negative",
            isNegativeMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not negative", isNegativeMatcher,
            .0D);
        assertHasMismatchDescription("<0.0> is not negative", isNegativeMatcher,
            new BigDecimal(".0"));
    }

    @Test public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a negative value", isNegativeMatcher);
    }

}
