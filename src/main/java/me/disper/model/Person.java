package me.disper.model;

public class Person {
	private String name;
	private String surname;
	private int age;
	private Gender gender;

	public Person(final String name, final String surname) {
		this(name, surname, 0);
	}

	public Person(final String name, final String surname, final int age) {
		this(name, surname, age, Gender.UKNOWN);
	}

	public Person(final String name, final String surname, final int age, final Gender gender) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", age=" + age +
				", sex=" + gender +
				'}';
	}

	public enum Gender {
		MALE,
		FEMALE,
		UKNOWN
	}
}
