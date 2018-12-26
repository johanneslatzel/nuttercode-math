package de.nuttercode.math.matrix;

/**
 * 
 * common interface of full matrices. those are matrix-implementations which use
 * arrays to save all values.
 * 
 * @author Johannes B. Latzel
 *
 */
public interface FullMatrix {

	/**
	 * @return number of rows
	 */
	int getRowCount();

	/**
	 * @return number of columns
	 */
	int getColumnCount();

	/**
	 * swaps two rows
	 * 
	 * @param row1
	 * @param row2
	 */
	public void swapRows(int row1, int row2);

}