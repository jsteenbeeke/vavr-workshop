package nl.topicus.devdev.vavr;

import io.vavr.Tuple2;
import io.vavr.collection.*;
import io.vavr.control.Option;
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
 * Grading of assignment 2
 */
public class Assignment2Test
{
	private Assignment2 assignment2;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void createAssignment()
	{
		if (assignment2 == null)
		{
			assignment2 = new Assignment2();
		}
	}

	@Test
	public void grade_assignment_2_1()
	{
		Set<Integer> leet = assignment2.createSet(1, 3, 3, 7);

		assumeThat("An implementation exists",leet, notNullValue());
		assertThat(leet.size(), equalTo(3));
		assertTrue(leet.contains(1));
		assertTrue(leet.contains(3));
		assertTrue(leet.contains(7));
		assertFalse(leet.contains(9));

		Set<Integer> empty = assignment2.createSet();

		assertThat(empty.size(), equalTo(0));

		Set<Integer> justOne = assignment2.createSet(1);

		assertThat(justOne.size(), equalTo(1));
	}

	@Test
	public void grade_assignment_2_2()
	{
		Map<String, Integer> map = assignment2.createHashMap();

		assumeThat("An implementation exists",map, notNullValue());
		assertThat(map.size(), equalTo(0));
	}

	@Test
	public void grade_assignment_2_3()
	{
		Multimap<Integer, String> multimap = assignment2.createMultiMapWithDuplicateValuesAllowed();

		assumeThat("An implementation exists",multimap, notNullValue());
		assertThat(multimap.size(), equalTo(0));

		multimap = multimap.put(5, "test");
		multimap = multimap.put(5, "test");
		multimap = multimap.put(4, "test");

		assertThat(multimap.size(), equalTo(3));
		assertTrue(multimap.contains(new Tuple2<>(5, "test")));
		assertTrue(multimap.contains(new Tuple2<>(4, "test")));

		Option<Traversable<String>> five = multimap.get(5);

		assertTrue(five.isDefined());
		assertThat(five.get().size(), equalTo(2));
	}

	@Test
	public void grade_assignment_2_4()
	{
		Multimap<Integer, String> multimap = assignment2.createMultiMapWithDuplicateValuesNotAllowed();

		assumeThat("An implementation exists",multimap, notNullValue());
		assertThat(multimap.size(), equalTo(0));

		multimap = multimap.put(5, "test");
		multimap = multimap.put(5, "test");
		multimap = multimap.put(4, "test");

		assertThat(multimap.size(), equalTo(2));
		assertTrue(multimap.contains(new Tuple2<>(5, "test")));
		assertTrue(multimap.contains(new Tuple2<>(4, "test")));

		Option<Traversable<String>> five = multimap.get(5);

		assertTrue(five.isDefined());
		assertThat(five.get().size(), equalTo(1));
	}

	@Test
	public void grade_assignment_2_5()
	{
		assumeThat("An implementation exists",assignment2.removeOddKeys(HashMap.empty()), notNullValue());
		assertThat(assignment2.removeOddKeys(HashMap.empty()),
				equalTo(HashMap.empty()));

		assertThat(assignment2.removeOddKeys(HashMap.of(1, "one", 2, "two", 3, "three")),
				equalTo(HashMap.of(2, "two")));

		assertThat(assignment2.removeOddKeys(HashMap.of(4, "four", 2, "two")),
				equalTo(HashMap.of(2, "two", 4, "four")));
	}

	@Test
	public void grade_assignment_2_6()
	{
		assumeThat("An implementation exists",assignment2.removeUppercaseValues(HashMap.empty()), notNullValue());
		assertThat(assignment2.removeUppercaseValues(HashMap.empty()),
				equalTo(HashMap.empty()));

		assertThat(assignment2.removeUppercaseValues(HashMap.of(1, "ONE", 2, "two", 3, "three")),
				equalTo(HashMap.of(2, "two", 3, "three")));

		assertThat(assignment2.removeUppercaseValues(HashMap.of(4, "FOUR", 2, "two")),
				equalTo(HashMap.of(2, "two")));
	}
}
