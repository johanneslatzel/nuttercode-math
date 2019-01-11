package de.nuttercode.math.function;

import de.nuttercode.math.vector.DoubleVector;

/**
 * maps a pair of {@link DoubleVector}s to a double value
 * 
 * @author Johannes B. Latzel
 *
 */
@FunctionalInterface
public interface BiDoubleVectorToDoubleFunction {

	/**
	 * @param v
	 * @param w
	 * @return return value of this function
	 */
	double apply(DoubleVector v, DoubleVector w);

}
