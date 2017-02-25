package me.disper.java8.functional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

import org.assertj.core.data.Percentage;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;


public class CommonFunctionalInterfacesTest
{
	protected static final int EVEN_NUMBER = 2;
	protected static final String TEST_TEXT = "SOME_TEST_TEXT";
	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void testPredicateForPrimitiveInt()
	{
		IntPredicate evenNumber = value -> (value % EVEN_NUMBER) == 0;
		assertThat(evenNumber.test(EVEN_NUMBER)).isTrue();
	}

	@Test
	public void testPredicateForInteger()
	{
		Predicate<Integer> evenNumber = value -> (value % EVEN_NUMBER) == 0;
		assertThat(evenNumber.test(EVEN_NUMBER)).isTrue();
	}

	@Test
	public void testCommonConsumer()
	{
		IntConsumer consumer = System.out::print;
		consumer.accept(EVEN_NUMBER);

		assertThat(capture.toString()).contains(String.valueOf(EVEN_NUMBER));
	}

	@Test
	public void testCommonSupplier()
	{
		Random random = new Random();
		IntSupplier randomGenerator = () -> random.nextInt(2);

		assertThat(randomGenerator.getAsInt()).isCloseTo(1, Percentage.withPercentage(100));
	}

	@Test
	public void testCommonFunction()
	{
		ToIntFunction<String> stringSize = String::length;

		assertThat(stringSize.applyAsInt(TEST_TEXT)).isEqualTo(TEST_TEXT.length());
	}

	@Test
	public void testUnaryOperator()
	{
		UnaryOperator<String> lowerCaseMaker = String::toLowerCase;

		assertThat(lowerCaseMaker.apply(TEST_TEXT)).isEqualTo(TEST_TEXT.toLowerCase());
	}

	@Test
	public void testBinaryOperator()
	{
		DoubleBinaryOperator sumOperation = Double::sum;

		assertThat(sumOperation.applyAsDouble(2, 2)).isEqualTo(4);
	}

	@Test
	public void testBinaryPredicate()
	{
		BiPredicate<String, Integer> hasThatManyCharacters = (string, length) -> string.length() == length;

		assertThat(hasThatManyCharacters.test("YOLO", 4)).isTrue();
		assertThat(hasThatManyCharacters.test("Thank God it's not Monday", 4)).isFalse();
	}

	@Test
	public void testBinaryConsumer()
	{
		ObjIntConsumer<String> dayInMonthPrinter = (month, day) -> {
			String message = String.format("It's %s of %s", day, month);
			System.out.println(message);
		};

		dayInMonthPrinter.accept("January", 31);

		assertThat(capture.toString()).contains("It's 31 of January");
	}

	@Test
	public void testBinaryFunction()
	{
		ToIntBiFunction<String, String> sumOfCharacterInBothWords = (firstWord, secondWord) -> firstWord.length()
				+ secondWord.length();

		assertThat(sumOfCharacterInBothWords.applyAsInt("Apple", "Pie")).isEqualTo(8);
	}
}
