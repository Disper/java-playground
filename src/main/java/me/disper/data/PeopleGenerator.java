package me.disper.data;

import me.disper.model.Person;

import java.util.ArrayList;
import java.util.List;


public class PeopleGenerator
{

	public static final String EXAMPLE_NAME = "John";
	public static final String EXAMPLE_SURNAME = "Doe";
	public static final int STARTING_AGE = 1;

	private PeopleGenerator()
	{
		// util
	}

	public static List<Person> generate(int numberOfPeople)
	{
		List<Person> people = new ArrayList<>();
		for (int currentAge = STARTING_AGE; currentAge <= numberOfPeople; currentAge++)
		{
			final String name = EXAMPLE_NAME.concat(String.valueOf(currentAge));
			final String surname = EXAMPLE_SURNAME.concat(String.valueOf(currentAge));
			final Person.Gender gender = (currentAge % 2 == 0) ? Person.Gender.FEMALE : Person.Gender.MALE;
			people.add(new Person(name, surname, currentAge, gender));
		}
		return people;
	}
}
