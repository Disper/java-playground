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
		for (int i = STARTING_AGE; i <= numberOfPeople; i++)
		{
			final String name = EXAMPLE_NAME.concat(String.valueOf(i));
			final String surname = EXAMPLE_SURNAME.concat(String.valueOf(i));
			people.add(new Person(name, surname, i));
		}
		return people;
	}
}
