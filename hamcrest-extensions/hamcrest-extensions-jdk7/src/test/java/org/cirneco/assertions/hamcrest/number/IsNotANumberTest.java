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

public class IsNotANumberTest extends BaseMatcherTest {

    public Matcher<Number> isNotANumberMatcher;

    public String getMatcherSimpleName() {
        return IsNotANumber.class.getSimpleName();
    }

    @Before
    public void setup() {
        //Arrange
        isNotANumberMatcher = IsNotANumber.notANumber();
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription("<0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, (byte) 0);
        assertHasMismatchDescription("<0s> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, (short) 0);
        assertHasMismatchDescription("<0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, 0);
        assertHasMismatchDescription("<0L> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, 0L);
        assertHasMismatchDescription("<0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, new BigInteger("0"));
        assertHasMismatchDescription("<0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, new AtomicInteger(0));
        assertHasMismatchDescription("<0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, new AtomicLong(0L));

        assertHasMismatchDescription("<0.0F> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, .0F);
        assertHasMismatchDescription("<0.0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, .0D);
        assertHasMismatchDescription("<0.0> is not a number (i.e. Double.NaN or Float.NaN)",
                isNotANumberMatcher, new BigDecimal(".0"));
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("not a number (i.e. Double.NaN or Float.NaN)", isNotANumberMatcher);
    }

    @Test
    public void testGivenInvalidNumberValueWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Act
        final boolean isFloatNotANumber = isNotANumberMatcher.matches(Float.NaN);
        final boolean isDoubleNotANumber = isNotANumberMatcher.matches(Double.NaN);

        //Assert
        assertThat(isFloatNotANumber, is(true));
        assertThat(isDoubleNotANumber, is(true));
    }

    @Test
    public void testGivenValidNumberValueWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Act
        final boolean isByteNotANumber = isNotANumberMatcher.matches((byte) 0);
        final boolean isShortNotANumber = isNotANumberMatcher.matches((short) 0);
        final boolean isIntegerNotANumber = isNotANumberMatcher.matches(0);
        final boolean isLongNotANumber = isNotANumberMatcher.matches(0L);
        final boolean isBigIntegerNotANumber = isNotANumberMatcher.matches(new BigInteger("0"));
        final boolean isAtomicIntegerNotANumber = isNotANumberMatcher.matches(new AtomicInteger(0));
        final boolean isAtomicLongNotANumber = isNotANumberMatcher.matches(new AtomicLong(0L));

        final boolean isFloatNotANumber = isNotANumberMatcher.matches((float) .0);
        final boolean isDoubleNotANumber = isNotANumberMatcher.matches(.0D);
        final boolean isBigDecimalNotANumber = isNotANumberMatcher.matches(new BigDecimal(".0"));

        //Assert
        assertThat(isByteNotANumber, is(false));
        assertThat(isShortNotANumber, is(false));
        assertThat(isIntegerNotANumber, is(false));
        assertThat(isLongNotANumber, is(false));
        assertThat(isBigIntegerNotANumber, is(false));
        assertThat(isAtomicIntegerNotANumber, is(false));
        assertThat(isAtomicLongNotANumber, is(false));

        assertThat(isFloatNotANumber, is(false));
        assertThat(isDoubleNotANumber, is(false));
        assertThat(isBigDecimalNotANumber, is(false));
    }

}