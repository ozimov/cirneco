package it.ozimov.cirneco.hamcrest.java7.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.hamcrest.Matcher;

import org.junit.Before;
import org.junit.Test;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;

public class IsInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isInfinityMatcher;

    @Before
    public void setUp() {

        // Arrange
        isInfinityMatcher = IsInfinity.infinity();
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        // Assert
        assertMatches(isFloatInfinity);
        assertMatches(isDoubleInfinity);
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        // Assert
        assertMatches(isFloatInfinity);
        assertMatches(isDoubleInfinity);
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isByteInfinity = isInfinityMatcher.matches((byte) 0);
        final boolean isShortInfinity = isInfinityMatcher.matches((short) 0);
        final boolean isIntegerInfinity = isInfinityMatcher.matches(0);
        final boolean isLongInfinity = isInfinityMatcher.matches(0L);
        final boolean isBigIntegerInfinity = isInfinityMatcher.matches(new BigInteger("0"));
        final boolean isAtomicIntegerInfinity = isInfinityMatcher.matches(new AtomicInteger(0));
        final boolean isAtomicLongInfinity = isInfinityMatcher.matches(new AtomicLong(0L));

        final boolean isFloatInfinity = isInfinityMatcher.matches((float) .0);
        final boolean isDoubleInfinity = isInfinityMatcher.matches(.0D);
        final boolean isBigDecimalInfinity = isInfinityMatcher.matches(new BigDecimal(".0"));

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
        assertHasMismatchDescription("<0> is not infinite (positive or negative)", isInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not infinite (positive or negative)", isInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not infinite (positive or negative)", isInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not infinite (positive or negative)", isInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not infinite (positive or negative)", isInfinityMatcher,
            new BigInteger("0"));
        assertHasMismatchDescription("<0> is not infinite (positive or negative)", isInfinityMatcher,
            new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not infinite (positive or negative)", isInfinityMatcher,
            new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not infinite (positive or negative)", isInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not infinite (positive or negative)", isInfinityMatcher, .0D);
        assertHasMismatchDescription("<0.0> is not infinite (positive or negative)", isInfinityMatcher,
            new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to infinite (positive or negative)", isInfinityMatcher);
    }

}
