package me.disper.model;

public class Person {
	private String name;
	private String surname;
	private int age;

	public Person(final String name, final String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(final int age) {
		this.age = age;
	}
}
