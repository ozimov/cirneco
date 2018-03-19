package it.ozimov.cirneco.hamcrest.number;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IsNegativeInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isNegativeInfinityMatcher;

    @Before
    public void setUp() {

        // Arrange
        isNegativeInfinityMatcher = IsNegativeInfinity.negativeInfinity();
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isNegativeInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isNegativeInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        // Assert
        assertDoesNotMatch(isFloatInfinity);
        assertDoesNotMatch(isDoubleInfinity);
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenTrueIsReturned() throws Exception {

        // Act
        final boolean isFloatInfinity = isNegativeInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isNegativeInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        // Assert
        assertMatches(isFloatInfinity);
        assertMatches(isDoubleInfinity);
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenFalseIsReturned() throws Exception {

        // Act
        final boolean isByteInfinity = isNegativeInfinityMatcher.matches((byte) 0);
        final boolean isShortInfinity = isNegativeInfinityMatcher.matches((short) 0);
        final boolean isIntegerInfinity = isNegativeInfinityMatcher.matches(0);
        final boolean isLongInfinity = isNegativeInfinityMatcher.matches(0L);
        final boolean isBigIntegerInfinity = isNegativeInfinityMatcher.matches(new BigInteger("0"));
        final boolean isAtomicIntegerInfinity = isNegativeInfinityMatcher.matches(new AtomicInteger(0));
        final boolean isAtomicLongInfinity = isNegativeInfinityMatcher.matches(new AtomicLong(0L));

        final boolean isFloatInfinity = isNegativeInfinityMatcher.matches((float) .0);
        final boolean isDoubleInfinity = isNegativeInfinityMatcher.matches(.0D);
        final boolean isBigDecimalInfinity = isNegativeInfinityMatcher.matches(new BigDecimal(".0"));

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
        assertHasMismatchDescription("<0> is not negative infinity", isNegativeInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not negative infinity", isNegativeInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not negative infinity", isNegativeInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not negative infinity", isNegativeInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not negative infinity", isNegativeInfinityMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not negative infinity", isNegativeInfinityMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not negative infinity", isNegativeInfinityMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not negative infinity", isNegativeInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not negative infinity", isNegativeInfinityMatcher, .0D);
        assertHasMismatchDescription("<InfinityF> is not negative infinity", isNegativeInfinityMatcher,
                Float.POSITIVE_INFINITY);
        assertHasMismatchDescription("<Infinity> is not negative infinity", isNegativeInfinityMatcher,
                Double.POSITIVE_INFINITY);
        assertHasMismatchDescription("<0.0> is not negative infinity", isNegativeInfinityMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to negative infinity", isNegativeInfinityMatcher);
    }

}
