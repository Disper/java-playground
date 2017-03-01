package me.disper.java8.stream;

import me.disper.model.Person;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class SimpleStreamTest
{

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void testStreamOfValues()
	{
		//given
		final String stringValue = "String";
		final int intValue = 14;
		final double doubleValue = 55.55;
		final Person person = new Person("John", "Doe");

		//when
		Stream.of(stringValue, intValue, doubleValue, person).forEach(System.out::println);

		//then
		assertThat(outputCapture.toString()).contains(stringValue);
		assertThat(outputCapture.toString()).contains(String.valueOf(intValue));
		assertThat(outputCapture.toString()).contains(String.valueOf(doubleValue));
		assertThat(outputCapture.toString()).contains(person.toString());
	}
}
