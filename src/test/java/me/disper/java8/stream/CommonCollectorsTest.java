package me.disper.java8.stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.assertj.core.data.Percentage;
import org.junit.Test;

import me.disper.data.PeopleGenerator;
import me.disper.model.Person;


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
		assertThat(NUMBER_OF_PEOPLE).isCloseTo(Math.toIntExact(sumUsingCounting), Percentage.withPercentage(0))
				.isCloseTo(Math.toIntExact(sumUsingCount), Percentage.withPercentage(0))
				.isCloseTo(Math.toIntExact(sumUsingSize), Percentage.withPercentage(0));
	}

	@Test
	public void shouldReturnOldestAge()
	{
		//when
		Integer oldestAge = people.stream().map(Person::getAge).max(Integer::compare).orElse(0);

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
		assertThat(totalAgesReducing).isEqualTo(totalAgesSummingInt).isEqualTo(totalAgesReduce).isEqualTo(totalAgesMapToInt)
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
	public void shouldJoinStringsUsingJoining()
	{
		//when
		final String allNames = people.stream().map(Person::getName).collect(joining(", "));

		//then
		assertThat(allNames).contains("John1, ");
		assertThat(allNames).contains("John10");
	}

	@Test
	public void shouldJoinStringsUsingReduce()
	{
		//when
		final String allNames = people.stream().map(Person::getName).reduce("", (n1, n2) -> n1.concat(", ").concat(n2));

		//then
		assertThat(allNames).contains("John1, ");
		assertThat(allNames).contains("John10");
	}

	@Test
	public void shouldGroupByGender()
	{
		final int halfOfThePeople = NUMBER_OF_PEOPLE / 2;
		//when
		Map<Person.Gender, List<Person>> groupByGender = people.stream().collect(groupingBy(Person::getGender));

		Map<Person.Gender, Long> countByGender = people.stream().collect(groupingBy(Person::getGender, counting()));

		//then
		assertThat(groupByGender.get(Person.Gender.MALE)).size().isEqualTo(halfOfThePeople);
		assertThat(groupByGender.get(Person.Gender.FEMALE)).size().isEqualTo(halfOfThePeople);

		assertThat(countByGender.get(Person.Gender.MALE)).isEqualTo(halfOfThePeople);
		assertThat(countByGender.get(Person.Gender.FEMALE)).isEqualTo(halfOfThePeople);
	}

	@Test
	public void shouldGroupOldestByGender()
	{
		Map<Person.Gender, Person> groupByGender = people.stream()
				.collect(groupingBy(Person::getGender, collectingAndThen(
							maxBy(Comparator.comparingInt(Person::getAge)),
							Optional::get
						)
				));

		assertThat(groupByGender.get(Person.Gender.FEMALE)).isInstanceOf(Person.class);
		assertThat(groupByGender.get(Person.Gender.FEMALE).getAge()).isEqualTo(NUMBER_OF_PEOPLE);
		assertThat(groupByGender.get(Person.Gender.MALE).getAge()).isEqualTo(NUMBER_OF_PEOPLE - 1);
	}

	@Test
	public void shouldGroupMaxAgeByGender()
	{
		Map<Person.Gender, Integer> groupByGender = people.stream()
				.collect(groupingBy(Person::getGender, collectingAndThen(
						maxBy(Comparator.comparingInt(Person::getAge)),
						person -> person.get().getAge()
						)
				));

		assertThat(groupByGender.get(Person.Gender.FEMALE)).isEqualTo(NUMBER_OF_PEOPLE);
		assertThat(groupByGender.get(Person.Gender.MALE)).isEqualTo(NUMBER_OF_PEOPLE - 1);
	}
}
