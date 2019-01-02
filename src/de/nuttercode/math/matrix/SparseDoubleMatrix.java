package de.nuttercode.math.matrix;

import java.util.HashMap;
import java.util.Map;

import de.nuttercode.math.matrix.DoubleMatrixVisitor;
import de.nuttercode.util.IntPair;
import de.nuttercode.util.Pair;
import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.Positive;

/**
 * implementation of {@link DoubleMatrix}. every index not already mapped by
 * {@link #setValue(double, int, int)} will be considered to have
 * {@link #defaultValue} as its value.
 * 
 * @author Johannes B. Latzel
 *
 */
public class SparseDoubleMatrix implements DoubleMatrix {

	/**
	 * default value used for unknown indices
	 */
	private final double defaultValue;

	/**
	 * maps indices to their corresponding values
	 */
	private final Map<IntPair, Double> values;

	/**
	 * number of rows
	 */
	private final int rowCount;

	/**
	 * number of columns
	 */
	private final int columnCount;

	/**
	 * exactly as if {@link #SparseRealMatrix(double) new SparseRealMatrix(0)} was
	 * called
	 * 
	 * @param rowCount    number of rows
	 * @param columnCount number of columns
	 */
	public SparseDoubleMatrix(int rowCount, int columnCount) {
		this(0, rowCount, columnCount);
	}

	/**
	 * creates a new {@link SparseDoubleMatrix} with default value defaultValue
	 * 
	 * @param defaultValue default value
	 * @param rowCount     number of rows
	 * @param columnCount  number of columns
	 */
	public SparseDoubleMatrix(double defaultValue, @Positive int rowCount, @Positive int columnCount) {
		Assurance.assurePositive(rowCount);
		Assurance.assurePositive(columnCount);
		this.defaultValue = defaultValue;
		values = new HashMap<>();
		this.rowCount = rowCount;
		this.columnCount = columnCount;
	}

	/**
	 * assures that i and j are in the range of indices of this matrix
	 * 
	 * @param i row
	 * @param j column
	 */
	private void assureIndices(int i, int j) {
		Assurance.assureBoundaries(i, 0, rowCount - 1);
		Assurance.assureBoundaries(j, 0, columnCount - 1);
	}

	/**
	 * @param i some row
	 * @param j some column
	 * @return true if and only if the pair (i, j) is mapped to a value in this
	 *         matrix
	 */
	public boolean hasValue(int i, int j) {
		return values.containsKey(Pair.of(i, j));
	}

	@Override
	public void setValue(double value, int i, int j) {
		assureIndices(i, j);
		values.put(Pair.of(i, j), value);
	}

	@Override
	public double getValue(int i, int j) {
		assureIndices(i, j);
		IntPair interval = Pair.of(i, j);
		if (values.containsKey(interval))
			return defaultValue;
		return values.get(interval);
	}

	@Override
	public void reset() {
		values.clear();
	}

	/**
	 * iterates over every mapped mapped value
	 */
	@Override
	public void forEach(DoubleMatrixVisitor consumer) {
		for (IntPair pair : values.keySet())
			consumer.visit(pair.getI(), pair.getJ(), values.get(pair));
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public void swapRows(int row1, int row2) {
		double temp;
		int cols = getColumnCount();
		for (int a = 0; a < cols; a++) {
			if (hasValue(row1, a)) {
				temp = getValue(row1, a);
				setValue(getValue(row2, a), row1, a);
				setValue(temp, row2, a);
			}
		}
	}

}
