package it.ozimov.cirneco.hamcrest.number;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IsPositiveTest extends BaseMatcherTest {

    public Matcher<Number> isPositiveMatcher;

    @Before
    public void setUp() {

        // Arrange
        isPositiveMatcher = IsPositive.positive();
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatPositiveInfinityAPositiveValue = isPositiveMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoublePositiveInfinityAPositiveValue = isPositiveMatcher.matches(Double.POSITIVE_INFINITY);

        // Assert
        assertMatches(isFloatPositiveInfinityAPositiveValue);
        assertMatches(isDoublePositiveInfinityAPositiveValue);
    }

    @Test
    public void testGivenPositiveNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isByteAPositiveValue = isPositiveMatcher.matches((byte) 100);
        final boolean isShortAPositiveValue = isPositiveMatcher.matches((short) 100);
        final boolean isIntegerAPositiveValue = isPositiveMatcher.matches(100);
        final boolean isLongAPositiveValue = isPositiveMatcher.matches(100L);
        final boolean isAtomicIntegerAPositiveValue = isPositiveMatcher.matches(new AtomicInteger(100));
        final boolean isAtomicLongAPositiveValue = isPositiveMatcher.matches(new AtomicLong(100L));
        final boolean isBigIntegerAPositiveValue = isPositiveMatcher.matches(new BigInteger("100"));

        final boolean isFloatAPositiveValue = isPositiveMatcher.matches(100f);
        final boolean isDoubleAPositiveValue = isPositiveMatcher.matches(100D);
        final boolean isBigDecimalAPositiveValue = isPositiveMatcher.matches(new BigDecimal("100.0"));

        // Assert
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

    @Test
    public void testGivenNegativeNumberWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isByteAPositiveValue = isPositiveMatcher.matches((byte) -100);
        final boolean isShortAPositiveValue = isPositiveMatcher.matches((short) -100);
        final boolean isIntegerAPositiveValue = isPositiveMatcher.matches(-100);
        final boolean isLongAPositiveValue = isPositiveMatcher.matches(-100L);
        final boolean isAtomicIntegerAPositiveValue = isPositiveMatcher.matches(new AtomicInteger(-100));
        final boolean isAtomicLongAPositiveValue = isPositiveMatcher.matches(new AtomicLong(-100L));
        final boolean isBigIntegerAPositiveValue = isPositiveMatcher.matches(new BigInteger("-100"));

        final boolean isFloatAPositiveValue = isPositiveMatcher.matches(-100.0f);
        final boolean isDoubleAPositiveValue = isPositiveMatcher.matches(-100.0D);
        final boolean isBigDecimalAPositiveValue = isPositiveMatcher.matches(new BigDecimal("-100.0"));

        // Assert
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

    @Test
    public void testGivenZeroNumberWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isByteAPositiveValue = isPositiveMatcher.matches((byte) 0);
        final boolean isShortAPositiveValue = isPositiveMatcher.matches((short) 0);
        final boolean isIntegerAPositiveValue = isPositiveMatcher.matches(0);
        final boolean isLongAPositiveValue = isPositiveMatcher.matches(0L);
        final boolean isAtomicIntegerAPositiveValue = isPositiveMatcher.matches(new AtomicInteger(0));
        final boolean isAtomicLongAPositiveValue = isPositiveMatcher.matches(new AtomicLong(0L));
        final boolean isBigIntegerAPositiveValue = isPositiveMatcher.matches(new BigInteger("0"));

        final boolean isFloatAPositiveValue = isPositiveMatcher.matches(.0f);
        final boolean isDoubleAPositiveValue = isPositiveMatcher.matches(.0D);
        final boolean isBigDecimalAPositiveValue = isPositiveMatcher.matches(new BigDecimal(".0"));

        // Assert
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

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not positive", isPositiveMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not positive", isPositiveMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not positive", isPositiveMatcher, 0);
        assertHasMismatchDescription("<0L> is not positive", isPositiveMatcher, 0L);
        assertHasMismatchDescription("<0> is not positive", isPositiveMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not positive", isPositiveMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not positive", isPositiveMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not positive", isPositiveMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not positive", isPositiveMatcher, .0D);
        assertHasMismatchDescription("<0.0> is not positive", isPositiveMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a positive value", isPositiveMatcher);
    }

}
