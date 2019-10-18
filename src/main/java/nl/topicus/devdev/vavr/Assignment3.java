package nl.topicus.devdev.vavr;

import io.vavr.Function0;
import io.vavr.Value;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import nl.topicus.devdev.vavr.model.Person;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;

/**
 * Assignment 3: Values
 */
public class Assignment3
{
	/**
	 * 3.1 - Create an Option for the given value
	 */
	@Nonnull
	public Option<Integer> createOption(@Nullable Integer value)
	{
		return null;
	}

	/**
	 * 3.2 - Create a Try for dividing the given two values
	 */
	@Nonnull
	public Try<Integer> tryIntegerDivision(int a, int b)
	{
		return null;
	}

	/**
	 * 3.3 - Create an Either that represents the result of an unsafe division. An Either is a construct
	 * that contains either a result value (by convention: the right value) or an error state (the left value).
	 * <p>
	 * The goal of this assignment is to provide an {@code Either<String, Integer>} with the result of the division
	 * as the right value, or the exception message as the left value.
	 * <p>
	 * There are several ways to achieve this; pick whatever way you feel most comfortable with, but try
	 * to prevent using branching statements.
	 */
	@Nonnull
	public Either<String, Integer> eitherIntegerDivision(int a, int b)
	{
		return null;
	}

	/**
	 * 3.4 - Check the given input if the error in question is either an {@code IllegalStateException} or an {@code IllegalArgumentException}, and
	 * if so, return the value 0 instead. For all other errors, do nothing
	 */
	@Nonnull
	public Try<Integer> recovery(@Nonnull Try<Integer> possiblyContainsErrors)
	{
		return null;
	}

	/**
	 * 3.5 - Convert the given Option to an Either. If the Option has a value, the Either should have
	 * its value as right value. If the Option is empty, it should contain the message "no value given"
	 */
	@Nonnull
	public <T> Either<String, T> eitherize(@Nonnull Option<T> input)
	{
		return null;
	}

	/**
	 * 3.6 - Create a Value that defers the given computation to a later time
	 */
	@Nonnull
	public <T> Value<T> lazilyEvaluate(@Nonnull Function0<T> computation)
	{
		return null;
	}

	/**
	 * 3.7 - Create a Validation that checks if a person has a non-empty name, and
	 * a positive salary. The Validation can contain the following error messages:
	 * <p>
	 * - Name may not be empty
	 * - Salary must be positive
	 * <p>
	 * Creating a validation has multiple steps. First you create a validation for each
	 * input, then you combine these, and then ap(ply) them to a new Person object.
	 * <p>
	 * Tip: validation isn't entirely intuitive. If you're unsure how to proceed, check the manual
	 * on validation: https://www.vavr.io/vavr-docs/#_validation
	 * <p>
	 * For an additional challenge: use Option instead of conditional expressions.
	 */
	@Nonnull
	public Validation<Seq<String>, Person> validatePerson(@Nullable String name, @Nullable BigDecimal salary)
	{
		return null;
	}

}
