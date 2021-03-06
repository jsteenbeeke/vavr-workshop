package nl.topicus.devdev.vavr;

import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.control.Option;
import nl.topicus.devdev.vavr.model.Person;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Assignment 4: Working with collections
 */
public class Assignment4
{
	/**
	 * 4.1 - Find out which of the given persons has the highest salary, and return that person
	 */
	@Nonnull
	public Option<Person> whoEarnsTheMost(@Nonnull List<Person> persons)
	{
		return null;
	}

	/**
	 * 4.2 - Calculate the average salary of the given persons, ensuring no person is counted twice
	 * and that all salaries are weighted equally.
	 *
	 * Scale of resulting object (if any) should be 2
	 *
	 * For an additional challenge: do not convert the salaries to double
	 */
	@Nonnull
	public Option<BigDecimal> averageSalary(@Nonnull List<Person> persons)
	{
		return null;
	}

	/**
	 * 4.3 - Calculate the actual balance of a bank account. Ensure the scale of the resulting numbers
	 * is equal to 2
	 *
	 * Scale of resulting object should be 2
	 *
	 * Tip: use a fold method
	 */
	@Nonnull
	public BigDecimal calculateActualBalance(
			@Nonnull BigDecimal initialState,
			@Nonnull List<BigDecimal> mutations)
	{
		return null;
	}

	/**
	 * 4.4 - Create a list of bank account balances, based on a list of mutations. The resulting list
	 * should have elements of type Tuple3 containing the name of the mutation, the change in balance and the resulting balance. It
	 * should also include the initial state
	 *
	 * Scale of any BigDecimals in resulting list should be 2
	 *
	 * Tip: use scanLeft.
	 */
	@Nonnull
	public List<Tuple3<String, BigDecimal, BigDecimal>> createBalanceList(
			@Nonnull Tuple3<String, BigDecimal, BigDecimal> initialState,
			@Nonnull List<Tuple2<String, BigDecimal>> mutations)
	{
		return null;
	}

	/**
	 * 4.5 - Combine two lists, yielding a list containing tuples of values from both lists
	 *
	 * Tip: this can be done with a single command that only takes the second list as parameter
	 */
	@Nonnull
	public List<Tuple2<String,BigDecimal>> combineLists(@Nonnull List<String> first, @Nonnull List<BigDecimal> second) {
		return null;
	}
}
