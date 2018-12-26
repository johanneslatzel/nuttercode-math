package de.nuttercode.math.matrix;

/**
 * used to consume entries of a {@link RealMatrix}
 * 
 * @author Johannes B. Latzel
 * @see #visit(int, int, double)
 *
 */
@FunctionalInterface
public interface RealMatrixVisitor {

	/**
	 * visits entries of {@link RealMatrix}
	 * 
	 * @param i     row index
	 * @param j     column index
	 * @param value value of the matrix at (i, j)
	 */
	void visit(int i, int j, double value);

}