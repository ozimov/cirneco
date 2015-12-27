package it.ozimov.cirneco.hamcrest.number;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsNegativeInfinityTest extends BaseMatcherTest {

    public Matcher<Number> isNegativeInfinityMatcher;

    @Before
    public void setUp() {
        //Arrange
        isNegativeInfinityMatcher = IsNegativeInfinity.negativeInfinity();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not negative notANumber",
                isNegativeInfinityMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not negative notANumber",
                isNegativeInfinityMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not negative notANumber",
                isNegativeInfinityMatcher, 0);
        assertHasMismatchDescription("<0L> is not negative notANumber",
                isNegativeInfinityMatcher, 0L);
        assertHasMismatchDescription("<0> is not negative notANumber",
                isNegativeInfinityMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not negative notANumber",
                isNegativeInfinityMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not negative notANumber",
                isNegativeInfinityMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not negative notANumber",
                isNegativeInfinityMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not negative notANumber",
                isNegativeInfinityMatcher, .0D);
        assertHasMismatchDescription("<InfinityF> is not negative notANumber",
                isNegativeInfinityMatcher, Float.POSITIVE_INFINITY);
        assertHasMismatchDescription("<Infinity> is not negative notANumber",
                isNegativeInfinityMatcher, Double.POSITIVE_INFINITY);
        assertHasMismatchDescription("<0.0> is not negative notANumber",
                isNegativeInfinityMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to negative notANumber", isNegativeInfinityMatcher);
    }

    @Test
    public void testGivenPositiveInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isNegativeInfinityMatcher.matches(Float.POSITIVE_INFINITY);
        final boolean isDoubleInfinity = isNegativeInfinityMatcher.matches(Double.POSITIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(false));
        assertThat(isDoubleInfinity, is(false));
    }

    @Test
    public void testGivenNegativeInfiniteNumberWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatInfinity = isNegativeInfinityMatcher.matches(Float.NEGATIVE_INFINITY);
        final boolean isDoubleInfinity = isNegativeInfinityMatcher.matches(Double.NEGATIVE_INFINITY);

        //Assert
        assertThat(isFloatInfinity, is(true));
        assertThat(isDoubleInfinity, is(true));
    }

    @Test
    public void testGivenNonInfiniteNumberWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Act
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