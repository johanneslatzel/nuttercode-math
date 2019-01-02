package de.nuttercode.math.matrix;

import de.nuttercode.math.matrix.DoubleMatrixVisitor;

/**
 * 
 * represents a matrix M = \((a_{i,j})_{i,j}\) with \(a_{i,j} \in \mathbb{R}, i
 * \in I, j \in J, and I, J \subset \mathbb{N}\)
 * 
 * @see FullDoubleMatrix
 * @see SparseDoubleMatrix
 * @author Johannes B. Latzel
 *
 */
public interface DoubleMatrix extends Matrix {

	/**
	 * sets a_{i, j} = value
	 * 
	 * @param i     row index
	 * @param j     column index
	 * @param value
	 */
	void setValue(double value, int i, int j);

	/**
	 * @param i row index
	 * @param j column index
	 * @return a_{i, j}
	 */
	double getValue(int i, int j);

	/**
	 * invokes {@link DoubleMatrixVisitor#visit(int, int, double)} for a subset of
	 * \(I \times J\). the actual behavior depends on the implementation.
	 */
	void forEach(DoubleMatrixVisitor consumer);

}
