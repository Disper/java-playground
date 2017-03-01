package me.disper.java8.stream;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class InfiniteStreamsTest
{
	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void shouldProduceNext20FibonacciSeries()
	{
		//when
		//applies successfully a function to generated value
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+ t[1]})
				.limit(20)
				.forEach(t -> System.out.println(t[0] + "," + t[1]));

		//then
		assertThat(outputCapture.toString()).contains("0,1", "8,13", "144,233");
	}

	@Test
	public void shouldGenerateNumbers()
	{
		//given
		final int STREAM_SIZE = 5;

		//when
		//generate doesn't apply any function to generated value
		List<Double> numbers = Stream.generate(Math::random).limit(STREAM_SIZE).collect(Collectors.toList());
		final double sum = numbers.stream().reduce(0.0, (a, b) -> a + b);

		//then
		assertThat(numbers).hasSize(STREAM_SIZE);
		assertThat(sum).isBetween(0.0, 5.0);
	}
}
