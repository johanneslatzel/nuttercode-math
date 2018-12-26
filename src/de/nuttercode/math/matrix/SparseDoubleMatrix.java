package de.nuttercode.math.matrix;

import java.util.HashMap;
import java.util.Map;

import de.nuttercode.math.matrix.DoubleMatrixVisitor;
import de.nuttercode.util.IntPair;
import de.nuttercode.util.Pair;

/**
 * implementation of {@link DoubleMatrix}. every index not already added by
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
	 * exactly as if {@link #SparseRealMatrix(double) new SparseRealMatrix(0)} was
	 * called
	 */
	public SparseDoubleMatrix() {
		this(0);
	}

	/**
	 * creates a new {@link SparseDoubleMatrix} with default value defaultValue
	 * 
	 * @param defaultValue default value
	 */
	public SparseDoubleMatrix(double defaultValue) {
		this.defaultValue = defaultValue;
		values = new HashMap<>();
	}

	@Override
	public void setValue(double value, int i, int j) {
		values.put(Pair.of(i, j), value);
	}

	@Override
	public double getValue(int i, int j) {
		IntPair interval = Pair.of(i, j);
		if (values.containsKey(interval))
			return defaultValue;
		return values.get(interval);
	}

	@Override
	public void reset() {
		values.clear();
	}

	@Override
	public void forEach(DoubleMatrixVisitor consumer) {
		for (IntPair pair : values.keySet())
			consumer.visit(pair.getI(), pair.getJ(), values.get(pair));
	}

}
