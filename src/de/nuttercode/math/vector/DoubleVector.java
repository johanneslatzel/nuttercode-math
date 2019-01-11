package de.nuttercode.math.vector;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import de.nuttercode.util.ArrayUtil;
import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * wraps around a double[] to provide
 * 
 * @author Johannes B. Latzel
 *
 */
public class DoubleVector implements Vector, Serializable {

	private static final long serialVersionUID = 7281625750036740535L;

	/**
	 * underlying array
	 */
	private final double[] values;

	/**
	 * vector with given dimension. values have default double-values.
	 * 
	 * @param dimension
	 */
	public DoubleVector(int dimension) {
		values = new double[dimension];
	}

	/**
	 * deep-copy constructor
	 * 
	 * @param vector
	 */
	public DoubleVector(@NotNull DoubleVector vector) {
		this(Assurance.assureNotNull(vector).values);
	}

	/**
	 * the dimension of this vector will be array.length and the values of the array
	 * will be copied into this vector
	 * 
	 * @param array
	 */
	public DoubleVector(double[] array) {
		values = Arrays.copyOf(array, array.length);
	}

	/**
	 * @param vector
	 * @throws IllegalArgumentException if vector == null or if
	 *                                  vector.getDimension() != getDimension()
	 */
	private void assureSameDimension(@NotNull DoubleVector vector) {
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
	public void add(@NotNull DoubleVector vector) {
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
	public double scalarProduct(@NotNull DoubleVector vector) {
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

	/**
	 * @return index with the highest value in this vector
	 * @throws IllegalArgumentException if and only if getDimension() is not
	 *                                  positive
	 */
	public int getMaxIndex() {
		int dimension = getDimension();
		Assurance.assurePositive(dimension);
		int maxIndex = 0;
		double maxValue = getValue(0);
		double current;
		for (int a = 1; a < dimension; a++) {
			current = getValue(a);
			if (current > maxValue) {
				maxValue = current;
				maxIndex = a;
			}
		}
		return maxIndex;
	}

	/**
	 * @param visitor a visitor
	 */
	public void forEach(DoubleVectorVisitor visitor) {
		int dimension = getDimension();
		for (int i = 0; i < dimension; i++) {
			visitor.visit(i, getValue(i));
		}
	}

	/**
	 * sets this vectors components to random values of [-0.5, 0.5)
	 * 
	 * @param random
	 */
	public void randomize(Random random) {
		forEach((i, v) -> setValue(random.nextDouble() - 0.5, i));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
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
		DoubleVector other = (DoubleVector) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(values);
	}

}
