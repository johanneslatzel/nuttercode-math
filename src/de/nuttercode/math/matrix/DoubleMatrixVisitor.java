package de.nuttercode.math.matrix;

/**
 * used to consume entries of a {@link DoubleMatrix}
 * 
 * @author Johannes B. Latzel
 * @see #visit(int, int, double)
 *
 */
@FunctionalInterface
public interface DoubleMatrixVisitor {

	/**
	 * visits entries of {@link DoubleMatrix}
	 * 
	 * @param i     row index
	 * @param j     column index
	 * @param value value of the matrix at (i, j)
	 */
	void visit(int i, int j, double value);

}