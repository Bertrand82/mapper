package bg.test.p2;

public class Personn {
	@Override
	public String toString() {
		return "Personn [name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	String name;
	String firstName;
	String lastName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Personn() {

	}

}
