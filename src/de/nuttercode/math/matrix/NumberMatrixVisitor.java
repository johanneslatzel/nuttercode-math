package de.nuttercode.math.matrix;

/**
 * used to consume entries of a {@link NumberMatrix}
 * 
 * @author Johannes B. Latzel
 * @see #visit(int, int, double)
 *
 */
@FunctionalInterface
public interface NumberMatrixVisitor<T extends Number> {

	/**
	 * visit entries of {@link NumberMatrix}
	 * 
	 * @param i     row index
	 * @param j     column index
	 * @param value value of the matrix at (i, j)
	 */
	void visit(int i, int j, T value);

}