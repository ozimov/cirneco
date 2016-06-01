package it.ozimov.cirneco.hamcrest.java7.base;

import it.ozimov.cirneco.hamcrest.java7.base.utils.LevenshteinDistance;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.hamcrest.object.HasToString.hasToString;

/**
 * Is the value a number between two numbers?
 *
 * @since version 0.7.0 for JDK7
 */
public class IsSimilar extends BaseMatcher<CharSequence> {

    private final CharSequence target;
    private final int maxDistance;

    public IsSimilar(final CharSequence target, final int levenshteinDistance) {
        checkNotNull(target);
        checkArgument(levenshteinDistance>=0, "Levenshtein distance cannot be negative");
        this.target = target;
        this.maxDistance = levenshteinDistance;
    }

    /**
     * Creates a matcher that matches only when the examined {@linkplain CharSequence} is within the given Levenshtein distance
     * (inclusive) with the provided <code>target</code> {@linkplain CharSequence}.
     */
    public static Matcher<CharSequence> isSimilar(final CharSequence target, final int levenshteinDistance) {
        return new IsSimilar(target, levenshteinDistance);
    }

    @Override
    public boolean matches(final Object arg) {
        checkNotNull(arg);
        checkArgument(arg instanceof CharSequence, "Expected and instance of CharSequence");
        return LevenshteinDistance.distance((CharSequence)arg, target) <= maxDistance;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("has max Levenshtein distance ").appendValue(maxDistance)
                .appendText(" from ").appendValue(target);
    }


}



