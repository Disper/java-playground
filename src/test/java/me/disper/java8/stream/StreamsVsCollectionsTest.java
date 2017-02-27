package me.disper.java8.stream;

import me.disper.data.PeopleGenerator;
import me.disper.model.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.containsString;


public class StreamsVsCollectionsTest
{
	protected static final int NUMBER_OF_PEOPLE = 10;
	@Rule
	public OutputCapture outputCapture = new OutputCapture();
	private List<Person> people;

	@Before
	public void setUp() throws Exception
	{
		people = PeopleGenerator.generate(NUMBER_OF_PEOPLE);
	}

	@Test
	public void shouldPrintOnlyLastThreeNames()
	{
		// given
		int limitTo = 3;
		final Comparator<Person> reverseByAge = (o1, o2) -> Integer.compare(o2.getAge(), o1.getAge());

		// when
		people.stream()
				.sorted(reverseByAge)
				.limit(limitTo)
				.forEach(System.out::println);

		final String firstPersonName = PeopleGenerator.EXAMPLE_NAME.concat(String.valueOf(NUMBER_OF_PEOPLE - limitTo + 1));
		final String lastPersonName = PeopleGenerator.EXAMPLE_NAME.concat(String.valueOf(NUMBER_OF_PEOPLE));
		outputCapture.expect(containsString(firstPersonName));
		outputCapture.expect(containsString(lastPersonName));
		// then
	}

	@Test
	public void collectionShouldIterateThroughAllElements()
	{
		// when
		for (Person person : people)
		{
			System.out.println(person.toString());
		}

		// then
		final String firstPersonName = PeopleGenerator.EXAMPLE_NAME.concat(String.valueOf(PeopleGenerator.STARTING_AGE));
		final String lastPersonName = PeopleGenerator.EXAMPLE_NAME.concat(String.valueOf(NUMBER_OF_PEOPLE));
		outputCapture.expect(containsString(firstPersonName));
		outputCapture.expect(containsString(lastPersonName));
	}
}
