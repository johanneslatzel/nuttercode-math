package de.nuttercode.math;

/**
 * used to consume entries of a {@link RealMatrix}
 * 
 * @author Johannes B. Latzel
 * @see #consume(int, int, double)
 *
 */
@FunctionalInterface
public interface RealMatrixConsumer {

	/**
	 * consumes entries of {@link RealMatrix}
	 * 
	 * @param i     row index
	 * @param j     column index
	 * @param value value of the matrix at (i, j)
	 */
	void consume(int i, int j, double value);

}