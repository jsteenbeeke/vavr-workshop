package nl.topicus.devdev.vavr;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import nl.topicus.devdev.vavr.model.Address;
import nl.topicus.devdev.vavr.model.Person;

import javax.annotation.Nonnull;

/**
 * Assignment 1: vavr sequences.
 *
 * In this assignment you will create a number of basic Vavr sequences, and apply various
 * operations on them that should be familiar from the Java 8 Stream API.
 */
public class Assignment1
{
	/**
	 * 1.1 - Create an empty Array
	 */
	@Nonnull
	public Array<String> createEmptyArray()
	{
		return Array.empty();
	}

	/**
	 * 1.2 - Add the String "vavr" to a given Array
	 */
	@Nonnull
	public Array<String> addElement(@Nonnull Array<String> original)
	{
		return original.append("vavr");
	}

	/**
	 * 1.3 - Create a List from the given elements
	 */
	@Nonnull
	public List<String> createList(@Nonnull String... elements)
	{
		return List.of(elements);
	}

	/**
	 * 1.4 - Transform a list of String to a list of lengths
	 *
	 * i.e. - If an element is "vavr" then the output should be 4
	 */
	@Nonnull
	public List<Integer> mapToLength(@Nonnull List<String> input)
	{
		return input.map(String::length);
	}

	/**
	 * 1.5 - Transform a list of String to a list of lengths, but only if this yields an even number
	 *
	 * i.e. - If an element is "vavr" then the output should be 4
	 */
	@Nonnull
	public List<Integer> mapToLengthAndDiscardOddNumbers(@Nonnull List<String> input)
	{
		return input.map(String::length).filter(i -> (i & 1) != 1);
	}

	/**
	 * 1.6 - Transform a list of Persons into a list of addresses
	 */
	@Nonnull
	public List<Address> personToAdddresses(@Nonnull List<Person> persons)
	{
		return persons.flatMap(Person::getAddresses);
	}

	/**
	 * 1.7 - Transform a list of Persons into a list of addresses, giving back
	 * only addresses that have odd housenumbers
	 */
	@Nonnull
	public List<Address> personToOddAdddresses(@Nonnull List<Person> persons)
	{
		return persons.flatMap(Person::getAddresses).filter(a -> (a.getHouseNumber() & 1) == 1);
	}

	/**
	 * 1.8 - Create a Stream of all positive odd numbers less than 1000
	 */
	@Nonnull
	public Stream<Long> createPositiveOddNumbers()
	{
		return Stream.iterate(1L, i -> i + 2).takeUntil(l -> l >= 1000);
	}

	/**
	 * 1.9 - Convert an array to a Java list. There are several ways to do this, so please pick
	 * a method that yields a mutable Java list
	 */
	@Nonnull
	public java.util.List<Long> convertToJava(@Nonnull Array<Long> array)
	{
		return array.toJavaList(); // Or: array.asJavaMutable()
	}

	/**
	 * 1.10 - Convert an array to a Java list. There are several ways to do this, so please pick
	 * a method that yields an immutable Java list
	 */
	@Nonnull
	public java.util.List<Long> convertToImmutableJava(@Nonnull Array<Long> array)
	{
		return array.asJava();
	}

}
