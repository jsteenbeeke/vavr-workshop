package nl.topicus.devdev.vavr;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function4;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import nl.topicus.devdev.vavr.model.LogLine;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

/**
 * Assignment 5 - Fun with functions
 */
public class Assignment5
{
	/**
	 * Assignment 5.1 - Partially apply the given function with the given value
	 */
	@Nonnull
	public Function1<Integer,Integer> partialApplication(@Nonnull Integer value, @Nonnull Function2<Integer,Integer,Integer> function) {
		return function.apply(value);
	}

	/**
	 * Assignment 5.2a - Make the given unsafe function safe.
	 *
	 * Tip: this technique is known as lifting
	 */
	@Nonnull
	public Function2<Integer, Integer, Option<Integer>> makeSafe(@Nonnull Function2<Integer,Integer,Integer> unsafeFunction) {
		return Function2.lift(unsafeFunction);
	}

	/**
	 * Assignment 5.2b - Make the given unsafe function safe.
	 *
	 * Tip: this technique is known as lifting
	 */
	@Nonnull
	public Function4<Integer, Integer, Integer, Integer, Option<Integer>> makeSafe(@Nonnull Function4<Integer, Integer, Integer, Integer, Integer> unsafeFunction) {
		return Function4.lift(unsafeFunction);
	}

	/**
	 * Assignment 5.3 - Curry the given function, yielding a function that accepts a single parameter and
	 * yields another function (that in turn accepts a single parameter, and so on).
	 */
	@Nonnull
	public <T,U,V,W,X> Function1<T,Function1<U,Function1<V,Function1<W,X>>>> curryFunction(
			@Nonnull Function4<T, U, V, W, X> inputFunction
	) {
		return inputFunction.curried();
	}

	/**
	 * Assignment 5.4 - Compose the two given functions, yielding a single function that
	 * can convert straight from T to V.
	 *
	 * Tip: There are two ways to do this. Pick whichever method you find easiest to read.
	 */
	@Nonnull
	public <T,U,V> Function1<T,V> composition(@Nonnull Function1<T,U> first, @Nonnull Function1<U,V> second) {
		return second.compose(first); // Alternatively: first.AndThen(second);
	}

	/**
	 * Assignment 5.5 - Ensure that repeat invocations of the same function use a cached
	 * version of the function.
	 */
	@Nonnull
	public CheckedFunction1<Integer,Integer> cacheRepeatInvocations(@Nonnull
			CheckedFunction1<Integer,Integer> function) {
		return function.memoized();
	}

	/**
	 * Assignment 5.6 - Currying in action
	 * 
	 * Assign the constructor of the class LogLine to a Function4, curry the function, and
	 * apply the 2 given parameters to this function.
	 * 
	 * Return both the curried function, and the partially applied function
	 */
	@Nonnull
	public Tuple2<Function1<String, Function1<String, Function1<LocalDateTime, Function1<String, LogLine>>>>, Function1<LocalDateTime, Function1<String, LogLine>>> partiallyAppliedConstructor(
			@Nonnull String system, @Nonnull String user)
	{
		return null;
	}
}
