package de.nuttercode.math.function;

import java.io.Serializable;

import de.nuttercode.math.vector.DoubleVector;

/**
 * maps a {@link DoubleVectorFunction} value to another {@link DoubleVector}
 * value
 * 
 * @author Johannes B. Latzel
 *
 */
@FunctionalInterface
public interface DoubleVectorFunction extends Serializable {

	/**
	 * @param value
	 * @return return value of this function
	 */
	DoubleVector apply(DoubleVector value);

}
