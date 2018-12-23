package de.nuttercode.math;

import java.util.Arrays;

import de.nuttercode.util.ArrayUtil;
import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * wraps around a double[] to provide
 * 
 * @author johannes
 *
 */
public class RealVector {

	/**
	 * underlying array
	 */
	private final double[] values;

	/**
	 * vector with given dimension. values have default double-values.
	 * 
	 * @param dimension
	 */
	public RealVector(int dimension) {
		values = new double[dimension];
	}

	/**
	 * deep-copy constructor
	 * 
	 * @param vector
	 */
	public RealVector(@NotNull RealVector vector) {
		this(Assurance.assureNotNull(vector).values);
	}

	/**
	 * the dimension of this vector will be array.length and the values of the array
	 * will be copied into this vector
	 * 
	 * @param array
	 */
	public RealVector(double[] array) {
		values = Arrays.copyOf(array, array.length);
	}

	/**
	 * @param vector
	 * @throws IllegalArgumentException if vector == null or if
	 *                                  vector.getDimension() != getDimension()
	 */
	private void assureSameDimension(@NotNull RealVector vector) {
		Assurance.assureNotNull(vector);
		if (vector.getDimension() != getDimension())
			throw new IllegalArgumentException(
					"dimension of argument vector is " + vector.getDimension() + " != " + getDimension());
	}

	/**
	 * sets the value at the index to the given value
	 * 
	 * @param value
	 * @param index
	 */
	public void setValue(double value, int index) {
		values[index] = value;
	}

	/**
	 * @param index
	 * @return the value at the index
	 */
	public double getValue(int index) {
		return values[index];
	}

	/**
	 * @return the dimension of this vector
	 */
	public int getDimension() {
		return values.length;
	}

	/**
	 * swaps the values at the sourceIndex with the destinationindex
	 * 
	 * @param sourceIndex
	 * @param destinationIndex
	 */
	public void swapValues(int sourceIndex, int destinationIndex) {
		ArrayUtil.swap(values, sourceIndex, destinationIndex);
	}

	/**
	 * scales the value at the index by scalar
	 * 
	 * @param scalar
	 * @param index
	 */
	public void scaleValue(double scalar, int index) {
		values[index] *= scalar;
	}

	/**
	 * scales all values of this vector by the given scalar
	 * 
	 * @param scalar
	 */
	public void scale(double scalar) {
		for (int i = 0; i < values.length; i++)
			scaleValue(scalar, i);
	}

	/**
	 * adds the values of the vector to this vector's values
	 * 
	 * @param vector
	 * @throws IllegalArgumentException if vector == null or if
	 *                                  vector.getDimension() != getDimension()
	 */
	public void add(@NotNull RealVector vector) {
		assureSameDimension(vector);
		for (int i = 0; i < values.length; i++) {
			setValue(getValue(i) + vector.getValue(i), i);
		}
	}

	/**
	 * @param vector
	 * @return scalar product of two vectors
	 * @throws IllegalArgumentException if vector == null or if
	 *                                  vector.getDimension() != getDimension()
	 */
	public double scalarProduct(@NotNull RealVector vector) {
		assureSameDimension(vector);
		double value = 0;
		for (int i = 0; i < values.length; i++) {
			value += getValue(i) * vector.getValue(i);
		}
		return value;
	}

	/**
	 * adds value of destination index to scalar * value of source index and saves
	 * the result at the destination index
	 * 
	 * @param source      source index
	 * @param destination destination index
	 * @param scalar      some scalar value
	 */
	public void addValue(int source, int destination, double scalar) {
		setValue(getValue(destination) + getValue(source) * scalar, destination);
	}

	@Override
	public String toString() {
		return "RealVector [values=" + Arrays.toString(values) + "]";
	}

}
