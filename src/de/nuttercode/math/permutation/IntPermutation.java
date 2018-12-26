package de.nuttercode.math.permutation;

import java.util.ArrayList;
import java.util.List;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * permutation implementation for int values over a list of
 * {@link IntTransposition}s. see
 * <a href="https://en.wikipedia.org/wiki/Permutation">Permutation</a> for more
 * details. use {@link #chain(IntTransposition)} chains the new permutation to
 * this function.
 * 
 * @author Johannes B. Latzel
 *
 */
public class IntPermutation {

	private final List<IntTransposition> transpositionList;

	public IntPermutation() {
		transpositionList = new ArrayList<>();
	}

	/**
	 * applies this permutation on the value.
	 * 
	 * @param value
	 * @return the result of this function applied on the values
	 */
	public int apply(int value) {
		int result = value;
		for (IntTransposition transposition : transpositionList)
			result = transposition.apply(result);
		return result;
	}

	/**
	 * chains the new transposition to the previous transpositions.
	 * 
	 * @param transposition
	 */
	public void chain(@NotNull IntTransposition transposition) {
		Assurance.assureNotNull(transposition);
		transpositionList.add(transposition);
	}

	/**
	 * @return the inverse of this function
	 */
	public IntPermutation getInverse() {
		IntPermutation permutation = new IntPermutation();
		for (int a = transpositionList.size() - 1; a >= 0; a--)
			permutation.chain(transpositionList.get(a));
		return permutation;
	}

}
