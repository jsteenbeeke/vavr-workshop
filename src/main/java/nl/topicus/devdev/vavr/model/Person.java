package nl.topicus.devdev.vavr.model;

import io.vavr.collection.List;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Objects;

public class Person
{
	private final String name;

	private final List<Address> addresses;

	private final BigDecimal salary;

	public Person(String name)
	{
		this(name, List.empty(), BigDecimal.ZERO);
	}

	private Person(String name, List<Address> addresses, BigDecimal salary)
	{
		this.name = name;
		this.addresses = addresses;
		this.salary = salary;
	}

	public Person withAddress(@Nonnull Address address) {
		return new Person(name, addresses.append(address), salary);
	}

	public Person withSalary(@Nonnull BigDecimal salary) {
		return new Person(name, addresses, salary);
	}

	public String getName()
	{
		return name;
	}

	public List<Address> getAddresses()
	{
		return addresses;
	}

	public BigDecimal getSalary()
	{
		return salary;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person person = (Person) o;
		return name.equals(person.name) && addresses.equals(person.addresses) &&
				salary.equals(person.salary);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name, addresses, salary);
	}
}
