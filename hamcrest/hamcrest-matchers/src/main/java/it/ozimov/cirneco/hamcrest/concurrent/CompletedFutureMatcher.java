package it.ozimov.cirneco.hamcrest.concurrent;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;


/**
 * Does the {@linkplain Iterable} has the same size of another?
 * <p/>
 *
 * @since version 1.0.3 for JDK7
 */
public class CompletedFutureMatcher<T> extends CustomTypeSafeMatcher<CompletionStage<T>> {

    private final long timeout;
    private final TimeUnit unit;
    private final Matcher<? super T> innerMatcher;

    CompletedFutureMatcher(final Matcher<? super T> innerMatcher, final long timeout, final TimeUnit unit) {
        super("completed future with " + innerMatcher.toString());
        this.innerMatcher = innerMatcher;
        this.timeout = timeout;
        this.unit = unit;
    }

    public static <T> Matcher<CompletionStage<T>> completedWith(final Matcher<? super T> innerMatcher, long timeout, TimeUnit unit) {
        return new CompletedFutureMatcher<>(innerMatcher, timeout, unit);
    }

    @Override
    protected boolean matchesSafely(CompletionStage<T> stage) {
        try {
            final T value = stage.toCompletableFuture().get(timeout, unit);
            return innerMatcher.matches(value);
        } catch (Exception e) {
            return false;
        }
    }
}

