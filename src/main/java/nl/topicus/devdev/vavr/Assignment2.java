package nl.topicus.devdev.vavr;

import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Set;

import javax.annotation.Nonnull;

/**
 * Assignment 2: vavr sets, maps and multimaps
 *
 * In this assignment we'll have a further look into collections, this time looking into
 * sets, maps (which should be familiar from Java's util package), as well as multimaps, and try out
 * a number of basic operations that are similar to those found in the Java 8 Stream API.
 */
public class Assignment2
{
	/**
	 * 2.1 - Create a set containing the specified numbers
	 */
	@Nonnull
	public Set<Integer> createSet(int... numbers) {
		return HashSet.ofAll(numbers);
	}

	/**
	 * 2.2 - Create an empty HashMap with String keys and Integer values
	 */
	@Nonnull
	public HashMap<String,Integer> createHashMap() {
		return HashMap.empty();
	}

	/**
	 * 2.3 - Create an empty multimap with integer keys that allows duplicate values
	 */
	@Nonnull
	public Multimap<Integer,String> createMultiMapWithDuplicateValuesAllowed() {
		return LinkedHashMultimap.withSeq().empty();
	}

	/**
	 * 2.4 - Create an empty multimap with integer keys that does not allow duplicate values
	 */
	@Nonnull
	public Multimap<Integer,String> createMultiMapWithDuplicateValuesNotAllowed() {
		return LinkedHashMultimap.withSet().empty();
	}

	/**
	 * 2.5 - Remove all entries with odd keys from a map
	 */
	@Nonnull
	public Map<Integer,String> removeOddKeys(@Nonnull Map<Integer,String> input) {
		return input.filterKeys(i -> (i & 1) == 0);
	}

	/**
	 * 2.6 - Remove all entries with uppercase values from a map
	 */
	@Nonnull
	public Map<Integer,String> removeUppercaseValues(@Nonnull Map<Integer,String> input) {
		return input.filterValues(s -> s.toLowerCase().equals(s));
	}
}
