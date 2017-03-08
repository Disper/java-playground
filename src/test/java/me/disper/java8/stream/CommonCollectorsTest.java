package me.disper.java8.stream;

import me.disper.data.PeopleGenerator;
import me.disper.model.Person;
import org.assertj.core.data.Percentage;
import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.assertj.core.api.Assertions.assertThat;


public class CommonCollectorsTest
{
	protected static final Integer NUMBER_OF_PEOPLE = 10;
	private List<Person> people = PeopleGenerator.generate(Math.toIntExact(NUMBER_OF_PEOPLE));

	@Test
	public void shouldCountElements()
	{
		// when
		Long sumUsingCounting = people.stream().collect(Collectors.counting());
		Long sumUsingCount = people.stream().count();
		Long sumUsingSize = (long) people.size();

		// then
		assertThat(NUMBER_OF_PEOPLE)
				.isCloseTo(Math.toIntExact(sumUsingCounting), Percentage.withPercentage(0))
				.isCloseTo(Math.toIntExact(sumUsingCount), Percentage.withPercentage(0))
				.isCloseTo(Math.toIntExact(sumUsingSize), Percentage.withPercentage(0));
	}

	@Test
	public void shouldReturnOldestAge()
	{
		//when
		Integer oldestAge = people.stream()
				.map(Person::getAge)
				.max(Integer::compare)
				.orElse(0);

		//then
		assertThat(oldestAge).isEqualTo(NUMBER_OF_PEOPLE);
	}

	@Test
	public void shouldReturnOldestMan()
	{
		//when
		Optional<Person> person = people.stream().max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

		//then

		assertThat(person.isPresent()).isTrue();
		assertThat(person.get().getAge()).isSameAs(NUMBER_OF_PEOPLE);
	}

	@Test
	public void shouldReturnTotalAges()
	{
		//when
		final int totalAgesReducing = people.stream().collect(reducing(0, Person::getAge, (a, b) -> a + b));
		final int totalAgesSummingInt = people.stream().collect(summingInt(Person::getAge));
		final int totalAgesReduce = people.stream().map(Person::getAge).reduce(0, (a, b) -> a + b);
		final int totalAgesMapToInt = people.stream().mapToInt(Person::getAge).sum();

		//then
		assertThat(totalAgesReducing)
				.isEqualTo(totalAgesSummingInt)
				.isEqualTo(totalAgesReduce)
				.isEqualTo(totalAgesMapToInt)
				.isEqualTo(55);
	}

	@Test
	public void shouldCreateIntSummaryStatistics()
	{
		//when
		IntSummaryStatistics statistics = people.stream().collect(summarizingInt(Person::getAge));

		//then
		assertThat(statistics.getAverage()).isEqualTo(5.5);
		assertThat(statistics.getCount()).isEqualTo(NUMBER_OF_PEOPLE.intValue());
		assertThat(statistics.getMax()).isEqualTo(NUMBER_OF_PEOPLE);
		assertThat(statistics.getMin()).isEqualTo(1);
		assertThat(statistics.getSum()).isEqualTo(55);
	}

	@Test
	public void shouldJoinStrings()
	{
		//when
		final String allNames = people.stream().map(Person::getName).collect(joining(", "));

		//then
		assertThat(allNames).contains("John1, ");
		assertThat(allNames).contains("John10");
	}
}
