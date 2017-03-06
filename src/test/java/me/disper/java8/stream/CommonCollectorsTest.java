package me.disper.java8.stream;

import me.disper.data.PeopleGenerator;
import me.disper.model.Person;
import org.assertj.core.data.Percentage;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}
