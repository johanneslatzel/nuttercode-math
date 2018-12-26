package de.nuttercode.math.permutation;

/**
 * when applied: swaps i with j, vice versa, and behaves on all other values as
 * the identity-function.
 * 
 * @author Johannes B. Latzel
 *
 */
public class IntTransposition {

	private final int i, j;

	/**
	 * @param i
	 * @param j
	 */
	public IntTransposition(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * swaps i with j, vice versa, and behaves on all other values as the
	 * identity-function
	 * 
	 * @param value
	 * @return j, if value == i; i, if value == j; else value
	 */
	public int apply(int value) {
		if (value == i)
			return j;
		if (value == j)
			return i;
		return value;
	}

	/**
	 * @return i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @return j
	 */
	public int getJ() {
		return j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntTransposition other = (IntTransposition) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IntTransposition [i=" + i + ", j=" + j + "]";
	}

}
