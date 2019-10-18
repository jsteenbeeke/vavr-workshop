package nl.topicus.devdev.vavr;

import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import nl.topicus.devdev.vavr.model.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static nl.topicus.devdev.vavr.VavrMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class Assignment4Test
{
	private Assignment4 assignment4;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void createAssignment()
	{
		if (assignment4 == null)
		{
			assignment4 = new Assignment4();
		}
	}

	@Test public void grade_assignment_4_1()
	{
		Person jan = new Person("Jan").withSalary(new BigDecimal(2500));
		Person piet = new Person("Piet").withSalary(new BigDecimal(3000));
		Person klaas = new Person("Klaas").withSalary(new BigDecimal(4500));

		assumeThat("An implementation exists", assignment4.whoEarnsTheMost(List.empty()), notNullValue());
		assertThat(assignment4.whoEarnsTheMost(List.empty()), isNone());
		assertThat(assignment4.whoEarnsTheMost(List.of(jan)), isSome(jan));
		assertThat(assignment4.whoEarnsTheMost(List.of(jan, jan)), isSome(jan));
		assertThat(assignment4.whoEarnsTheMost(List.of(jan, piet, klaas)), isSome(klaas));
		assertThat(assignment4.whoEarnsTheMost(List.of(jan, piet, klaas, piet, piet)),
				isSome(klaas));
	}

	@Test
	public void grade_assignment_4_2()
	{
		Person jan = new Person("Jan").withSalary(new BigDecimal(2500));
		Person piet = new Person("Piet").withSalary(new BigDecimal(3000));
		Person klaas = new Person("Klaas").withSalary(new BigDecimal(4500));

		assumeThat("An implementation exists", assignment4.averageSalary(List.empty()), notNullValue());
		assertThat(assignment4.averageSalary(List.empty())
				.map(b -> b.setScale(2, RoundingMode.HALF_UP)), isNone());
		assertThat(assignment4.averageSalary(List.of(jan))
						.map(b -> b.setScale(2, RoundingMode.HALF_UP)),
				isSome(new BigDecimal(2500).setScale(2, RoundingMode.HALF_UP)));
		assertThat(assignment4.averageSalary(List.of(jan, jan))
						.map(b -> b.setScale(2, RoundingMode.HALF_UP)),
				isSome(new BigDecimal(2500).setScale(2, RoundingMode.HALF_UP)));
		assertThat(assignment4.averageSalary(List.of(jan, piet, klaas))
						.map(b -> b.setScale(2, RoundingMode.HALF_UP)),
				isSome(new BigDecimal(3333.33).setScale(2, RoundingMode.HALF_UP)));
		assertThat(assignment4.averageSalary(List.of(jan, piet, klaas, piet, piet))
						.map(b -> b.setScale(2, RoundingMode.HALF_UP)),
				isSome(new BigDecimal(3333.33).setScale(2, RoundingMode.HALF_UP)));
	}

	@Test
	public void grade_assignment_4_3()
	{
		BigDecimal case0 = assignment4.calculateActualBalance(BigDecimal.TEN, List.empty());

		assumeThat("An implementation exists", case0, notNullValue());
		assertThat(case0, equalTo(BigDecimal.TEN));

		BigDecimal case1 = assignment4.calculateActualBalance(BigDecimal.ZERO,
				List.of(BigDecimal.TEN, new BigDecimal(-20), new BigDecimal(100),
						new BigDecimal(-25)));

		assertThat(case1, notNullValue());
		assertThat(case1, equalTo(new BigDecimal(65).setScale(2, RoundingMode.HALF_UP)));

	}

	@Test
	public void grade_assignment_4_4()
	{
		List<Tuple3<String, BigDecimal, BigDecimal>> case0 = assignment4.createBalanceList(
				new Tuple3<>("Initial Balance", BigDecimal.ZERO, BigDecimal.ZERO), List.empty());

		assumeThat("An implementation exists", case0, notNullValue());
		assertThat(case0.size(), equalTo(1));

		List<Tuple3<String, BigDecimal, BigDecimal>> case1 = assignment4.createBalanceList(
				new Tuple3<>("Initial Balance", BigDecimal.ZERO, new BigDecimal(100)),
				List.of(new Tuple2<>("Entry fee", new BigDecimal(-5)),
						new Tuple2<>("Taxes", new BigDecimal(-50)),
						new Tuple2<>("Lunch", BigDecimal.TEN.negate()),
						new Tuple2<>("Found 10 euros on the street", BigDecimal.TEN)));

		assertThat(case1, notNullValue());
		assertThat(case1.size(), equalTo(5));
		assertThat(case1.get(0), equalTo(new Tuple3<>("Initial Balance",
				BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
				new BigDecimal(100).setScale(2, RoundingMode.HALF_UP))));
		assertThat(case1.get(1), equalTo(new Tuple3<>("Entry fee",
				new BigDecimal(-5).setScale(2, RoundingMode.HALF_UP),
				new BigDecimal(95).setScale(2, RoundingMode.HALF_UP))));
		assertThat(case1.get(2),
				equalTo(new Tuple3<>("Taxes", new BigDecimal(-50).setScale(2, RoundingMode.HALF_UP),
						new BigDecimal(45).setScale(2, RoundingMode.HALF_UP))));
		assertThat(case1.get(3), equalTo(new Tuple3<>("Lunch",
				BigDecimal.TEN.negate().setScale(2, RoundingMode.HALF_UP),
				new BigDecimal(35).setScale(2, RoundingMode.HALF_UP))));
		assertThat(case1.get(4), equalTo(new Tuple3<>("Found 10 euros on the street",
				BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP),
				new BigDecimal(45).setScale(2, RoundingMode.HALF_UP))));

	}
}