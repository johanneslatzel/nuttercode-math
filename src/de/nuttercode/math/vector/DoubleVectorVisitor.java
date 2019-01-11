package de.nuttercode.math.vector;

/**
 * used to visit entries of a {@link DoubleVector}
 * 
 * @author Johannes B. Latzel
 * @see #visit(int, int, double)
 *
 */
@FunctionalInterface
public interface DoubleVectorVisitor {

	/**
	 * visits entries of {@link DoubleVector}
	 * 
	 * @param index index
	 * @param value value index
	 */
	void visit(int index, double value);

}