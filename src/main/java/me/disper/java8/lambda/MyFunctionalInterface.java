package me.disper.java8.lambda;

@FunctionalInterface
public interface MyFunctionalInterface
{
	String DEFAULT_MESSAGE = "default message";

	void abstractMessagePrint();

	// Won't compile beacause @FunctionalInterface can have only one abstract method
	// void secondAbstractMethod();

	// Compiles because default method is not abstract.
	default void printDefaultMessage()
	{
		System.out.println(DEFAULT_MESSAGE);
	}
}
