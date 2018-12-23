package de.nuttercode.math;

import java.util.HashMap;
import java.util.Map;

import de.nuttercode.math.RealMatrixConsumer;
import de.nuttercode.util.IntPair;
import de.nuttercode.util.Pair;

/**
 * implementation of {@link RealMatrix}. every index not already added by
 * {@link #setValue(double, int, int)} will be considered to have
 * {@link #defaultValue} as its value.
 * 
 * @author Johannes B. Latzel
 *
 */
public class SparseRealMatrix implements RealMatrix {

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
	public SparseRealMatrix() {
		this(0);
	}

	/**
	 * creates a new {@link SparseRealMatrix} with default value defaultValue
	 * 
	 * @param defaultValue default value
	 */
	public SparseRealMatrix(double defaultValue) {
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
	public void forEach(RealMatrixConsumer consumer) {
		for (IntPair pair : values.keySet())
			consumer.consume(pair.getI(), pair.getJ(), values.get(pair));
	}

}
