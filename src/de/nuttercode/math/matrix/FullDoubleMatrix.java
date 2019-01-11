package de.nuttercode.math.matrix;

import java.io.Serializable;
import java.util.Random;

import de.nuttercode.util.ArrayUtil;
import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;
import de.nuttercode.util.assurance.Positive;

/**
 * implementation of {@link DoubleMatrix} with double arrays of fixed size.
 * 
 * @author Johannes B. Latzel
 *
 */
public class FullDoubleMatrix implements DoubleMatrix, Serializable {

	private static final long serialVersionUID = 4791467477622750766L;

	/**
	 * array representation of the matrix
	 */
	private final double[][] values;

	/**
	 * creates a new matrix with rowCount rows and columnCount columns
	 * 
	 * @param rowCount
	 * @param columnCount
	 */
	public FullDoubleMatrix(@Positive int rowCount, @Positive int columnCount) {
		Assurance.assurePositive(rowCount);
		Assurance.assurePositive(columnCount);
		values = new double[rowCount][columnCount];
	}

	/**
	 * creates a new quadratic matrix with size rows and columns
	 * 
	 * @param size
	 */
	public FullDoubleMatrix(@Positive int size) {
		this(size, size);
	}

	/**
	 * copy-constructor (deep)
	 * 
	 * @param matrix
	 */
	public FullDoubleMatrix(@NotNull FullDoubleMatrix matrix) {
		this(Assurance.assureNotNull(matrix).getRowCount(), matrix.getColumnCount());
		int rows = getRowCount();
		int cols = getColumnCount();
		for (int a = 0; a < rows; a++) {
			for (int b = 0; b < cols; b++) {
				values[a][b] = matrix.getValue(a, b);
			}
		}
	}

	/**
	 * creates the matrix represented by the array. array.length will be the number
	 * of rows and array[0].length the number of columns and \(a_{i, j} = \)
	 * array[i][j].
	 * 
	 * @param array
	 */
	public FullDoubleMatrix(@NotNull double[][] array) {
		this(Assurance.assureNotNull(array).length, Assurance.assureNotNull(array[0]).length);
		ArrayUtil.copy(array, values);
	}

	/**
	 * adds the scalar-scaled row source to the row destination and saves the result
	 * in the row destination
	 * 
	 * @param source      source row
	 * @param destination destination row
	 * @param scalar      some scalar
	 */
	public void addRow(int source, int destination, double scalar) {
		int cols = getColumnCount();
		for (int a = 0; a < cols; a++) {
			setValue(getValue(destination, a) + getValue(source, a) * scalar, destination, a);
		}
	}

	/**
	 * scales the row by scalar
	 * 
	 * @param scalar
	 * @param row
	 */
	public void scaleRow(double scalar, int row) {
		int cols = getColumnCount();
		for (int a = 0; a < cols; a++) {
			setValue(getValue(row, a) * scalar, row, a);
		}
	}

	/**
	 * sets all values of this matrix to the specified value
	 * 
	 * @param value
	 */
	public void setAllValuesTo(double value) {
		forEach((i, j, v) -> setValue(value, i, j));
	}

	/**
	 * sets this matrix components to random values of [-0.5, 0.5)
	 * 
	 * @param random
	 */
	public void randomize(@NotNull Random random) {
		forEach((i, j, v) -> setValue(random.nextDouble() - 0.5, i, j));
	}

	/**
	 * invokes {@link DoubleMatrixVisitor#visit(int, int, double)} for each entry in
	 * this matrix.
	 * 
	 * @see DoubleMatrix#forEach(DoubleMatrixVisitor)
	 */
	@Override
	public void forEach(DoubleMatrixVisitor consumer) {
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				consumer.visit(i, j, getValue(i, j));
			}
		}
	}

	@Override
	public void reset() {
		setAllValuesTo(0);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder((int) (Math.min(getRowCount() * getColumnCount() * 10, 1) * 10));
		int rows = getRowCount();
		int cols = getColumnCount();
		for (int a = 0; a < rows; a++) {
			if (a != 0)
				builder.append('\n');
			for (int b = 0; b < cols; b++) {
				if (b != 0)
					builder.append('\t');
				builder.append(getValue(a, b));
			}
		}
		return builder.toString();
	}

	@Override
	public double getValue(int row, int column) {
		return values[row][column];
	}

	@Override
	public void setValue(double value, int row, int column) {
		values[row][column] = value;
	}

	@Override
	public void swapRows(int row1, int row2) {
		double temp;
		int cols = getColumnCount();
		for (int a = 0; a < cols; a++) {
			temp = getValue(row1, a);
			setValue(getValue(row2, a), row1, a);
			setValue(temp, row2, a);
		}
	}

	@Override
	public int getRowCount() {
		return values.length;
	}

	@Override
	public int getColumnCount() {
		return values[0].length;
	}

}