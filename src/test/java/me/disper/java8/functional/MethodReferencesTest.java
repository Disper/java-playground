package me.disper.java8.functional;

import me.disper.model.Person;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodReferencesTest {

	protected static final String NAME = "John";
	protected static final String SURNAME = "Snow";

	@Test
	public void shouldCreateNewPerson()
	{
		BiFunction<String, String, Person> personFactory = Person::new;

		Person person = personFactory.apply(NAME, SURNAME);

		assertThat(person.getName()).isEqualTo(NAME);
		assertThat(person.getSurname()).isEqualTo(SURNAME);
		assertThat(person.getAge()).isEqualTo(0);
	}
}
