package it.ozimov.mockito.helpers.captors;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Capture the result of a spied object
 * <pre>
 *  //Arrange
 *  MyObject spy = Mockito.spy(new MyObject());
 *  ResultCaptor<String> resultCaptor = new ResultCaptor<>();
 *  Mockito.doAnswer(resultCaptor).when(spy).callMethodOnSpy();
 *
 *  //Act
 *  spy.callMethodOnSpy();
 *
 *  //Assert
 *  String capturedResult = resultCaptor.result();
 *  //do any assertion on the captured result
 * </pre>
 * <p>
 *     when the captor is invoked multiple times, the {@code results()} method should be called to
 *     get the results in the order they have been collected (i.e. according to the order
 *     of execution of the methods).
 * </p>
 *
 * @since version 1.2.0 for JDK7
 */
public class ResultCaptor<T> implements Answer {

	private List<T> capturedResults = new ArrayList<>();

	public static <T> ResultCaptor<T> resultCaptor() {
	    return new ResultCaptor<>();
    }

	public T result() {
        Preconditions.checkState(!capturedResults.isEmpty(), "No result captured, invocation on result() is misleading. Instead, consider to use verify() with never().");

	    return capturedResults.get(0);
	}

	public Collection<T> results() {
        Preconditions.checkState(!capturedResults.isEmpty(), "No result captured, invocation on results() is misleading. Instead, consider to use verify() with never().");

	    return ImmutableList.copyOf(capturedResults);
	}

	@Override
	public T answer(final InvocationOnMock invocationOnMock) throws Throwable {
		T capturedResult = (T) invocationOnMock.callRealMethod();
        capturedResults.add(capturedResult);
        return capturedResult;
	}

}