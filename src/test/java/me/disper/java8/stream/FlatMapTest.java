package me.disper.java8.stream;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class FlatMapTest {
	@Test
	public void shouldCreateAListOfDistincCharacters()
	{
		//given
		final String[] arrayOfWords = {"Hello", "World" };

		//when
		final List<String> distintCharacters = Arrays.stream(arrayOfWords)
				.map(word -> word.split(""))
//				.map(Arrays::stream) //Wouldn't work because it would produce Stream<String> instead of String
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());

		final String result = StringUtils.collectionToDelimitedString(distintCharacters, "");

		//then
		assertThat(result).isEqualTo("HeloWrd");
	}

	@Test
	public void shouldReturnPairOfNumber()
	{
		//given
		List<Integer> firstList = Arrays.asList(1, 2, 3);
		List<Integer> secondList = Arrays.asList(4, 5);

		//when
		List<int[]> pairs = firstList.stream()
				.flatMap(first -> secondList.stream()
					.map(second -> new int[]{first, second})
				)
				.collect(toList());

		//then
		List<int[]> expectedResult = Arrays.asList(
				new int[]{1, 4}, new int[]{1, 5},
				new int[]{2, 4}, new int[]{2, 5},
				new int[]{3, 4}, new int[]{3, 5});

		assertThat(pairs).hasSize(6);
		assertThat(pairs).isSubsetOf(expectedResult);
	}
}
