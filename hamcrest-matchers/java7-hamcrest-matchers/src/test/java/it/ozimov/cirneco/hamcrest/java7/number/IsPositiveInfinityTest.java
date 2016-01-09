package it.ozimov.cirneco.hamcrest.java7.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsPositiveInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isPositiveInfinityMatcher;

    @Before
    public void setUp() {

        // Arrange
        isPositiveInfinityMatcher = IsPositiveInfinity.positiveInfinity();
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isPositiveInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isPositiveInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        // Assert
        assertMatches(isFloatInfinity);
        assertMatches(isDoubleInfinity);
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isPositiveInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isPositiveInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        // Assert
        assertDoesNotMatch(isFloatInfinity);
        assertDoesNotMatch(isDoubleInfinity);
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isByteInfinity = isPositiveInfinityMatcher.matches((byte) 0);
        final boolean isShortInfinity = isPositiveInfinityMatcher.matches((short) 0);
        final boolean isIntegerInfinity = isPositiveInfinityMatcher.matches(0);
        final boolean isLongInfinity = isPositiveInfinityMatcher.matches(0L);
        final boolean isBigIntegerInfinity = isPositiveInfinityMatcher.matches(new BigInteger("0"));
        final boolean isAtomicIntegerInfinity = isPositiveInfinityMatcher.matches(new AtomicInteger(0));
        final boolean isAtomicLongInfinity = isPositiveInfinityMatcher.matches(new AtomicLong(0L));

        final boolean isFloatInfinity = isPositiveInfinityMatcher.matches((float) .0);
        final boolean isDoubleInfinity = isPositiveInfinityMatcher.matches(.0D);
        final boolean isBigDecimalInfinity = isPositiveInfinityMatcher.matches(new BigDecimal(".0"));

        // Assert
        assertDoesNotMatch(isByteInfinity);
        assertDoesNotMatch(isShortInfinity);
        assertDoesNotMatch(isIntegerInfinity);
        assertDoesNotMatch(isLongInfinity);
        assertDoesNotMatch(isBigIntegerInfinity);
        assertDoesNotMatch(isAtomicIntegerInfinity);
        assertDoesNotMatch(isAtomicLongInfinity);

        assertDoesNotMatch(isFloatInfinity);
        assertDoesNotMatch(isDoubleInfinity);
        assertDoesNotMatch(isBigDecimalInfinity);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not positive infinity", isPositiveInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not positive infinity", isPositiveInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not positive infinity", isPositiveInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not positive infinity", isPositiveInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not positive infinity", isPositiveInfinityMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not positive infinity", isPositiveInfinityMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not positive infinity", isPositiveInfinityMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not positive infinity", isPositiveInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not positive infinity", isPositiveInfinityMatcher, .0D);
        assertHasMismatchDescription("<-InfinityF> is not positive infinity", isPositiveInfinityMatcher,
            Float.NEGATIVE_INFINITY);
        assertHasMismatchDescription("<-Infinity> is not positive infinity", isPositiveInfinityMatcher,
            Double.NEGATIVE_INFINITY);
        assertHasMismatchDescription("<0.0> is not positive infinity", isPositiveInfinityMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to positive infinity", isPositiveInfinityMatcher);
    }

}
