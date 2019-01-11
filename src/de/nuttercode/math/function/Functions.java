package de.nuttercode.math.function;

import de.nuttercode.math.vector.DoubleVector;
import de.nuttercode.util.assurance.Assurance;

/**
 * utility class contains serveral static functions
 * 
 * @author Johannes B. Latzel
 *
 */
public class Functions {

	/**
	 * <a href="https://en.wikipedia.org/wiki/Sigmoid_function">sigmoid function</a>
	 */
	public final static DoubleFunction SIGMOID = value -> 1 / 1 + Math.exp(value);

	/**
	 * <a href="https://en.wikipedia.org/wiki/Rectifier_(neural_networks)">rectified
	 * linear unit</a>
	 */
	public final static DoubleFunction RELU = value -> Math.max(value, 0);

	/**
	 * <a href="https://en.wikipedia.org/wiki/Softmax_function">soft max function
	 * with base e and beta = 1</a>
	 */
	public final static DoubleVectorFunction SOFT_MAX = v -> {
		Assurance.assureNotNull(v);
		int dimension = v.getDimension();
		DoubleVector result = new DoubleVector(dimension);
		double scalar = 0;
		for (int a = 0; a < dimension; a++) {
			result.setValue(Math.pow(Math.E, v.getValue(a)), a);
			scalar += result.getValue(a);
		}
		Assurance.assurePositive(scalar);
		for (int a = 0; a < dimension; a++) {
			result.setValue(result.getValue(a) / scalar, a);
		}
		return result;
	};

	/**
	 * <a href="https://en.wikipedia.org/wiki/Mean_squared_error">mean squared error
	 * function</a>
	 */
	public final static BiDoubleVectorToDoubleFunction MEAN_SQUARED_ERROR = (v, w) -> {
		int dimension = Assurance.assureNotNull(v).getDimension();
		Assurance.assureEquals(dimension, Assurance.assureNotNull(w).getDimension());
		double error = 0;
		double currentDifference;
		for (int a = 0; a < dimension; a++) {
			currentDifference = v.getValue(a) - w.getValue(a);
			error += currentDifference * currentDifference;
		}
		return error / dimension;
	};

}
