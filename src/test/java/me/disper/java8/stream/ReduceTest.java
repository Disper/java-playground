package me.disper.java8.stream;

import org.junit.Test;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ReduceTest {

	@Test
	public void shouldSumAllElements()
	{
		//given
		List<Integer> numbers = IntStream.range(1, 10)
				.boxed()
				.collect(toList());

		//when
		int result = numbers.stream()
				.reduce(0, Integer::sum);

		//then
		assertThat(result).isEqualTo(45);
	}

	@Test
	public void shouldMultiplyAllElements()
	{
		//given
		List<Integer> numbers = IntStream.range(1, 6)
				.boxed()
				.collect(toList());

		//when
		int result = numbers.stream()
				.reduce(1, (a, b) -> a * b);

		//then
		assertThat(result).isEqualTo(120);
	}

	@Test
	public void streamAndParrarelStreamMightReturnDifferentResult()
	{
		final int MULTIPLY_TIMES = 10;
		//given
		List<Integer> numbers = IntStream.range(1, MULTIPLY_TIMES)
				.boxed()
				.collect(toList());

		final BinaryOperator<Integer> mathOperation = (a, b) -> a + a * b;

		//when
		int streamResult = numbers.stream()
				.reduce(1, mathOperation);

		int parrarelStreamResult = numbers.parallelStream()
				.reduce(1, mathOperation);

		//then
		assertThat(parrarelStreamResult).isNotEqualTo(streamResult);
	}
}
