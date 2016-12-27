package it.ozimov.mockito.helpers.captors;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import it.ozimov.cirneco.hamcrest.java7.collect.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultCaptorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Spy
    public FixedReturnA returnA = new FixedReturnA();

    @Spy
    public FixedReturnB returnB = new FixedReturnB();

    @Mock
    public InvocationOnMock invocationOnMock;

    public ResultCaptor<String> resultCaptor = ResultCaptor.resultCaptor();

    @Before
    public void setUp() {
        Mockito.doAnswer(resultCaptor).when(returnA).returnSomething();
        Mockito.doAnswer(resultCaptor).when(returnB).returnSomething();
    }
    @Test
    public void testGivenNoCapturedValueWhenResultIsCalledThenIllegalStateExceptionIsThrown() throws Exception {

        //Arrange
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("No result captured, invocation on result() is misleading. Instead, consider to use verify() with never().");

        //Act + Assert
        resultCaptor.result();
    }

    @Test
    public void testGivenNoCapturedValueWhenResultsIsCalledThenIllegalStateExceptionIsThrown() throws Exception {

        //Arrange
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("No result captured, invocation on results() is misleading. Instead, consider to use verify() with never().");

        //Act + Assert
        resultCaptor.results();
    }


    @Test
    public void testGivenACapturedValueWhenResultIsCalledThenReturnFirstCapturedValue() throws Exception {

        //Act
        returnA.returnSomething();
        returnB.returnSomething();

        //Assert
        assertThat(resultCaptor.result(), Matchers.is(returnA.returnSomething()));
    }

    @Test
    public void testGivenACapturedValueWhenResultsIsCalledThenReturnAllCapturedValue() throws Exception {

        //Act
        returnA.returnSomething();
        returnB.returnSomething();
        returnA.returnSomething();

        //Assert
        assertThat(resultCaptor.results(), IsIterableContainingInOrder.containsInOrder(ImmutableList.of(
                returnA.returnSomething(),
                returnB.returnSomething(),
                returnA.returnSomething()
        )));
    }

    @Test
    public void answer() throws Throwable {
        //Arrange
        String expectedResult = "Hello all!";
        when(invocationOnMock.callRealMethod()).thenReturn(expectedResult);

        //Act
        String givenResult =resultCaptor.answer(invocationOnMock);

        //Assert
        verify(invocationOnMock).callRealMethod();
        assertThat(givenResult, is(expectedResult));
    }

    public class FixedReturnA {

        String returnSomething() {
            return "Hello World";
        }

    }

    public class FixedReturnB {

        String returnSomething() {
            return "Hello insane World";
        }

    }

}