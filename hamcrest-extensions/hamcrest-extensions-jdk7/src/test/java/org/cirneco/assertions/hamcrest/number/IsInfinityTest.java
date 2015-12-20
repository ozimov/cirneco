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

public class IsInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isInfinityMatcher;

    @Before
    public void setUp() {
        //Arrange
        isInfinityMatcher = IsInfinity.infinity();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not notANumber (positive or negative)",
                isInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not notANumber (positive or negative)",
                isInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not notANumber (positive or negative)",
                isInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not notANumber (positive or negative)",
                isInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not notANumber (positive or negative)",
                isInfinityMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not notANumber (positive or negative)",
                isInfinityMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not notANumber (positive or negative)",
                isInfinityMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not notANumber (positive or negative)",
                isInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not notANumber (positive or negative)",
                isInfinityMatcher, .0D);
        assertHasMismatchDescription("<0.0> is not notANumber (positive or negative)",
                isInfinityMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to notANumber (positive or negative)", isInfinityMatcher);
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(true));
        assertThat(isDoubleInfinity, is(true));
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(true));
        assertThat(isDoubleInfinity, is(true));
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Act
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