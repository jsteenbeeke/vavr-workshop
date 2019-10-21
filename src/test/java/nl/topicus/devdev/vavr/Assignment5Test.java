package nl.topicus.devdev.vavr;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function4;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import nl.topicus.devdev.vavr.model.LogLine;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

import static nl.topicus.devdev.vavr.VavrMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class Assignment5Test
{

	private Assignment5 assignment5;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void createAssignment()
	{
		if (assignment5 == null)
		{
			assignment5 = new Assignment5();
		}
	}

	@Test
	public void grade_assignment_5_1()
	{
		Function1<Integer, Integer> f1 = assignment5.partialApplication(5, Integer::sum);

		assumeThat("An implementation exists", f1, notNullValue());
		assertThat(f1.apply(5), equalTo(10));
		assertThat(f1.apply(6), equalTo(11));
		assertThat(f1.apply(7), equalTo(12));

		assertThat(f1.apply(-6), equalTo(-1));

	}

	@Test
	public void grade_assignment_5_2()
	{
		Function2<Integer, Integer, Integer> unsafeDivision = (a, b) -> a / b;

		Function2<Integer, Integer, Option<Integer>> safeDivision =
				assignment5.makeSafe(unsafeDivision);
		Function4<Integer, Integer, Integer, Integer, Integer> unsafeMath =
				(a, b, c, d) -> a + b / c - d;

		Function4<Integer, Integer, Integer, Integer, Option<Integer>> safeMath =
				assignment5.makeSafe(unsafeMath);

		assumeThat("An implementation exists", safeDivision, notNullValue());
		assumeThat(safeMath, notNullValue());

		assertThat(safeDivision.apply(4, 2), isSome(2));
		assertThat(safeDivision.apply(8, 2), isSome(4));
		assertThat(safeDivision.apply(4, -2), isSome(-2));
		assertThat(safeDivision.apply(4, 0), isNone());

		assertThat(safeMath.apply(1, 4, 2, 4), isSome(-1));
		assertThat(safeMath.apply(1, 4, 0, 4), isNone());
		assertThat(safeMath.apply(1, 1, 1, 1), isSome(0));

	}

	@Test
	public void grade_assignment_5_3()
	{
		Function4<String, Integer, String, Integer, String> uncurried =
				(a, b, c, d) -> a + b + c + d;

		Function1<String, Function1<Integer, Function1<String, Function1<Integer, String>>>>
				curried = assignment5.curryFunction(uncurried);

		assumeThat("An implementation exists", curried, notNullValue());
		assertThat(curried.apply("Steve").apply(5).apply("John").apply(6), equalTo("Steve5John6"));

	}

	@Test
	public void grade_assignment_5_4()
	{
		Function1<String, Integer> first = String::length;
		Function1<Integer, Integer> second = a -> a / 2;

		Function1<String, Integer> composed = assignment5.composition(first, second);

		assumeThat("An implementation exists", composed, notNullValue());
		assertThat(composed.apply("Steve"), equalTo(2));
		assertThat(composed.apply("Bob"), equalTo(1));
		assertThat(composed.apply(""), equalTo(0));
		assertThat(composed.apply("Zarathustra"), equalTo(5));

	}

	@Test(timeout = 5000L)
	public void grade_assignment_5_5() throws Throwable
	{
		CheckedFunction1<Integer, Integer> quadraticWithSideEffect = input -> {
			Thread.sleep(1000L); // Side effect. BAD JEROEN!!

			return input * input;
		};

		CheckedFunction1<Integer, Integer> memoized =
				assignment5.cacheRepeatInvocations(quadraticWithSideEffect);

		assumeThat("An implementation exists", memoized, notNullValue());

		// Should take 50 seconds if not properly memoized
		for (int i = 0; i < 50; i++)
		{
			assertThat(memoized.apply(5), equalTo(25));
		}

	}

	@Test
	public void grade_assignment_5_6()
	{
		final String SYSTEM = "Production";
		final String USER = "Jeroen";
		LocalDateTime NOW = LocalDateTime.now();
		String MESSAGE = "Performing unit test";

		Tuple2<Function1<String, Function1<String, Function1<LocalDateTime, Function1<String, LogLine>>>>, Function1<LocalDateTime, Function1<String, LogLine>>>
				functions = assignment5.partiallyAppliedConstructor(SYSTEM, USER);

		assumeThat("An implementation exists", functions, notNullValue());

		LogLine expected = new LogLine(SYSTEM, USER, NOW, MESSAGE);

		assertThat(functions._1.apply(SYSTEM)
				.apply(USER)
				.apply(NOW)
				.apply(MESSAGE), equalTo(expected));

		assertThat(functions._2.apply(NOW).apply(MESSAGE), equalTo(expected));

	}

}
