package me.disper.java8.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.junit.Test;


public class ComposingFunctionsTest
{
	@Test
	public void testAndThenVsCompose()
	{
		UnaryOperator<Integer> plusTwo = x -> x + 2;
		UnaryOperator<Integer> multiplyByTwo = x -> x * 2;

		Function<Integer, Integer> firstAddThenMultiply = plusTwo.andThen(multiplyByTwo);
		Function<Integer, Integer> firstMultiplyThenAdd = plusTwo.compose(multiplyByTwo);

		assertThat(firstAddThenMultiply.apply(2)).isEqualTo(8);
		assertThat(firstMultiplyThenAdd.apply(2)).isEqualTo(6);
	}
}
