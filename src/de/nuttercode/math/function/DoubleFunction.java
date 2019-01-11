package de.nuttercode.math.function;

import java.io.Serializable;

/**
 * maps a double value to another double value
 * 
 * @author Johannes B. Latzel
 *
 */
@FunctionalInterface
public interface DoubleFunction extends Serializable {

	/**
	 * @param value
	 * @return return value of this function
	 */
	double apply(double value);

}
