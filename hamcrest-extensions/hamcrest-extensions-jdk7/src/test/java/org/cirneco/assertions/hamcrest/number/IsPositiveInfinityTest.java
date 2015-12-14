package org.cirneco.assertions.hamcrest.number;

import org.cirneco.assertions.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsPositiveInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isPositiveInfinityMatcher;

    public String getMatcherSimpleName() {
        return IsPositiveInfinity.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        isPositiveInfinityMatcher = IsPositiveInfinity.positiveInfinity();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not positive notANumber",
                isPositiveInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not positive notANumber",
                isPositiveInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not positive notANumber",
                isPositiveInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not positive notANumber",
                isPositiveInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not positive notANumber",
                isPositiveInfinityMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not positive notANumber",
                isPositiveInfinityMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not positive notANumber",
                isPositiveInfinityMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not positive notANumber",
                isPositiveInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not positive notANumber",
                isPositiveInfinityMatcher, .0D);
        assertHasMismatchDescription("<-InfinityF> is not positive notANumber",
                isPositiveInfinityMatcher, Float.NEGATIVE_INFINITY);
        assertHasMismatchDescription("<-Infinity> is not positive notANumber",
                isPositiveInfinityMatcher, Double.NEGATIVE_INFINITY);
        assertHasMismatchDescription("<0.0> is not positive notANumber",
                isPositiveInfinityMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to positive notANumber", isPositiveInfinityMatcher);
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isPositiveInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isPositiveInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(true));
        assertThat(isDoubleInfinity, is(true));
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isPositiveInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isPositiveInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(false));
        assertThat(isDoubleInfinity, is(false));
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Act
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

        //Assert
        assertThat(isByteInfinity, is(false));
        assertThat(isShortInfinity, is(false));
        assertThat(isIntegerInfinity, is(false));
        assertThat(isLongInfinity, is(false));
        assertThat(isBigIntegerInfinity, is(false));
        assertThat(isAtomicIntegerInfinity, is(false));
        assertThat(isAtomicLongInfinity, is(false));

        assertThat(isFloatInfinity, is(false));
        assertThat(isDoubleInfinity, is(false));
        assertThat(isBigDecimalInfinity, is(false));
    }

}