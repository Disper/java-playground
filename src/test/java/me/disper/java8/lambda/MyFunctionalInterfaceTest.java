package me.disper.java8.lambda;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class MyFunctionalInterfaceTest
{
	protected static final String SHOULD_LOG_USING_THIS_LAMBDA = "Should log using this lambda";

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void shouldLogWithLambda()
	{
		MyFunctionalInterface lambda = () -> System.out.println(SHOULD_LOG_USING_THIS_LAMBDA);
		myTestMethod(lambda);

		assertThat(capture.toString()).contains(SHOULD_LOG_USING_THIS_LAMBDA);
	}

	@Test
	public void shouldBePossibleToUseDefaultMethodInFunctionalInterface(){
		MyFunctionalInterface myFunctionalInterface = () -> {};
		myFunctionalInterface.printDefaultMessage();

		assertThat(capture.toString()).contains(MyFunctionalInterface.DEFAULT_MESSAGE);
	}

	private void myTestMethod(MyFunctionalInterface myFunctionalInterface)
	{
		myFunctionalInterface.abstractMessagePrint();
	}

}
