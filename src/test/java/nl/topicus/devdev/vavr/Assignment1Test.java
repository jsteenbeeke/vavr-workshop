package nl.topicus.devdev.vavr;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import nl.topicus.devdev.vavr.model.Address;
import nl.topicus.devdev.vavr.model.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

/**
 * Grading of assignment 1
 */
public class Assignment1Test
{
	private Assignment1 assignment1;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void createAssignment()
	{
		if (assignment1 == null)
		{
			assignment1 = new Assignment1();
		}
	}

	@Test
	public void grade_assignment_1_1()
	{
		assumeThat("An implementation exists", assignment1.createEmptyArray(), notNullValue());
		assertThat(assignment1.createEmptyArray(), equalTo(Array.<String>empty()));
	}

	@Test
	public void grade_assignment_1_2()
	{
		Array<String> original = Array.of("awesome!", "is");

		assumeThat("An implementation exists", assignment1.addElement(original), notNullValue());

		assertThat(assignment1.addElement(original).reverse(),
				equalTo(Array.of("vavr", "is", "awesome!")));
	}

	@Test
	public void grade_assignment_1_3()
	{
		assumeThat("An implementation exists", assignment1.createList(), notNullValue());

		assertThat(assignment1.createList(), equalTo(List.empty()));

		assertThat(assignment1.createList("you", "should", "use", "vavr"),
				equalTo(List.of("you", "should", "use", "vavr")));

		assertThat(assignment1.createList("Topicus"), equalTo(List.of("Topicus")));
	}

	@Test
	public void grade_assignment_1_4()
	{
		assumeThat("An implementation exists", assignment1.mapToLength(List.of("vavr", "is", "awesome")), notNullValue());
		assertThat(assignment1.mapToLength(List.of("vavr", "is", "awesome")),
				equalTo(List.of(4, 2, 7)));
		assertThat(assignment1.mapToLength(List.of("you", "should", "use", "vavr")),
				equalTo(List.of(3, 6, 3, 4)));
	}

	@Test
	public void grade_assignment_1_5()
	{
		assumeThat("An implementation exists", assignment1.mapToLengthAndDiscardOddNumbers(List.of("vavr", "is", "awesome")), notNullValue());
		assertThat(assignment1.mapToLengthAndDiscardOddNumbers(List.of("vavr", "is", "awesome")),
				equalTo(List.of(4, 2)));
		assertThat(assignment1.mapToLengthAndDiscardOddNumbers(
				List.of("you", "should", "use", "vavr")), equalTo(List.of(6, 4)));
	}

	@Test
	public void grade_assignment_1_6()
	{
		Address leeuwenbrug23 = new Address("Leeuwenbrug", 23);
		Address singel9 = new Address("Singel", 9);
		Address singel25 = new Address("Singel", 25);
		Address sluisstraat6 = new Address("Sluisstraat", 6);

		Person john = new Person("John").withAddress(leeuwenbrug23).withAddress(singel9);
		Person steve = new Person("Steve").withAddress(singel25);
		Person bob = new Person("Bob").withAddress(sluisstraat6);

		assumeThat("An implementation exists", assignment1.personToAdddresses(List.of(steve, bob)), notNullValue());
		assertThat(assignment1.personToAdddresses(List.of(steve, bob)),
				equalTo(List.of(singel25, sluisstraat6)));
		assertThat(assignment1.personToAdddresses(List.of(john, steve)),
				equalTo(List.of(leeuwenbrug23, singel9, singel25)));
		assertThat(assignment1.personToAdddresses(List.of(john, steve, bob)),
				equalTo(List.of(leeuwenbrug23, singel9, singel25, sluisstraat6)));
	}

	@Test
	public void grade_assignment_1_7()
	{
		Address leeuwenbrug23 = new Address("Leeuwenbrug", 23);
		Address singel9 = new Address("Singel", 9);
		Address singel25 = new Address("Singel", 25);
		Address sluisstraat6 = new Address("Sluisstraat", 6);

		Person john = new Person("John").withAddress(leeuwenbrug23).withAddress(singel9);
		Person steve = new Person("Steve").withAddress(singel25);
		Person bob = new Person("Bob").withAddress(sluisstraat6);

		assumeThat("An implementation exists", assignment1.personToOddAdddresses(List.of(steve, bob)),
				notNullValue());

		assertThat(assignment1.personToOddAdddresses(List.of(steve, bob)),
				equalTo(List.of(singel25)));
		assertThat(assignment1.personToOddAdddresses(List.of(john, steve)),
				equalTo(List.of(leeuwenbrug23, singel9, singel25)));
		assertThat(assignment1.personToOddAdddresses(List.of(john, steve, bob)),
				equalTo(List.of(leeuwenbrug23, singel9, singel25)));
	}

	@Test(timeout = 5000L)
	public void grade_assignment_1_8()
	{
		Stream<Long> positiveOddNumbers = assignment1.createPositiveOddNumbers();

		assumeThat("An implementation exists", positiveOddNumbers, notNullValue());

		Stream<Long> firstHundredOdd = Stream.iterate(1L, i -> i = i + 2).take(500);
		Stream<Long> firstHundredEven = Stream.iterate(2L, i -> i = i + 2).take(500);

		assertTrue(positiveOddNumbers.containsAll(firstHundredOdd));
		assertTrue(firstHundredEven.find(positiveOddNumbers::contains).isEmpty());

		assertFalse(positiveOddNumbers.contains(-1L));
		assertFalse(positiveOddNumbers.contains(0L));
		assertTrue(positiveOddNumbers.contains(999L));
		assertFalse(positiveOddNumbers.contains(1000L));
		assertFalse(positiveOddNumbers.contains(1001L));
	}

	@Test
	public void grade_assignment_1_9() {
		Array<Long> a = Array.of(1L, 3L, 3L, 7L);

		java.util.List<Long> immutableJava = assignment1.convertToJava(a);

		assumeThat("An implementation exists", immutableJava, notNullValue());
		assertEquals(immutableJava.size(), 4);
		assertEquals((long) immutableJava.get(0), 1L);
		assertEquals((long) immutableJava.get(1), 3L);
		assertEquals((long) immutableJava.get(2), 3L);
		assertEquals((long) immutableJava.get(3), 7L);

		immutableJava.add(5L);

		assertEquals(immutableJava.size(), 5);
		assertEquals((long) immutableJava.get(4), 5L);

		immutableJava.remove(4);

		assertEquals(immutableJava.size(), 4);
		assertEquals((long) immutableJava.get(0), 1L);
		assertEquals((long) immutableJava.get(1), 3L);
		assertEquals((long) immutableJava.get(2), 3L);
		assertEquals((long) immutableJava.get(3), 7L);
	}

	@Test
	public void grade_assignment_1_10() {
		Array<Long> a = Array.of(1L, 3L, 3L, 7L);

		java.util.List<Long> immutableJava = assignment1.convertToImmutableJava(a);

		assumeThat("An implementation exists", immutableJava, notNullValue());
		assertEquals(immutableJava.size(), 4);
		assertEquals((long) immutableJava.get(0), 1L);
		assertEquals((long) immutableJava.get(1), 3L);
		assertEquals((long) immutableJava.get(2), 3L);
		assertEquals((long) immutableJava.get(3), 7L);

		expectedException.expect(UnsupportedOperationException.class);

		immutableJava.add(5L);
	}
}
