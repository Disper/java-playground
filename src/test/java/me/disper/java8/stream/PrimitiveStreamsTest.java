package me.disper.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class PrimitiveStreamsTest {
	@Test
	public void shouldSumUsingIntStream()
	{
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		//given

		//when
		final int sum = numbers.stream()
				.mapToInt(Integer::intValue)
				.sum();

		//then
		assertThat(sum).isEqualTo(15);
	}

	@Test
	public void shouldMinUsingPrimitiveStream()
	{
		//given
		List<Integer> numbers = Arrays.asList(999, 20, 2322, 14, 23);
		//when

		int result = numbers.stream()
				.mapToInt(Integer::intValue)
				.min().orElse(0);

		//then
		assertTrue(result == 14);
	}
}
