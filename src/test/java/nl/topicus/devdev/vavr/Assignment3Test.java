package nl.topicus.devdev.vavr;

import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import nl.topicus.devdev.vavr.model.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static nl.topicus.devdev.vavr.VavrMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class Assignment3Test
{
	private static final long[] FIBONACCI =
			{0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L,
					1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L,
					196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L,
					9227465L, 14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L};

	private Assignment3 assignment3;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void createAssignment()
	{
		if (assignment3 == null)
		{
			assignment3 = new Assignment3();
		}
	}

	@Test
	public void grade_assignment_3_1()
	{
		assumeThat("An implementation exists", assignment3.createOption(null), notNullValue());
		assertThat(assignment3.createOption(null), isNone());
		assertThat(assignment3.createOption(5), isSome(5));
		assertThat(assignment3.createOption(1337), isSome(1337));
		assertThat(assignment3.createOption(Integer.MAX_VALUE),
				equalTo(Option.some(Integer.MAX_VALUE)));
	}

	@Test
	public void grade_assignment_3_2()
	{
		assumeThat("An implementation exists", assignment3.tryIntegerDivision(4, 2), notNullValue());
		assertThat(assignment3.tryIntegerDivision(4, 2), equalTo(Option.some(2).toTry()));
		assertThat(assignment3.tryIntegerDivision(8, 2), equalTo(Option.some(4).toTry()));
		assertThat(assignment3.tryIntegerDivision(16, 2), equalTo(Option.some(8).toTry()));

		assertThat(assignment3.tryIntegerDivision(1, 0),
				isFailure(ArithmeticException.class, "/ by zero"));
	}

	@Test
	public void grade_assignment_3_3()
	{
		assumeThat("An implementation exists", assignment3.eitherIntegerDivision(4, 2), notNullValue());
		assertThat(assignment3.eitherIntegerDivision(4, 2), isRight(2));
		assertThat(assignment3.eitherIntegerDivision(8, 2), isRight(4));
		assertThat(assignment3.eitherIntegerDivision(16, 2), isRight(8));

		assertThat(assignment3.eitherIntegerDivision(1, 0), isLeft("/ by zero"));
	}

	@Test
	public void grade_assignment_3_4()
	{
		assumeThat("An implementation exists", assignment3.recovery(Try.success(5)), notNullValue());
		assertThat(assignment3.recovery(Try.success(-5)), isSuccess(-5));
		assertThat(assignment3.recovery(Try.success(null)), isSuccess(null));
		assertThat(assignment3.recovery(Try.failure(new IllegalStateException())), isSuccess(0));
		assertThat(assignment3.recovery(Try.failure(new IllegalArgumentException())), isSuccess(0));
		assertThat(assignment3.recovery(Try.failure(new NullPointerException())),
				isFailure(NullPointerException.class, null));
	}

	@Test
	public void grade_assignment_3_5()
	{
		assumeThat("An implementation exists", assignment3.eitherize(Option.some(null)),
				notNullValue());
		assertThat(assignment3.eitherize(Option.some(null)), isRight(null));
		assertThat(assignment3.eitherize(Option.of(5)), isRight(5));
		assertThat(assignment3.eitherize(Option.of("vavr")), isRight("vavr"));
		assertThat(assignment3.eitherize(Option.none()), isLeft("no value given"));
		assertThat(assignment3.eitherize(Option.of(null)), isLeft("no value given"));
	}

	@Test(timeout = 100L)
	public void grade_assignment_3_6_speed()
	{
		assumeThat("An implementation exists", assignment3.lazilyEvaluate(() -> 5), notNullValue());

		for (int i = 0; i < FIBONACCI.length; i++)
		{
			final int n = i;
			assertThat(assignment3.lazilyEvaluate(() -> slowFibonacci(n)), notNullValue());
		}
	}

	@Test
	public void grade_assignment_3_6_correctness()
	{
		assumeThat("An implementation exists", assignment3.lazilyEvaluate(() -> 5), notNullValue());

		for (int i = 0; i < FIBONACCI.length; i++)
		{
			final int n = i;
			assertThat(assignment3.lazilyEvaluate(() -> slowFibonacci(n)).get(),
					equalTo(FIBONACCI[n]));

		}
	}

	@Test
	public void grade_assignment_3_7()
	{
		assumeThat("An implementation exists", assignment3.validatePerson(null, null),
				notNullValue());
		assertThat(assignment3.validatePerson(null, BigDecimal.TEN),
				isInvalid(List.of("Name may not be empty")));
		assertThat(assignment3.validatePerson("", BigDecimal.TEN),
				isInvalid(List.of("Name may not be empty")));
		assertThat(assignment3.validatePerson("Joe", null),
				isInvalid(List.of("Salary must be positive")));
		assertThat(assignment3.validatePerson("Joe", BigDecimal.ZERO),
				isInvalid(List.of("Salary must be positive")));
		assertThat(assignment3.validatePerson("Joe", BigDecimal.ONE.negate()),
				isInvalid(List.of("Salary must be positive")));
		assertThat(assignment3.validatePerson("Joe", BigDecimal.TEN),
				isValid(new Person("Joe").withSalary(BigDecimal.TEN)));

	}

	private long slowFibonacci(int n)
	{
		switch (n)
		{
			case 0:
				return 0L;
			case 1:
				return 1L;
			default:
				return slowFibonacci(n - 1) + slowFibonacci(n - 2);
		}
	}

}
