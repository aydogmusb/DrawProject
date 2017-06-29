package example;

public class Person {
	public String name;
	public String surname;
	public int id;

	public Person(int id,String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.id = id;
	}
	@Override
	public String toString() {
		return "name=" + name + ", surname=" + surname + ", id=" + id;
	}
}
	